package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @Description 主角类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/1 8:33 AM
 */
public class Hero extends AbstractMapleStoryObject {

    public Hero() {
        this.speed = Constant.HERO_SPEED;
        this.dir = Direction.RIGHT;
        this.verticalDir = Direction.HOLD;
        this.action = Action.STAND;
    }

    public Hero(MapleStoryClient msc, List<Image> images, int x, int y) {
        this();
        this.msc = msc;
        this.imgs = images;
        this.width = this.imgs.get(0).getWidth(null);
        this.height = this.imgs.get(0).getHeight(null);
        this.x = x;
        this.y = y - 100;
    }

    /**
     * 跳跃 X 轴初速度
     */
    public double v0 = Constant.INIT_JUMP_V0;
    /**
     * 末速度
     */
    public double vt = 0.0;
    /**
     * 重力加速度
     */
    private static final double g = 9.8;
    /**
     * 单位时间
     */
    private final double t = 0.5;
    /**
     * 高度变化量
     */
    public double deltaHeight;

    /**
     * 表示自由落体的 Boolean 变量
     */
    public boolean drop = true;

    /**
     * 竖直上抛运动
     * @param groundList msc管家中的 List<Ground> 容器
     */
    public void jump(List<Ground> groundList) {
        if (!drop) {
            vt = v0 - g * t;
            v0 = vt;
            deltaHeight = v0 * t;
            y -= deltaHeight;
            // 判断停止竖直上抛
            if (vt <= 0) {
                // 开始自由落体
                drop = true;
                v0 = 0;
                verticalDir = Direction.DOWN;
                jumpDown(groundList);
            }
        } else {
            jumpDown(groundList);
        }
    }

    /**
     * 自由落体
     * @param groundList msc管家中的 List<Ground> 容器
     */
    public void jumpDown(List<Ground> groundList) {
        vt = v0 + g * t;
        v0 = vt;
        deltaHeight = v0 * t;
        y += deltaHeight;
        // 停止自由落体
        // 与地面相交判断
        for (Ground ground : groundList) {
            if (this.getRectangle().intersects(ground.getRectangle())
                    && y + height - msc.bg.y - ground.y <= v0
            ) {
                // 判断 y 值
                if (y + height >= msc.bg.y + ground.y) {
                    y = msc.bg.y + ground.y + 6 - height;
                    jump = false;
                    drop = false;
                    v0 = Constant.INIT_JUMP_V0;
                    vt = 0;
                    verticalDir = Direction.HOLD;
                    break;
                }
            } else {
                drop = true;
            }
        }
    }

    private int step = -1, cnt = 0;
    public boolean right, left, prone, jump, shoot, moving;

    @Override
    public void move() {
        if (prone && !jump) {
            moving = false;
            action = Action.PRONE;
            return;
        }

        if (left && !right) {
            dir = Direction.LEFT;
            action = Action.WALK;
            moving = true;
            x -= speed;
        } else if (!left && right) {
            dir = Direction.RIGHT;
            action = Action.WALK;
            moving = true;
            x += speed;
        } else {
            action = Action.STAND;
            moving = false;
        }

        if (shoot) {
            shoot();
            action = Action.SHOOT;
        }

        if (jump) {
            // 竖直上抛
            verticalDir = Direction.UP;
            jump(msc.groundList);
            action = Action.JUMP;
        } else {
            // 自由落体
            verticalDir = Direction.DOWN;
            jumpDown(msc.groundList);
        }

        outOfBounds();
    }

    private int shoot_rate = 0;

    /**
     * 射箭的方法
     */
    private void shoot() {
        shoot_rate++;
        if (shoot_rate % 8 == 0) {
            shoot_rate = 0;
            shoot = false;
            int arrow_x = x, arrow_y = y + 50;
            if (dir == Direction.RIGHT) {
                arrow_x += 26;
            }
            // 创建弓箭对象
            Arrow arrow = new Arrow(msc, ImageUtil.getValue("arrow"), arrow_x, arrow_y, dir);
            // 通过中介者模式访问主类中的容器，并添加进去
            msc.arrowList.add(arrow);
        }
    }

    private void outOfBounds() {
        if (x <= 0) {
            x = 0;
        }
        if (x >= Constant.GAME_WIDTH - this.width) {
            x = Constant.GAME_WIDTH - this.width;
        }
    }

    private void calcStep() {
        // 图片切换速度
        switch (action) {
            case STAND:
                if (cnt++ % 4 == 0) step++;
                break;
            case WALK:
            case SHOOT:
                if (cnt++ % 3 == 0) step++;
                break;
            case PRONE:
            case JUMP:
                if (cnt++ % 2 == 0) step++;
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        calcStep();
        int idx = 0; // 图片序号
        switch (dir) {
            case RIGHT:
                switch (action) {
                    case STAND:
                        idx = step % 4;
                        break;
                    case WALK:
                        idx = step % 5 + 8;
                        break;
                    case PRONE:
                        idx = step % 2 + 18;
                        break;
                    case JUMP:
                        idx = step % 2 + 22;
                        break;
                    case SHOOT:
                        idx = step % 4 + 26;
                        break;
                    default:
                        break;
                }
                break;
            case LEFT:
                switch (action) {
                    case STAND:
                        idx = step % 4 + 4;
                        break;
                    case WALK:
                        idx = step % 5 + 13;
                        break;
                    case PRONE:
                        idx = step % 2 + 20;
                        break;
                    case JUMP:
                        idx = step % 2 + 24;
                        break;
                    case SHOOT:
                        idx = step % 4 + 30;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        img = imgs.get(idx);

        if (prone) {
            g.drawImage(img, x, y + 26, null);
            g.drawRect(x, y + 26, img.getWidth(null), img.getHeight(null));
        } else if (jump) {
            g.drawImage(img, x, y, null);
            g.drawRect(x, y, img.getWidth(null), img.getHeight(null));
        } else {
            g.drawImage(img, x, y, null);
            g.drawRect(x, y, img.getWidth(null), img.getHeight(null));
        }
    }

    private boolean flag = true; // 判断动作开始

    /**
     * 按键操作
     * @param e 键盘事件
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_S:
                prone = true;
                break;
            case KeyEvent.VK_K:
                jump = true;
                break;
            case KeyEvent.VK_J:
                shoot = true;
                break;
            default:
                break;
        }
        if (flag) {
            cnt = 0;
            step = -1;
            flag = false;
        }
    }

    public void keyReleased(KeyEvent e) {
        flag = true;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_S:
                prone = false;
                break;
            default:
                break;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
}

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
        this.action = Action.STAND;
    }

    public Hero(MapleStoryClient mapleStoryClient, List<Image> images, int x, int y) {
        this();
        this.mapleStoryClient = mapleStoryClient;
        this.imgs = images;
        this.width = this.imgs.get(0).getWidth(null);
        this.height = this.imgs.get(0).getHeight(null);
        this.x = x;
        this.y = y;
    }

    /**
     * 跳跃 X 轴初速度
     */
    private double v0 = Constant.INIT_JUMP_V0;
    /**
     * 末速度
     */
    private double vt = 0.0;
    /**
     * 重力加速度
     */
    private static final double g = 9.8;
    /**
     * 单位时间
     */
    private final double t = 0.5;

    /**
     * 跳的方法
     */
    private void jump() {
        // 竖直上抛公式 v_t = v_0 - g * t
        vt = v0 - g * t;
        // 下一次的初速度是上一次的末速度
        v0 = vt;
        y -= v0 * t;
        if (y >= 565) {
            jump = false;
            v0 = Constant.INIT_JUMP_V0;
            vt = 0.0;
            y = 565;
        }
    }

    private int step = -1, cnt = 0;
    public boolean right, left, prone, jump, shoot, moving;

    @Override
    public void move() {
        if (prone && !jump) {
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
            jump();
            action = Action.JUMP;
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
            Arrow arrow = new Arrow(mapleStoryClient, ImageUtil.getValue("arrow"), arrow_x, arrow_y, dir);
            // 通过中介者模式访问主类中的容器，并添加进去
            mapleStoryClient.arrowList.add(arrow);
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

        if (prone) {
            g.drawImage(imgs.get(idx), x, y + 26, null);
        } else if (jump) {
            g.drawImage(imgs.get(idx), x, y, null);
        } else {
            g.drawImage(imgs.get(idx), x, y, null);
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
}

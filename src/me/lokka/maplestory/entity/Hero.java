package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Description 主角类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/1 8:33 AM
 */
public class Hero extends AbstractMapleStoryObject {

    public static Image[] images = new Image[35];
    static {
        // right stand
        for (int i = 0; i < 4; i++) {
            images[i] = ImageUtil.getKey("hero_right_stand_" + i);
        }
        // left stand
        for (int i = 4; i < 8; i++) {
            images[i] = ImageUtil.getKey("hero_left_stand_" + (i - 4));
        }
        // right walk
        for (int i = 8; i < 13; i++) {
            images[i] = ImageUtil.getKey("hero_right_walk_" + (i - 8));
        }
        // left walk
        for (int i = 13; i < 18; i++) {
            images[i] = ImageUtil.getKey("hero_left_walk_" + (i - 13));
        }
        // right prone
        for (int i = 18; i < 21; i++) {
            images[i] = ImageUtil.getKey("hero_right_prone_" + (i - 18));
        }
        // left prone
        for (int i = 21; i < 23; i++) {
            images[i] = ImageUtil.getKey("hero_left_prone_" + (i - 21));
        }
        // right jump
        for (int i = 23; i < 25; i++) {
            images[i] = ImageUtil.getKey("hero_right_jump_" + (i - 23));
        }
        // left jump
        for (int i = 25; i < 27; i++) {
            images[i] = ImageUtil.getKey("hero_left_jump_" + (i - 25));
        }
        // right shoot
        for (int i = 27; i < 31; i++) {
            images[i] = ImageUtil.getKey("hero_right_shoot_" + (i - 27));
        }
        // left shoot
        for (int i = 31; i < 35; i++) {
            images[i] = ImageUtil.getKey("hero_left_shoot_" + (i - 31));
        }
    }

    public Hero(){
        this.speed = Constant.HERO_SPEED;
        this.dir = Direction.RIGHT;
        this.action = Action.STAND;
    }

    public Hero(MapleStoryClient mapleStoryClient, int x, int y) {
        this();
        this.mapleStoryClient = mapleStoryClient;
        this.width = images[0].getWidth(null);
        this.height = images[0].getHeight(null);
        this.x = x;
        this.y = y;
    }

    /**
     * 初速度
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
    private double t = 0.5;
    /**
     * 跳的方法
     */
    private void jump() {
        // 竖直上抛公式 v_t = v_0 - g * t
        vt = v0 - g * t;
        // 下一次的初速度是上一次的末速度
        v0 = vt;
        y -= v0 * t;
        if (y >= 700) {
            jump = false;
            v0 = Constant.INIT_JUMP_V0;
            vt = 0.0;
            y = 700;
        }
    }

    private int step = -1, cnt = 0;
    public boolean right, left, prone, jump, shoot;

    @Override
    public void move() {
        if (prone && !jump) {
            action = Action.PRONE;
            return;
        }

        if (left && !right) {
            dir = Direction.LEFT;
            action = Action.WALK;
            x -= speed;
        } else if (!left && right) {
            dir = Direction.RIGHT;
            action = Action.WALK;
            x += speed;
        } else {
            action = Action.STAND;
        }

        if (shoot) {
            action = Action.SHOOT;
        }

        if (jump) {
            jump();
            action = Action.JUMP;
        }

        outOfBounds();

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
                        idx = step % 4; break;
                    case WALK:
                        idx = step % 5 + 8; break;
                    case PRONE:
                        idx = step % 2 + 18; break;
                    case JUMP:
                        idx = step % 2 + 23; break;
                    case SHOOT:
                        idx = step % 4 + 27; break;
                    default:
                        break;
                }
                break;
            case LEFT:
                switch (action) {
                    case STAND:
                        idx = step % 4 + 4; break;
                    case WALK:
                        idx = step % 5 + 13; break;
                    case PRONE:
                        idx = step % 2 + 21; break;
                    case JUMP:
                        idx = step % 2 + 25; break;
                    case SHOOT:
                        idx = step % 4 + 31; break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        if (prone) {
            g.drawImage(images[idx], x, y + 26, null);
        } else if (jump) {
            g.drawImage(images[idx], x, y, null);
        } else {
            g.drawImage(images[idx], x, y, null);
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
                // shoot = true;
                shoot = !shoot;
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

package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Description 主角类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/1 8:33 AM
 */
public class Hero extends AbstractMapleStoryObject {

    public static Image[] images = new Image[27];
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
        // right jump
        for (int i = 18; i < 21; i++) {
            images[i] = ImageUtil.getKey("hero_right_jump_" + (i - 18));
        }
        // left jump
        for (int i = 21; i < 23; i++) {
            images[i] = ImageUtil.getKey("hero_left_jump_" + (i - 21));
        }
        // right prone
        for (int i = 23; i < 25; i++) {
            images[i] = ImageUtil.getKey("hero_right_prone_" + (i - 23));
        }
        // left prone
        for (int i = 25; i < 27; i++) {
            images[i] = ImageUtil.getKey("hero_left_prone_" + (i - 25));
        }
    }

    public Hero(){
        this.speed = 5;
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

    private int step = -1, cnt = 0;
    public boolean right, left, prone;

    @Override
    public void move() {
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
    }

    private void calcStep() {
        // 图片切换进度
        switch (action) {
            case STAND:
                if (cnt++ % 4 == 0) step++;
                break;
            case WALK:
                if (cnt++ % 3 == 0) step++;
                break;
//            case PRONE:
//                if (cnt++ % 2 == 0) step++;
//                break;
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
//                    case PRONE:
//                        idx = step % 2 + 23; break;
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
//                    case PRONE:
//                        idx = step % 2 + 25; break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        g.drawImage(images[idx], x, y, null);
    }

    private boolean flag = true; // 判断动作开始

    /**
     * 按键操作
     * @param e 键盘事件
     */
    public void keyPressed(KeyEvent e) {
        if (flag) {
            cnt = 0;
            step = -1;
            flag = false;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
//            case KeyEvent.VK_CAPS_LOCK:
//                prone = !prone;
//                break;
            default:
                break;
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
//            case KeyEvent.VK_CAPS_LOCK:
//                prone = !prone;
//                break;
            default:
                break;
        }
    }
}

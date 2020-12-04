package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;

import java.awt.*;
import java.util.List;

/**
 * @Description 箭矢类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/3 8:23 AM
 */
public class Arrow extends AbstractMapleStoryObject {
    /**
     * left : 0
     * right : 1
     */

    public Arrow() {
        this.speed = Constant.ARROW_SPEED;
    }

    public Arrow(MapleStoryClient mapleStoryClient, List<Image> images, int x, int y, Direction dir) {
        this();
        this.mapleStoryClient = mapleStoryClient;
        this.imgs = images;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.width = imgs.get(0).getWidth(null);
        this.height = imgs.get(0).getHeight(null);
        this.action = Action.STAND;
    }

    @Override
    public void move() {
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }
        outOfBounds();
    }

    @Override
    public void draw(Graphics g) {
        move();
        switch (dir) {
            case LEFT:
                img = imgs.get(0);
                break;
            case RIGHT:
                img = imgs.get(1);
                break;
            default:
                break;
        }
        g.drawImage(img, x, y, null);
    }

    /**
     * 判断箭矢出界的方法
     */
    private void outOfBounds() {
        if (x < -500 || x > Constant.GAME_WIDTH + 500) {
            mapleStoryClient.arrowList.remove(this);
        }
    }
}

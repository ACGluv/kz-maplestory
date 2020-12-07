package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;

import java.awt.*;

/**
 * @Description 背景图片类
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 10:45 AM
 */
public class Background extends AbstractMapleStoryObject{

    public Background() {
        this.speed = Constant.HERO_SPEED;
    }

    public Background(MapleStoryClient msc, Image img) {
        this();
        this.msc = msc;
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.x = 0;
        this.y = Constant.GAME_HEIGHT - this.height;
        this.dir = Direction.LEFT;
        this.action = Action.STAND;
    }

    @Override
    public void move() {
        switch (msc.hero.dir) {
            case RIGHT:
                if (msc.hero.moving
                        && msc.hero.x > Constant.GAME_WIDTH / 2 - msc.hero.width / 2
                        && x + width > Constant.GAME_WIDTH
                ) {
                    x -= speed;
                    msc.hero.speed = 0;
                } else {
                    msc.hero.speed = Constant.HERO_SPEED;
                }
                break;

            case LEFT:
                if (msc.hero.moving
                        && msc.hero.x < Constant.GAME_WIDTH / 2 - msc.hero.width / 2
                        && x < 0
                ) {
                    x += speed;
                    msc.hero.speed = 0;
                } else {
                    msc.hero.speed = Constant.HERO_SPEED;
                }
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }
}

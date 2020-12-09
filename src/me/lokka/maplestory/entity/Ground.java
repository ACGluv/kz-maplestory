package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;

/**
 * @Description 地面类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/9 8:32 AM
 */
public class Ground extends AbstractMapleStoryObject {

    public Ground() {

    }

    public Ground(MapleStoryClient msc, Image img, int x, int y) {
        this.msc = msc;
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public Ground(MapleStoryClient msc, Image img, int x, int y, int width, int height) {
        this.msc = msc;
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = width;
        this.height = height;
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }


    @Override
    public void move() {
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(img, msc.bg.x + x, msc.bg.y + y, null);
        g.setColor(Color.WHITE);
        g.drawRect(msc.bg.x + x, msc.bg.y + y, width, height);
    }
}

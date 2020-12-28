package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;

import java.awt.*;

/**
 * @Description 绳子类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/28 9:18 AM
 */
public class Rope extends Ground {

    public Rope(MapleStoryClient msc, Image img, int x, int y) {
        super(msc, img, x, y);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(msc.bg.x + x + width / 3, msc.bg.y + y - 5, width / 3, height + 5);
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.setColor(Color.WHITE);
        if (img != null) {
            g.drawImage(img, msc.bg.x + x, msc.bg.y + y, null);
        } else {
            g.drawRect(msc.bg.x + x, msc.bg.y + y, width, height);
        }
        g.drawRect(msc.bg.x + x + width / 3, msc.bg.y + y - 5, width / 3, height + 5);
    }
}

package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.util.ImageUtil;
import me.lokka.maplestory.util.MapleStoryUtil;

import java.awt.*;
import java.util.List;

/**
 * @Description 伤害值类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/14 10:56 AM
 */
public class Power extends AbstractMapleStoryObject {

    private int num;

    public Power(MapleStoryClient msc, int x, int y, int num) {
        this.msc = msc;
        this.x = x;
        this.y = y;
        this.num = num;
        this.imgs = ImageUtil.getValue("powernum");
        this.width = imgs.get(0).getWidth(null);
        this.height = imgs.get(0).getHeight(null);
    }

    private int cnt = 0;
    @Override
    public void move() {
        this.y -= 3;
        cnt++;
        if (cnt == 15) {
            msc.powerList.remove(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        int tmp = num, tx = x;
        while(tmp != 0) {
            int idx = tmp % 10;
            g.drawImage(imgs.get(idx), msc.bg.x + tx, msc.bg.y + y, null);
            tmp /= 10;
            tx -= width - 2;
        }
        move();
    }
}

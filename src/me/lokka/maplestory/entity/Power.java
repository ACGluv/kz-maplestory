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
        int tmp = num, tx = x, ty = y, cnt = 1;
        while(tmp != 0) {
            int idx = tmp % 10;
            if (cnt++ % 2 == 0) {
                ty -= 6;
            } else {
                ty += 6;
            }
            tmp /= 10;
            g.drawImage(imgs.get(idx), msc.bg.x + tx, msc.bg.y + ty, null);
            tx -= width - 12;
        }
        move();
    }
}

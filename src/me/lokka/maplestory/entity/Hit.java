package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;

import java.awt.*;
import java.util.List;

/**
 * @Description 击中效果
 * @Author: kainzhang (张健)
 * @Date: 2020/12/30 4:55 PM
 */
public class Hit extends Widget {

    public Hit (MapleStoryClient msc, List<Image> imgs, int x, int y, int rate) {
        super(msc, imgs, x, y, rate);
    }

    private int cnt = -1, step = -1;

    @Override
    public void draw(Graphics g) {
        if (++cnt % rate == 0) {
            step++;
            cnt = 0;
        }
        if (step >= imgs.size()) {
            msc.hitList.remove(this);
            return;
        }
        g.drawImage(imgs.get(step), x, y, null);
    }
}

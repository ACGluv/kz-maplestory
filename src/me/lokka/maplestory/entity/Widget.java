package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;

import java.awt.*;
import java.util.List;

/**
 * @Description 图片元素类（地图中的图片
 * @Author: kainzhang (张健)
 * @Date: 2020/12/28 10:57 PM
 */
public class Widget extends AbstractMapleStoryObject {

    /**
     * 图片切换频率
     */
    private int rate;

    public Widget(MapleStoryClient msc, List<Image> imageList, int x, int y, int rate) {
        this.msc = msc;
        this.imgs = imageList;
        this.x = x;
        this.y = y;
        this.rate = rate;
        System.out.println(imgs.size());
    }

    private int cnt = -1, step = -1;

    @Override
    public void draw(Graphics g) {
        if (++cnt % rate == 0) {
            step++;
            cnt = 0;
        }
        int idx = step % imgs.size();
        g.drawImage(imgs.get(idx), msc.bg.x + x, msc.bg.y + y, null);
    }
}

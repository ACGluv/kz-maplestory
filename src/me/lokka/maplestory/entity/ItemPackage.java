package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description 道具背包类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/16 11:09 AM
 */
public class ItemPackage extends AbstractMapleStoryObject {

    private List<Item> itemPackage = new CopyOnWriteArrayList<>();

    public ItemPackage(MapleStoryClient msc, int x, int y) {
        this.msc = msc;
        this.x = x;
        this.y = y;
        this.img = ImageUtil.getValue("itempackage").get(0);
        this.live = false;
    }

    public List<Item> getItemPackage() {
        return this.itemPackage;
    }

    @Override
    public void draw(Graphics g) {
        if (!live) return;
        g.drawImage(img, x, y, null);

        // 12 为图片基础左边距，16+36 为图片基础上边距
        int dx = 12, dy = 16, edgeL = 36;
        for (int i = 0; i < itemPackage.size(); i++) {
            // 每四个一换行，更新 y轴相对坐标
            if (i % 4 == 0) {
                dy += edgeL;
            }
            Item item = itemPackage.get(i);
            // 长宽最长设定为 30, 短边等比例缩放
            int tw = 30, th = 30;
            if (item.width > item.height) {
                th = (int) (30.0 / item.width * item.height);
            } else if (item.width < item.height) {
                tw = (int) (30.0 / item.height * item.width);
            }
            g.drawImage(item.img, x + i % 4 * edgeL + dx, y + dy, tw, th, null);
            g.setColor(Color.BLACK);
            g.drawString("" + item.qty, x + i % 4 * edgeL + dx, y + dy + 30);
        }
    }
}

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
        int dx = 36, dy = 17;
        for (int i = 0; i < itemPackage.size(); i++) {
            if (i % 4 == 0) {
                dx = 36;
                dy += 36;
            }
            Item item = itemPackage.get(i);
            g.drawImage(item.img, x + i % 4 * dx + 13, y + dy, null);
            g.setColor(Color.BLACK);
            g.drawString("" + item.qty, x + i % 4 * dx + 13, y + dy + 30);
        }
    }
}

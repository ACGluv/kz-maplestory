package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;

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
    }

    public List<Item> getItemPackage() {
        return this.itemPackage;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}

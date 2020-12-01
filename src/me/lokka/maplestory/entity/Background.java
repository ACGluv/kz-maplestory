package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;
import java.util.UUID;

/**
 * @Description 背景图片类
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 10:45 AM
 */
public class Background extends AbstractMapleStoryObject{

    public Background(){}

    public Background(MapleStoryClient mapleStoryClient, String imgKey, int x, int y){
        this.mapleStoryClient = mapleStoryClient;
        this.img = ImageUtil.getKey(imgKey);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
        move();
    }

    @Override
    public void move() {

    }

//    public static void main(String[] args) {
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid.toString().replace("-", ""));
//    }
}

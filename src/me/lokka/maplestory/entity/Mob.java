package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;

import java.awt.*;
import java.util.List;

/**
 * @Description 怪物类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/4 8:29 AM
 */
public class Mob extends AbstractMapleStoryObject {

    public Mob(List<Image> imgs) {
        this.imgs = imgs;
        this.width = imgs.get(0).getWidth(null);
        this.height = imgs.get(0).getHeight(null);
        this.x = 500;
        this.y = 700;
    }

    public Mob(MapleStoryClient mapleStoryClient, List<Image> imgs, int x, int y) {
        this(imgs);
        this.mapleStoryClient = mapleStoryClient;
        this.x = x;
        this.y = y;
        this.dir = Direction.LEFT;
        this.action = Action.STAND;
    }

    public Mob(MapleStoryClient mapleStoryClient, List<Image> imgs, int x, int y, String name, int level, int HP, int MP, int EXP, int speed) {
        this(mapleStoryClient, imgs, x, y);
        this.mapleStoryClient = mapleStoryClient;
        this.name = name;
        this.level = level;
        this.HP = HP;
        this.MP = MP;
        this.EXP = EXP;
        this.speed = speed;
    }

    public int cnt, step = -1;
    @Override
    public void move() {
        if (cnt++ % 3 == 0) step++;
        int idx = step % imgs.size();
        img = imgs.get(idx);
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(img, mapleStoryClient.background.x + x, y, null);
    }
}

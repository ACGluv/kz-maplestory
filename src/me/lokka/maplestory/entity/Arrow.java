package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;

import java.awt.*;
import java.util.List;

/**
 * @Description 箭矢类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/3 8:23 AM
 */
public class Arrow extends AbstractMapleStoryObject {
    /**
     * left : 0
     * right : 1
     */

    public Arrow() {
        this.speed = Constant.ARROW_SPEED;
    }

    public Arrow(MapleStoryClient msc, List<Image> images, int x, int y, Direction dir) {
        this();
        this.msc = msc;
        this.imgs = images;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.width = imgs.get(0).getWidth(null);
        this.height = imgs.get(0).getHeight(null);
        this.action = Action.STAND;
    }

    @Override
    public void move() {
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }
        outOfBounds();
    }

    @Override
    public void draw(Graphics g) {
        if (!live) {
            msc.arrowList.remove(this);
            return;
        }
        move();
        switch (dir) {
            case LEFT:
                img = imgs.get(0);
                break;
            case RIGHT:
                img = imgs.get(1);
                break;
            default:
                break;
        }
        g.drawImage(img, x, y, null);
        g.drawRect(x, y, width, height);
    }

    /**
     * 判断箭矢出界的方法
     */
    private void outOfBounds() {
        if (x < -500 || x > Constant.GAME_WIDTH + 500) {
            this.live = false;;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * 箭矢射中怪物
     * @param mob 被射击的怪物
     * @return 是否打击成功 (相交
     */
    public boolean hit(Mob mob) {
        if (this.live && mob.live && this.getRectangle().intersects(mob.getRectangle())) {
            this.live = false;
            mob.live = false;
            Item item = new Item(msc, mob.x + mob.width / 3, mob.y);
            msc.itemList.add(item);
            return true;
        }
        return false;
    }

    /**
     * 相交方法的重载，只做遍历
     * @param mobList 怪物容器
     * @return 是否与容器中怪物相交
     */
    public boolean hit(List<Mob> mobList) {
        for (Mob mob : mobList) {
            if (this.hit(mob)) {
                return true;
            }
        }
        return false;
    }
}

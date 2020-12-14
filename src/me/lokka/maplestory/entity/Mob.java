package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;
import java.util.List;

/**
 * @Description 怪物类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/4 8:29 AM
 */
public class Mob extends AbstractMapleStoryObject {

    /**
     * 最大血量
     */
    public static final int MAX_HP = 100;

    public Mob(List<Image> imgs) {
        this.imgs = imgs;
        this.width = imgs.get(0).getWidth(null);
        this.height = imgs.get(0).getHeight(null);
        this.x = 500;
        this.y = 700;
        this.HP = MAX_HP;
    }

    public Mob(MapleStoryClient msc, List<Image> imgs, int x, int y) {
        this(imgs);
        this.msc = msc;
        this.x = x;
        this.y = y;
        this.origin_y_down = this.y + this.height;
        this.dir = Direction.LEFT;
        this.action = Action.STAND;
    }

    public Mob(MapleStoryClient msc, List<Image> imgs, int x, int y, String name, int level, int HP, int MP, int EXP, int speed) {
        this(msc, imgs, x, y);
        this.msc = msc;
        this.name = name;
        this.level = level;
        this.HP = HP;
        this.MP = MP;
        this.EXP = EXP;
        this.speed = speed;
    }

    private int cnt, step = -1;
    public int die_x_right, origin_y_down;
    private void calcStep() {
        // 图片切换速度
        switch (action) {
            case STAND:
                if (cnt++ % 3 == 0) step++;
                break;
            case DIE:
                if (cnt++ % 2 == 0) step++;
                break;
            default:
                break;
        }
    }

    private boolean flag = false;
    @Override
    public void move() {
        if (!live) {
            if (!flag) {
                cnt = 0;
                step = -1;
                die_x_right = x + width;
                flag = true;
            }
            action = Action.DIE;
            return;
        }
    }

    private void drawBloodBar(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        Image bloodBlk = ImageUtil.getValue("common").get(0);
        int bloodBlkWidth = bloodBlk.getWidth(null);
        for (int i = 0; i < width / bloodBlkWidth * HP / MAX_HP; i++) {
            g.drawImage(bloodBlk, msc.bg.x + x + i * bloodBlkWidth, msc.bg.y + y - 12 , null);
        }
        g.setColor(c);
    }

    @Override
    public void draw(Graphics g) {
        move();
        calcStep();
        int idx = 0; // 图片序号
        switch (dir) {
            case RIGHT:
                break;
            case LEFT:
                switch (action) {
                    case STAND:
                        idx = step % 6;
                        break;
                    case DIE:
                        idx = step % 12 + 13;
                        if (idx == 24) {
                            msc.mobList.remove(this);
                            return;
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        img = imgs.get(idx);
        if (!live) {
            die_x_right = Math.max(die_x_right, x + img.getWidth(null));

            g.drawImage(
                    img,
                    msc.bg.x + die_x_right - img.getWidth(null),
                    msc.bg.y + origin_y_down - img.getHeight(null),
                    null
            );
            g.drawRect(
                    msc.bg.x + die_x_right - img.getWidth(null),
                    msc.bg.y + origin_y_down - img.getHeight(null),
                    img.getWidth(null),
                    img.getHeight(null)
            );
        } else {
            g.drawImage(
                    img,
                    msc.bg.x + x,
                    msc.bg.y + origin_y_down - img.getHeight(null),
                    null
            );
            g.drawRect(
                    msc.bg.x + x,
                    msc.bg.y + origin_y_down - img.getHeight(null),
                    img.getWidth(null),
                    img.getHeight(null)
            );
        }
        drawBloodBar(g);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(msc.bg.x + x, msc.bg.y + y, width, height);
    }
}

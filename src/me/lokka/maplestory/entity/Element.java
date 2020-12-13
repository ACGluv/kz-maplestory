package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;

import java.awt.*;
import java.util.List;

/**
 * @Description 界面元素、图片
 * @Author: kainzhang (张健)
 * @Date: 2020/12/11 2:45 PM
 */
public class Element extends AbstractMapleStoryObject{

    /**
     * 水平方向移动速度
     */
    private int hSpeed;
    /**
     * 竖直方向移动速度
     */
    private int vSpeed;
    /**
     * 是否跟随玩家移动
     */
    private boolean followPlayer;
    /**
     * 图片切换速率
     */
    private int rate = 1;

    public Element(MapleStoryClient msc, Image img, int x, int y, int hSpeed, int vSpeed, boolean followPlayer) {
        this.msc = msc;
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);

        this.x = x;
        this.y = y;
        this.hSpeed = hSpeed;
        this.vSpeed = vSpeed;

        this.followPlayer = followPlayer;
    }

    public Element(MapleStoryClient msc, List<Image> imageList, int rate, int x, int y, int hSpeed, int vSpeed, boolean followPlayer) {
        this(msc, imageList.get(0), x, y, hSpeed, vSpeed, followPlayer);
        this.imgs = imageList;
        this.rate = rate;
    }

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

    private int cnt = 0, step = -1;

    @Override
    public void move() {
        if (followPlayer) {

        }
        if (msc.hero.moving) {
            switch (msc.hero.dir) {
                case LEFT:
                    if (msc.hero.x > 0) {
                        if (msc.hero.x == Constant.GAME_WIDTH / 2 - msc.hero.width / 2) {
                            x += hSpeed - msc.hero.speed;
                        } else {
                            x += hSpeed;
                        }
                    }
                    break;
                case RIGHT:
                    if (msc.hero.x < Constant.GAME_WIDTH && msc.hero.x != Constant.GAME_WIDTH / 2 - msc.hero.width / 2) {
                        x -= msc.hero.speed + hSpeed;
                    } else {
                        x -= hSpeed;
                    }
                    break;
            }
            switch (msc.hero.verticalDir) {
                case UP:
                    y += vSpeed;
                    break;
                case DOWN:
                    y -= vSpeed;
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        if (imgs != null) {
            if (cnt++ % rate == 0) {
                step++;
                cnt = 1;
            }
            img = imgs.get(step % imgs.size());
        }
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }
}

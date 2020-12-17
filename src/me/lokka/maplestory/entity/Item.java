package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;

/**
 * @Description 道具类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/8 8:25 AM
 */
public class Item extends AbstractMapleStoryObject {

    public int qty = 1;
    public int pid;

    public Item() {

    }

    public Item(MapleStoryClient msc, int x, int y, int type) {
        this.msc = msc;
        this.x = x;
        this.y = y;
        int idx;
        switch (type) {
            case 0:
                idx = random.nextInt(2);
                this.img = ImageUtil.getValue("blood").get(idx);
                this.pid = idx;
                break;
            case 1:
                idx = random.nextInt(2);
                this.img = ImageUtil.getValue("mana").get(idx);
                this.pid = 2 * type + idx;
                break;
            case 2:
                idx = random.nextInt(7);
                this.img = ImageUtil.getValue("commonitem").get(idx);
                this.pid = 2 * type + idx;
                break;
            default: break;
        }
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.pid = pid;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(msc.bg.x + x, msc.bg.y + y, width, height);
    }

    @Override
    public void move() {
        if (jump) {
            jump();
        }
    }

    public boolean jump = true;
    /**
     * 跳跃 X 轴初速度
     */
    private double v0 = Constant.INIT_JUMP_V0;
    /**
     * 末速度
     */
    private double vt = 0.0;
    /**
     * 重力加速度
     */
    private static final double g = 9.8;
    /**
     * 单位时间
     */
    private final double t = 0.5;

    /**
     * 跳的方法
     */
    private void jump() {
        // 竖直上抛公式 v_t = v_0 - g * t
        vt = v0 - g * t;
        // 下一次的初速度是上一次的末速度
        v0 = vt;
        y -= v0 * t;
        if (y >= 1676) {
            jump = false;
            v0 = Constant.INIT_JUMP_V0;
            vt = 0.0;
            y = 1676;
        }
    }

    private int timer = 0;

    @Override
    public void draw(Graphics g) {
        if (timer++ >= Constant.TIME_OUT) {
            this.live = false;
        }
        if (!live) {
            msc.itemList.remove(this);
            return;
        }
        move();
        g.drawImage(img, msc.bg.x + x, msc.bg.y + y, null);
    }
}

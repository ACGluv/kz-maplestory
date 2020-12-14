package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * @Description 项目中所有的实体类都要继承此抽象类
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 10:44 AM
 */
public abstract class AbstractMapleStoryObject implements Moveable, Drawable {
    /**
     * 图片列表
     */
    public List<Image> imgs;
    /**
     * 图片对象
     */
    public Image img;
    /**
     * 横坐标
     */
    public int x;
    /**
     * 纵坐标
     */
    public int y;
    /**
     * 对象的宽度
     */
    public int width;
    /**
     * 对象的高度
     */
    public int height;
    /**
     * 中介者设计模式
     */
    public MapleStoryClient msc;

    /**
     * 对象的速度
     */
    public int speed;

    /**
     * 表示左右方向的枚举类型
     */
    public Direction dir;

    /**
     * 表示竖直方向的枚举类型
     */
    public Direction verticalDir;

    /**
     * 表示动作的枚举类型
     */
    public Action action;

    /**
     * 名称
     */
    public String name;
    /**
     * 等级
     */
    public int level;
    /**
     * 血量
     */
    public int HP;
    /**
     * 法量
     */
    public int MP;
    /**
     * 经验值
     */
    public int EXP;

    /**
     * 表示生死
     */
    public boolean live = true;

    /**
     * 攻击值
     */
    public int atk;

    /**
     * 随机数生成器
     */
    public Random random = new Random();

    public void draw(Graphics g) {
        move();
        g.drawImage(img, x, y, null);
    };

    public Rectangle getRectangle() {
        return null;
    }

    @Override
    public void move() {}
}

package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;

import java.awt.*;
import java.util.concurrent.locks.Lock;

/**
 * @Description 项目中所有的实体类都要继承此抽象类
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 10:44 AM
 */
public abstract class AbstractMapleStoryObject implements Moveable, Drawable {

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
    public MapleStoryClient mapleStoryClient;

    /**
     * 对象的速度
     */
    public int speed;

    /**
     * 表示方向的枚举类型
     */
    public Direction dir;

    /**
     * 表示动作的枚举类型
     */
    public Action action;

}

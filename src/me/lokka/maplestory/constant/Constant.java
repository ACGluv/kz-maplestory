package me.lokka.maplestory.constant;

import me.lokka.maplestory.util.MapleStoryUtil;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description 存放项目中所有的常量
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 9:49 AM
 */
public class Constant {
    /**
     * 存放属性配置文件的类
     */
    private static Properties props = new Properties();
    static {
        try {
            props.load(MapleStoryUtil.class.getClassLoader().getResourceAsStream("maplestory.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取屏幕尺寸
     */
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * 窗口的宽度
     */
    //public static final int GAME_WIDTH = Integer.parseInt(props.getProperty("GAME_WIDTH"));
    public static final int GAME_WIDTH = screenSize.width;
    /**
     * 窗口的高度
     */
    //public static final int GAME_HEIGHT = Integer.parseInt(props.getProperty("GAME_HEIGHT"));
    public static final int GAME_HEIGHT = screenSize.height;
    /**
     * 项目标题
     */
    public static final String TITLE = props.getProperty("TITLE");
    /**
     * 图片路径前缀
     */
    public static final String IMG_PRE = props.getProperty("IMG_PRE");
    /**
     * 图片路径后缀
     */
    public static final String IMG_POST = props.getProperty("IMG_POST");
    /**
     * 移动速度
     */
    public static final int HERO_SPEED = Integer.parseInt(props.getProperty("HERO_SPEED"));
    /**
     * 跳跃的初速度
     */
    public static final double INIT_JUMP_V0 = Double.parseDouble(props.getProperty("INIT_JUMP_V0"));
    /**
     * 弓箭的速度
     */
    public static final int ARROW_SPEED = Integer.parseInt(props.getProperty("ARROW_SPEED"));
}

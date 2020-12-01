package me.lokka.maplestory.constant;

import java.awt.*;

/**
 * @Description 存放项目中所有的常量
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 9:49 AM
 */
public class Constant {
    /**
     * 获取屏幕尺寸
     */
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * 窗口的宽度
     */
    public static final int GAME_WIDTH = screenSize.width;
    /**
     * 窗口的高度
     */
    public static final int GAME_HEIGHT = screenSize.height;
    /**
     * 项目标题
     */
    public static final String TITLE = "Maple Story";
    /**
     * 图片路径前缀
     */
    public static final String IMG_PRE = "me/lokka/maplestory/image/";
    /**
     * 图片路径后缀
     */
    public static final String IMG_POST = ".png";
    /**
     * 音频路径前缀
     */
    public static final String AUDIO_PRE = "me/lokka/maplestory/audio/";
}

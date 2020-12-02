package me.lokka.maplestory.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 项目启动时，一次性将图片从硬盘中读取到JVM中，静态加载图片（从内存中读取）
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 10:00 AM
 */
public class ImageUtil {
    /**
     * 外界无法访问的静态变量容器，用来存储项目中所有图片
     */
    private static final Map<String, Image> images = new HashMap<>();
    static {
        // logo 图片
        images.put("logo", MapleStoryUtil.getImage("icon/logo"));
        // background
        images.put("background", MapleStoryUtil.getImage("background/nasa"));
        // hero right stand
        for (int i = 0; i < 4; i++) {
            images.put("hero_right_stand_" + i, MapleStoryUtil.getImage("hero/right/stand1_" + i));
        }
        // hero left stand
        for (int i = 0; i < 4; i++) {
            images.put("hero_left_stand_" + i, MapleStoryUtil.getImage("hero/left/stand1_" + i));
        }
        // hero right walk
        for (int i = 0; i < 5; i++) {
            images.put("hero_right_walk_" + i, MapleStoryUtil.getImage("hero/right/walk1_" + i));
        }
        // hero left walk
        for (int i = 0; i < 5; i++) {
            images.put("hero_left_walk_" + i, MapleStoryUtil.getImage("hero/left/walk1_" + i));
        }
        // hero right prone
        for (int i = 0; i < 2; i++) {
            images.put("hero_right_prone_" + i, MapleStoryUtil.getImage("hero/right/prone_" + i));
        }
        // hero left prone
        for (int i = 0; i < 2; i++) {
            images.put("hero_left_prone_" + i, MapleStoryUtil.getImage("hero/left/prone_" + i));
        }
        // hero right jump
        for (int i = 0; i < 2; i++) {
            images.put("hero_right_jump_" + i, MapleStoryUtil.getImage("hero/right/jump_" + i));
        }
        // hero left jump
        for (int i = 0; i < 2; i++) {
            images.put("hero_left_jump_" + i, MapleStoryUtil.getImage("hero/left/jump_" + i));
        }
        // hero right shoot
        for (int i = 0; i < 4; i++) {
            images.put("hero_right_shoot_" + i, MapleStoryUtil.getImage("hero/right/shoot1_" + i));
        }
        // hero left shoot
        for (int i = 0; i < 4; i++) {
            images.put("hero_left_shoot_" + i, MapleStoryUtil.getImage("hero/left/shoot1_" + i));
        }
    }

    /**
     * 根据 Key 获取 Image 对象
     * @param key 键
     * @return Image 对象
     */
    public static Image getKey(String key) {
        return images.get(key);
    }
}

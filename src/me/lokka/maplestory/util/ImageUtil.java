package me.lokka.maplestory.util;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description 项目启动时，一次性将图片从硬盘中读取到JVM中，静态加载图片（从内存中读取）
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 10:00 AM
 */
public class ImageUtil {
    /**
     * 外界无法访问的静态变量容器，用来存储项目中所有图片
     */
    private static final Map<String, List<Image>> images = new HashMap<>();

    static {
        // logo 图片
        List<Image> logo = new CopyOnWriteArrayList<>();
        logo.add(MapleStoryUtil.getImage("icon/logo"));
        images.put("logo", logo);

        // background
        List<Image> background = new CopyOnWriteArrayList<>();
        background.add(MapleStoryUtil.getImage("background/origin"));
        background.add(MapleStoryUtil.getImage("background/background"));
        images.put("background", background);


        /* RedHair Start */
        List<Image> redHair = new CopyOnWriteArrayList<>();

        // hero right stand
        for (int i = 0; i < 4; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/right/stand1_" + i));
        }
        // hero left stand
        for (int i = 0; i < 4; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/left/stand1_" + i));
        }
        // hero right walk
        for (int i = 0; i < 5; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/right/walk1_" + i));
        }
        // hero left walk
        for (int i = 0; i < 5; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/left/walk1_" + i));
        }
        // hero right prone
        for (int i = 0; i < 2; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/right/prone_" + i));
        }
        // hero left prone
        for (int i = 0; i < 2; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/left/prone_" + i));
        }
        // hero right jump
        for (int i = 0; i < 2; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/right/jump_" + i));
        }
        // hero left jump
        for (int i = 0; i < 2; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/left/jump_" + i));
        }
        // hero right shoot
        for (int i = 0; i < 4; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/right/shoot1_" + i));
        }
        // hero left shoot
        for (int i = 0; i < 4; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/left/shoot1_" + i));
        }

        images.put("redhair", redHair);
        /* RedHair End */


        /* Arrow Start */
        List<Image> arrow = new CopyOnWriteArrayList<>();

        // arrow left
        arrow.add(MapleStoryUtil.getImage("arrow/left/0"));
        // arrow right
        arrow.add(MapleStoryUtil.getImage("arrow/right/0"));

        images.put("arrow", arrow);
        /* Arrow End */


        /* Dechick Start */
        List<Image> dechick = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 6; i++) { // de chick left
            dechick.add(MapleStoryUtil.getImage("mob/mob1/left/dechick_" + i));
        }

        images.put("dechick", dechick);
        /* Dechick End */

    }

    /**
     * 根据 Key 获取 Image 对象
     * @param key 键
     * @return List<Image> 对象
     */
    public static List<Image> getValue(String key) {
        return images.get(key);
    }

}

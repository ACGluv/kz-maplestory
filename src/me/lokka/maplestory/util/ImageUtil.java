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
        background.add(MapleStoryUtil.getImage("background/scene"));
        background.add(MapleStoryUtil.getImage("background/background"));
        images.put("background", background);

        /* background element */
//        List<Image> backgroundElement = new CopyOnWriteArrayList<>();
//        for (int i = 1; i < 4; i++) {
//            backgroundElement.add(MapleStoryUtil.getImage("background/bg_layer_" + i));
//        }
//        images.put("bg_element", backgroundElement);
        /* background element */


        /* Ground Start */
        List<Image> ground = new CopyOnWriteArrayList<>();
        ground.add(MapleStoryUtil.getImage("ground/ground_1"));
        ground.add(MapleStoryUtil.getImage("ground/ground_2"));
        ground.add(MapleStoryUtil.getImage("ground/ground_2_right"));
        ground.add(MapleStoryUtil.getImage("ground/ground_3"));
        images.put("ground", ground);
        /* Ground End */


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

        // dechick stand left
        for (int i = 0; i < 6; i++) {
            dechick.add(MapleStoryUtil.getImage("mob/dechick/left/stand_" + i));
        }
        // dechick move left
        for (int i = 0; i < 6; i++) {
            dechick.add(MapleStoryUtil.getImage("mob/dechick/left/move_" + i));
        }
        // dechick hit left
        dechick.add(MapleStoryUtil.getImage("mob/dechick/left/hit1_0"));

        // dechick die left
        for (int i = 0; i < 11; i++) {
            dechick.add(MapleStoryUtil.getImage("mob/dechick/left/die1_" + i));
        }

        images.put("dechick", dechick);
        /* Dechick End */


        /* Item Start */
        List<Image> blood = new CopyOnWriteArrayList<>();
        blood.add(MapleStoryUtil.getImage("item/HP_50"));
        images.put("blood", blood);

        List<Image> mana = new CopyOnWriteArrayList<>();
        mana.add(MapleStoryUtil.getImage("item/MP_50"));
        images.put("mana", mana);
        /* Item End */

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

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

        /* Background Start */
        List<Image> background = new CopyOnWriteArrayList<>();
        background.add(MapleStoryUtil.getImage("background/scene"));
        background.add(MapleStoryUtil.getImage("background/background"));
        background.add(MapleStoryUtil.getImage("background/1410"));
        background.add(MapleStoryUtil.getImage("background/1500"));
        background.add(MapleStoryUtil.getImage("background/810"));
        background.add(MapleStoryUtil.getImage("background/900"));
        background.add(MapleStoryUtil.getImage("background/1020"));
        background.add(MapleStoryUtil.getImage("background/510"));
        background.add(MapleStoryUtil.getImage("background/600"));
        background.add(MapleStoryUtil.getImage("background/720"));
        background.add(MapleStoryUtil.getImage("background/300"));
        background.add(MapleStoryUtil.getImage("background/420"));
        background.add(MapleStoryUtil.getImage("background/1110"));

        images.put("background", background);
        /* Background End */

        /* Common Start */
        List<Image> common = new CopyOnWriteArrayList<>();
        common.add(MapleStoryUtil.getImage("common/blood"));
        images.put("common", common);
        /* Common End */

        /* Number Start */
        List<Image> powerNum = new CopyOnWriteArrayList<>();
        for (int i = 0; i <= 9; i++) {
            powerNum.add(MapleStoryUtil.getImage("num/" + i));
        }
        images.put("powernum", powerNum);
        /* Number End */


        /* Ground Start */
        List<Image> ground = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 7; i++) {
            ground.add(MapleStoryUtil.getImage("ground/ground_" + i));
        }
        images.put("ground", ground);
        /* Ground End */


        /* Rope Start */
        List<Image> rope = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 3; i++) {
            rope.add(MapleStoryUtil.getImage("rope/rope_style_" + i));
        }
        images.put("rope", rope);
        /* Rope End */


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
        // hero climb
        for (int i = 0; i < 2; i++) {
            redHair.add(MapleStoryUtil.getImage("hero/common/climb_" + i));
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
        blood.add(MapleStoryUtil.getImage("item/HP_100"));
        images.put("blood", blood);

        List<Image> mana = new CopyOnWriteArrayList<>();
        mana.add(MapleStoryUtil.getImage("item/MP_50"));
        mana.add(MapleStoryUtil.getImage("item/MP_100"));
        images.put("mana", mana);

        List<Image> commonitem = new CopyOnWriteArrayList<>();
        commonitem.add(MapleStoryUtil.getImage("item/coconut"));
        commonitem.add(MapleStoryUtil.getImage("item/coke"));
        commonitem.add(MapleStoryUtil.getImage("item/icecream"));
        commonitem.add(MapleStoryUtil.getImage("item/juice"));
        commonitem.add(MapleStoryUtil.getImage("item/pearl"));
        commonitem.add(MapleStoryUtil.getImage("item/riceroll"));
        commonitem.add(MapleStoryUtil.getImage("item/soda"));
        images.put("commonitem", commonitem);
        /* Item End */

        /* Item Package Start */
        List<Image> itempackage = new CopyOnWriteArrayList<>();
        itempackage.add(MapleStoryUtil.getImage("itempackage/ItemPackage"));
        images.put("itempackage", itempackage);
        /* Item Package End */


        /**
         * Widget Start
         */

        /* Elin Cave_0 */
        List<Image> elin_cave_0 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            elin_cave_0.add(MapleStoryUtil.getImage("widget/elin/cave_0/" + i));
        }
        images.put("elin_cave_0", elin_cave_0);

        /* Elin Cave_1 */
        List<Image> elin_cave_1 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            elin_cave_1.add(MapleStoryUtil.getImage("widget/elin/cave_1/" + i));
        }
        images.put("elin_cave_1", elin_cave_1);

        /* Elin Fairy Field Acc_1 */
        List<Image> fairy_field_acc_1 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 6; i++) {
            fairy_field_acc_1.add(MapleStoryUtil.getImage("widget/elin/fairyfieldacc_1/" + i));
        }
        images.put("fairy_field_acc_1", fairy_field_acc_1);

        /* Elin Fairy Field Acc_6 */
        List<Image> fairy_field_acc_6 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 8; i++) {
            fairy_field_acc_6.add(MapleStoryUtil.getImage("widget/elin/fairyfieldacc_6/" + i));
        }
        images.put("fairy_field_acc_6", fairy_field_acc_6);

        /* Elin Fairy Field Acc_16 */
        List<Image> fairy_field_acc_16 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            fairy_field_acc_16.add(MapleStoryUtil.getImage("widget/elin/fairyfieldacc_16/" + i));
        }
        images.put("fairy_field_acc_16", fairy_field_acc_16);

        /* Elin Fairy Boss_1 */
        List<Image> fairy_boss_1 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            fairy_boss_1.add(MapleStoryUtil.getImage("widget/elin/fairyboss_1/" + i));
        }
        images.put("fairy_boss_1", fairy_boss_1);

        /**
         * Widget End
         */

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

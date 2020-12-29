package me.lokka.maplestory.client;

import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.entity.*;
import me.lokka.maplestory.util.ImageUtil;
import me.lokka.maplestory.util.MusicThread;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description 项目运行的主类
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 10:09 AM
 */
public class MapleStoryClient extends MapleStoryFrame {

    @Override
    public void loadFrame() {
        super.loadFrame();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                hero.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                hero.keyReleased(e);
            }
        });
    }

    /**
     * 英雄
     */
    public Hero hero = new Hero(this, ImageUtil.getValue("redhair"), 300, 725);
    /**
     * 背景图片
     */
    public Background bg = new Background(this, ImageUtil.getValue("background").get(0));

    /**
     * 初始化 Ground 容器
     */
    public List<Ground> groundList = new CopyOnWriteArrayList<>();
    {
        List<Image> groundImages = ImageUtil.getValue("ground");

        Ground ground_1 = new Ground(this, groundImages.get(0), 0, 1698);
        groundList.add(ground_1);

//        int xx = 824, yy = 1650;
//        for (int i = 0; i < 13; i++) {
//            Ground ground_tmp = new Ground(this, xx + i * 200, yy - i * 50, 200, 81);
//            groundList.add(ground_tmp);
//        }

//        xx = 1524;
//        yy = 1400;
//        for (int i = 0; i < 6; i++) {
//            Ground ground_tmp = new Ground(this, xx - i * 200, yy - i * 50, 200, 81);
//            groundList.add(ground_tmp);
//        }

        Ground ground_2 = new Ground(this, groundImages.get(1), -200, 1398);
        groundList.add(ground_2);

        Ground ground_2_mid = new Ground(this, groundImages.get(5), 1205, 1390);
        groundList.add(ground_2_mid);

        Ground ground_2_right = new Ground(this, groundImages.get(4), 2808, 1339);
        groundList.add(ground_2_right);

        Ground ground_3 = new Ground(this, groundImages.get(3), 324, 1085);
        groundList.add(ground_3);

        Ground ground_3_mid = new Ground(this, groundImages.get(5), 1321, 1050);
        groundList.add(ground_3_mid);

        Ground ground_3_right = new Ground(this, groundImages.get(2), 2440, 1046);
        groundList.add(ground_3_right);

        Ground ground_stair_1 = new Ground(this, groundImages.get(6), 2163, 945);
        groundList.add(ground_stair_1);
        Ground ground_stair_2 = new Ground(this, groundImages.get(6), 2189, 1121);
        groundList.add(ground_stair_2);
        Ground ground_stair_3 = new Ground(this, groundImages.get(6), 2371, 1214);
        groundList.add(ground_stair_3);
        Ground ground_stair_4 = new Ground(this, groundImages.get(6), 2330, 1331);
        groundList.add(ground_stair_4);
        Ground ground_stair_5 = new Ground(this, groundImages.get(6), 3070, 1174);
        groundList.add(ground_stair_5);
    }

    /**
     * 初始化 Rope 容器
     */
    public List<Rope> ropeList = new CopyOnWriteArrayList<>();
    {
        List<Image> ropeImages = ImageUtil.getValue("rope");

        Rope rope_01_01 = new Rope(this, ropeImages.get(0), 573, 1400);
        ropeList.add(rope_01_01);

        Rope rope_01_02 = new Rope(this, ropeImages.get(0), 930, 1400);
        ropeList.add(rope_01_02);

        Rope rope_02_01 = new Rope(this, ropeImages.get(1), 758, 1085);
        ropeList.add(rope_02_01);

        Rope rope_02_02 = new Rope(this, ropeImages.get(2), 2686, 1046);
        ropeList.add(rope_02_02);
    }

    /**
     * 箭矢的容器
     */
    public List<Arrow> arrowList = new CopyOnWriteArrayList<>();
    /**
     * 怪物的容器
     */
    public List<Mob> mobList = new CopyOnWriteArrayList<>();

    public List<Item> itemList = new CopyOnWriteArrayList<>();

    /**
     * 初始化 Mob 容器
     */
    {
        for (int i = 0; i < 2; i++) {
            Mob mob = new Mob(this, ImageUtil.getValue("dechick"), i * 100 + 400, 1624);
            mobList.add(mob);
        }
    }

    /**
     * 地图装饰物容器
     */
    public List<Widget> widgetList = new CopyOnWriteArrayList<>();
    {
        widgetList.add(new Widget(this, ImageUtil.getValue("elin_cave_0"), 633, 1315, 3));
        widgetList.add(new Widget(this, ImageUtil.getValue("elin_cave_1"), 702, 1243, 3));
        widgetList.add(new Widget(this, ImageUtil.getValue("fairy_field_acc_6"), 215, 1165, 5));
    }

    /**
     * 伤害数字
     */
    public List<Power> powerList = new CopyOnWriteArrayList<>();

    public ItemPackage itemPackage = new ItemPackage(this, 1300, 300);

    @Override
    public void paint(Graphics g) {
        Image canvas = ImageUtil.getValue("background").get(1);
        g.drawImage(canvas, 0, 0, null);

        bg.draw(g);

        for (Widget widget : widgetList) {
            widget.draw(g);
        }

        for (Ground ground : groundList) {
            ground.draw(g);
        }

        for (Rope rope : ropeList) {
            rope.draw(g);
        }

        Font f = g.getFont();
        Color c = g.getColor();
        g.setFont(new Font("Times New Roman", Font.BOLD, 23));
        g.setColor(Color.WHITE);
        g.drawString("Hero.x: " + hero.x, 200, 120);
        g.drawString("Hero.y: " + hero.y, 200, 150);
        g.drawString("Hero.action: " + hero.action, 200, 180);
        g.drawString("Hero.jump: " + hero.jump, 200, 210);
        g.drawString("Hero.drop: " + hero.drop, 200, 240);
        g.drawString("Ground1.x: " + (bg.x + groundList.get(0).x), 200, 270);
        g.drawString("Ground1.y: " + (bg.y + groundList.get(0).y), 200, 300);
        g.drawLine(Constant.GAME_WIDTH / 2, 0, Constant.GAME_WIDTH / 2, Constant.GAME_HEIGHT);
        g.setFont(f);
        g.setColor(c);

        for (Mob mob : mobList) {
            mob.draw(g);
        }

        for (Arrow arrow : arrowList) {
            arrow.draw(g);
            arrow.hit(mobList);
        }

        for (Item item : itemList) {
            item.draw(g);
        }

        for (Power power : powerList) {
            power.draw(g);
        }

        hero.draw(g);

        itemPackage.draw(g);
    }

    public static void main(String[] args) {
        new MapleStoryClient().loadFrame();
        new MusicThread("bgm.mp3", true).start();
    }
}

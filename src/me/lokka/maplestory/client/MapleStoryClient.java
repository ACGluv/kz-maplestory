package me.lokka.maplestory.client;

import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.entity.*;
import me.lokka.maplestory.util.ImageUtil;

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
     * 背景图片
     */
    public Background bg = new Background(this, ImageUtil.getValue("background").get(0));
    /**
     * 英雄
     */
    public Hero hero = new Hero(this, ImageUtil.getValue("redhair"), 300, 525);
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
     * 初始化 Mobs 容器
     */
    {
        for (int i = 0; i < 5; i++) {
            Mob mob = new Mob(this, ImageUtil.getValue("dechick"), i * 100 + 400, 1524);
            mobList.add(mob);
        }
    }

    public List<Ground> groundList = new CopyOnWriteArrayList<>();
    {
        Ground ground_1 = new Ground(this, ImageUtil.getValue("ground").get(0), 0, 1598);
        groundList.add(ground_1);
        Ground ground_2 = new Ground(this, ImageUtil.getValue("ground").get(1), 0, 1298);
        groundList.add(ground_2);
        Ground ground_2_right = new Ground(this, ImageUtil.getValue("ground").get(2), 2440, 946);
        groundList.add(ground_2_right);
        Ground ground_3 = new Ground(this, ImageUtil.getValue("ground").get(3), 824, 985);
        groundList.add(ground_3);
    }

    @Override
    public void paint(Graphics g) {
        Image canvas = ImageUtil.getValue("background").get(1);
        g.drawImage(canvas, 0, Constant.GAME_HEIGHT - canvas.getHeight(null), null);
        bg.draw(g);

        for (Ground ground : groundList) {
            ground.draw(g);
        }

        Font f = g.getFont();
        g.setFont(new Font("Times New Roman", Font.BOLD, 23));
        g.setColor(Color.WHITE);
//        g.drawString("Hero.x: " + hero.x, 200, 120);
//        g.drawString("Hero.y: " + hero.y, 200, 150);
//        g.drawString("Hero.action: " + hero.action, 200, 180);
//        g.drawString("Background.x: " + bg.x, 200, 210);
//        g.drawString("Item.size: " + itemList.size(), 200, 240);
        g.drawLine(Constant.GAME_WIDTH / 2, 0, Constant.GAME_WIDTH / 2, Constant.GAME_HEIGHT);
//        g.setFont(f);

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

        hero.draw(g);
    }

    public static void main(String[] args) {
        new MapleStoryClient().loadFrame();
    }
}

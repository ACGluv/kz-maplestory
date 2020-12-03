package me.lokka.maplestory.client;

import me.lokka.maplestory.entity.Arrow;
import me.lokka.maplestory.entity.Background;
import me.lokka.maplestory.entity.Hero;
import me.lokka.maplestory.entity.MapleStoryFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

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
    public Background background = new Background(this, "background", 0, 0);
    /**
     * 英雄
     */
    public Hero hero = new Hero(this, 300, 700);
    /**
     * 箭矢
     */
    public List<Arrow> arrowList = new CopyOnWriteArrayList<>();

    @Override
    public void paint(Graphics g) {
        //background.draw(g);
        Font f = g.getFont();
        g.setFont(new Font("Times New Roman", Font.BOLD, 23));
        g.setColor(Color.BLACK);
        g.drawString("Hero.y: " + hero.y, 200, 150);
        g.drawString("Hero.action: " + hero.action, 200, 180);
        g.drawString("Arrow.size " + arrowList.size(), 200, 210);
        g.setFont(f);
        hero.draw(g);
        for (Arrow arrow : arrowList) {
            arrow.draw(g);
        }
    }

    public static void main(String[] args) {
        new MapleStoryClient().loadFrame();
    }
}

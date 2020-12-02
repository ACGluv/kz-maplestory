package me.lokka.maplestory.client;

import me.lokka.maplestory.entity.Background;
import me.lokka.maplestory.entity.Hero;
import me.lokka.maplestory.entity.MapleStoryFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    public Hero hero = new Hero(this, 300, 700);

    @Override
    public void paint(Graphics g) {
        //background.draw(g);
        Font f = g.getFont();
        g.setFont(new Font("Times New Roman", Font.BOLD, 23));
        g.setColor(Color.BLACK);
        g.drawString("Hero.y: " + hero.y, 200, 150);
        g.drawString("Hero.left: " + hero.left, 200, 180);
        g.drawString("Hero.right: " + hero.right, 200, 210);
        g.drawString("Hero.jump: " + hero.jump, 200, 240);
        g.drawString("Hero.prone: " + hero.prone, 200, 270);
        g.drawString("Hero.shoot: " + hero.shoot, 200, 300);
        g.setFont(f);
        hero.draw(g);
    }

    public static void main(String[] args) {
        new MapleStoryClient().loadFrame();
    }
}

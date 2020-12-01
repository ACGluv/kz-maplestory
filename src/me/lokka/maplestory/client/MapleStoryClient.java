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
        hero.draw(g);
        Font f = g.getFont();
        g.setFont(new Font("Times New Roman", Font.BOLD, 18));
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.setColor(c);
        g.setFont(f);
    }

    public static void main(String[] args) {
        new MapleStoryClient().loadFrame();
    }
}

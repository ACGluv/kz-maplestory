package me.lokka.maplestory.entity;

import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.util.ImageUtil;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description 冒险岛项目的窗口的父类
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 9:44 AM
 */
public class MapleStoryFrame extends Frame {
    /**
     * 加载窗口的方法
     */
    public void loadFrame() {
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        // 窗口的 LOGO
        this.setIconImage(ImageUtil.getKey("logo"));
        this.setTitle(Constant.TITLE);
        // 相对于屏幕水平垂直居中
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        // 窗口的不可改变大小
        this.setResizable(false);

        // 窗口关闭的方法
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 启动重画线程
        new MapleStoryThread().start();
    }

    /**
     * 重画线程内部类
     */
    class MapleStoryThread extends Thread {

        @Override
        public void run() {
            for (; ; ) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
     */
    Image backImg = null;

    /**
     * 重写update()方法，在窗口的里层添加一个虚拟的图片
     */
    @Override
    public void update(Graphics g) {
        if (backImg == null) {
            // 如果虚拟图片不存在，创建一个和窗口一样大小的图片
            backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        }
        // 获取到虚拟图片的画笔
        Graphics backg = backImg.getGraphics();
        Color c = backg.getColor();
        backg.setColor(Color.WHITE);
        backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        backg.setColor(c);
        // 调用虚拟图片的paint()方法，每50ms刷新一次
        paint(backg);
        g.drawImage(backImg, 0, 0, null);
    }
}



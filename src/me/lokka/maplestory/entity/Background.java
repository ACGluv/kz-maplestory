package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.util.ImageUtil;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.util.List;

/**
 * @Description 背景图片类
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 10:45 AM
 */
public class Background extends AbstractMapleStoryObject{

    BgElement treeLyr01_1 = null;
    BgElement treeLyr01_2 = null;
    BgElement treeLyr02_1 = null;
    BgElement treeLyr02_2 = null;
    BgElement treeLyr02_3 = null;
    BgElement treeLyr03_1 = null;
    BgElement treeLyr03_2 = null;
    BgElement treeLyr03_3 = null;
    BgElement treeLyr04_1 = null;
    BgElement treeLyr04_2 = null;
    BgElement edgeLight = null;

    private boolean moving = false;

    public Background() {
        this.speed = Constant.HERO_SPEED;
    }

    public Background(MapleStoryClient msc, Image img) {
        this();
        this.msc = msc;
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.x = 0;
        this.y = Constant.GAME_HEIGHT - this.height;
        this.dir = Direction.LEFT;
        this.action = Action.STAND;

        this.imgs = ImageUtil.getValue("background");
        treeLyr01_1 = new BgElement(this.msc, imgs.get(2), -50, 0, 2, 1000,-7, true);
        treeLyr01_2 = new BgElement(this.msc, imgs.get(3), 400, 0, 2, 1000,-7, true);
        treeLyr02_1 = new BgElement(this.msc, imgs.get(4), 0, 0, 3, 800, -8, true);
        treeLyr02_2 = new BgElement(this.msc, imgs.get(5), 240, 0, 2, 800, -8, true);
        treeLyr02_3 = new BgElement(this.msc, imgs.get(6), 490, 0, 3, 800, -8, true);
        treeLyr03_1 = new BgElement(this.msc, imgs.get(7), 70, 0, 4, 450, -9, true);
        treeLyr03_2 = new BgElement(this.msc, imgs.get(8), 280, 0, 4, 450, -9, true);
        treeLyr03_3 = new BgElement(this.msc, imgs.get(9), 400, 0, 4, 450, -9, true);
        treeLyr04_1 = new BgElement(this.msc, imgs.get(10), 385, 0, 3, 450, -9, true);
        treeLyr04_2 = new BgElement(this.msc, imgs.get(11), 85, 0, 3, 450, -9, true);
        edgeLight = new BgElement(this.msc, imgs.get(12), 0, 705, 50, 50, -10, false);
    }

    @Override
    public void move() {
        switch (msc.hero.dir) {
            case LEFT:
                if (msc.hero.moving
                        && msc.hero.x < Constant.GAME_WIDTH / 2 - msc.hero.width / 2
                        && x < 0
                ) {
                    x += speed;
                    msc.hero.speed = 0;
                    moving = true;
                } else {
                    msc.hero.speed = Constant.HERO_SPEED;
                    moving = false;
                }
                break;
            case RIGHT:
                if (msc.hero.moving
                        && msc.hero.x > Constant.GAME_WIDTH / 2 - msc.hero.width / 2
                        && x + width > Constant.GAME_WIDTH
                ) {
                    x -= speed;
                    msc.hero.speed = 0;
                    moving = true;
                } else {
                    msc.hero.speed = Constant.HERO_SPEED;
                    moving = false;
                }
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        treeLyr04_1.draw(g);
        treeLyr04_2.draw(g);
        treeLyr03_1.draw(g);
        treeLyr03_2.draw(g);
        treeLyr03_3.draw(g);
        treeLyr02_1.draw(g);
        treeLyr02_2.draw(g);
        treeLyr02_3.draw(g);
        treeLyr01_1.draw(g);
        treeLyr01_2.draw(g);
//        edgeLight.draw(g);
        g.drawImage(img, x, y + 100, null);
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }

    class BgElement extends AbstractMapleStoryObject {

        /**
         * 元素的数量
         */
        private int num;
        /**
         * 相同元素之间的间隔
         */
        private int margin;
        /**
         * 元素竖直方向复制
         */
        private boolean duplicate;

        public BgElement(MapleStoryClient msc, Image img, int x, int y, int num, int margin, int speed, boolean duplicate) {
            this.msc = msc;
            this.img = img;
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);

            this.x = x;
            this.y = y;
            this.num = num;
            this.margin = margin;
            this.speed = speed;

            this.duplicate = duplicate;
        }

        @Override
        public void move() {
            switch (msc.hero.dir) {
                case LEFT:
                    if (msc.hero.moving
                        && msc.bg.moving
                        && msc.hero.x < Constant.GAME_WIDTH / 2 - msc.hero.width / 2
                        ) {
                        x += speed;
                    }
                    break;
                case RIGHT:
                    if (msc.hero.moving
                        && msc.bg.moving
                        && msc.hero.x > Constant.GAME_WIDTH / 2 - msc.hero.width / 2
                        ) {
                        x -= speed;
                    }
                    break;
            }
        }

        @Override
        public void draw(Graphics g) {
            move();
            int n = 1;
            if (duplicate) {
                n = Constant.GAME_HEIGHT / height + 1;
            }
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < n; j++) {
                    g.drawImage(img, msc.bg.x + x + i * margin, y + j * height, null);
                }
            }
        }
    }

//    class DynamicElement extends AbstractMapleStoryObject {
//
//        /**
//         * 图片切换速率
//         */
//        private int rate = 1;
//
//        private void calcStep() {
//            // 图片切换速度
//            switch (action) {
//                case STAND:
//                    if (cnt++ % 3 == 0) step++;
//                    break;
//                case DIE:
//                    if (cnt++ % 2 == 0) step++;
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        private int cnt = 0, step = -1;
//
//        @Override
//        public void move() {
//            if (msc.hero.moving) {
//                switch (msc.hero.dir) {
//                    case LEFT:
//                        if (msc.hero.x <= Constant.GAME_WIDTH / 2 - msc.hero.width / 2) {
//                            x += speed;
//                        }
//                        break;
//                    case RIGHT:
//                        if (msc.hero.x >= Constant.GAME_WIDTH / 2 - msc.hero.width / 2) {
//                            x -= speed;
//                        }
//                        break;
//                }
//            }
//        }
//
//        @Override
//        public void draw(Graphics g) {
//            move();
//            if (imgs != null) {
//                if (cnt++ % rate == 0) {
//                    step++;
//                    cnt = 1;
//                }
//                img = imgs.get(step % imgs.size());
//            }
//            g.drawImage(img, x, y, null);
//        }
//    }
}

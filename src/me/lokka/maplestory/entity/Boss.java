package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.util.ImageUtil;
import me.lokka.maplestory.util.MusicThread;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @Description BOSS类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/30 11:55 AM
 */
public class Boss extends Mob {

    /**
     * 最大血量
     */
    public static final int MAX_HP = (int) 1e6;

    public boolean underAttack = false;

    private Map<String, List<Image>> imgDict;

    public Boss(Map<String, List<Image>> imgDict) {
        this.imgDict = imgDict;
        this.width = imgDict.get("left_stand").get(0).getWidth(null);
        this.height = imgDict.get("left_stand").get(0).getHeight(null);
        this.x = 500;
        this.y = 700;
        this.HP = MAX_HP;
        this.speed = 3;
        this.left = true;
    }

    public Boss(MapleStoryClient msc, Map<String, List<Image>> imgDict, int x, int y) {
        this(imgDict);
        this.msc = msc;
        this.x = x;
        this.y = y;
        this.origin_y_down = this.y + this.height;
        this.dir = Direction.LEFT;
        this.action = Action.STAND;
    }

    public Boss(MapleStoryClient msc, Map<String, List<Image>> imgDict, int x, int y, String name, int level, int HP, int MP, int EXP, int speed) {
        this(msc, imgDict, x, y);
        this.msc = msc;
        this.name = name;
        this.level = level;
        this.HP = HP;
        this.MP = MP;
        this.EXP = EXP;
        this.speed = speed;
    }

    private int cnt = 0, step = -1;
    private void calcStep() {
        // 图片切换速度
        switch (action) {
            case WALK:
            case STAND:
            case DIE:
                if (cnt++ % 3 == 0) step++;
                break;
            case HIT:
                if (cnt++ % 7 == 0) {
                    action = Action.STAND;
                    step++;
                }
                break;
            case ATTACK:
                if (cnt++ % 3 == 0) step++;
                break;
            default:
                break;
        }
    }

    private void outOfBounds() {
        if (x <= 0) {
            x = 0;
            if (left) {
                left = false;
                right = true;
            }
        }
        if (x >= msc.bg.width - this.width) {
            x = msc.bg.width - this.width;
            if (right) {
                left = true;
                right = false;
            }
        }
    }

    private boolean flag = false;
    public boolean hit, left, right, attacking;

    public int die_x_right, origin_y_down;

    Direction[] dirs = {Direction.RIGHT, Direction.LEFT};
    Action[] actions = {Action.WALK, Action.STAND};

    public int attack_cnt = 0;
    @Override
    public void move() {
        // 修改当前怪物的状态，包括方向、动作
        if (!underAttack) {
            if (random.nextInt(1000) > 900) {
                dir = dirs[random.nextInt(dirs.length)];
                Action tmp_action = actions[random.nextInt(actions.length)];
                if (tmp_action != action) {
                    cnt = 0;
                    step = -1;
                }
                action = tmp_action;
            }
        } else {
            if (msc.hero.x > x + msc.bg.x) {
                dir = Direction.RIGHT;
            } else {
                dir = Direction.LEFT;
            }

            if (Math.abs(msc.hero.x + msc.hero.width / 2 - (x + msc.bg.x + width / 2)) > 300 && !attacking) {
                action = Action.WALK;
                attack_cnt = 0;
            } else {
                if (!attacking) {
                    action = Action.STAND;
                }
                if (attack_cnt++ % 120 == 0) {
                    action = Action.ATTACK;
                    attacking = true;
                    cnt = 0;
                    step = -1;
                    new MusicThread("mossysnail/Attack1.mp3", false).start();
                }
            }
        }

        if (!live) {
            if (!flag) {
                cnt = 0;
                step = -1;
                flag = true;
                die_x_right = x + width;
            }
            action = Action.DIE;
            return;
        }

        if (hit) {
            cnt = 1;
            step = 0;
            action = Action.HIT;
            hit = false;
        }

        switch (action) {
            case WALK :
                switch (dir) {
                    case LEFT -> x -= speed;
                    case RIGHT -> x += speed;
                }
                break;
            default:
                break;
        }

        outOfBounds();
    }

    private void drawBloodBar(Graphics g) {
        Image bg = ImageUtil.getValue("common").get(1);
        int bgWidth = bg.getWidth(null);
        int bgX = Constant.GAME_WIDTH / 2 - bgWidth / 2;
        int bgY = 100;

        g.drawImage(bg, bgX, bgY, null);

        Image blk = ImageUtil.getValue("common").get(2);
        int blkWidth = (int) (blk.getWidth(null) * 1.2);
        int blkHeight = (int) (blk.getHeight(null) * 1.2);

        for (int i = 0; i <= (bgWidth / blkWidth) * HP / MAX_HP - 8; i++) {
            g.drawImage(blk, bgX + 3 + i * blkWidth, bgY + 3, blkWidth, blkHeight, null);
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        calcStep();
        int len, idx;
        switch (dir) {
            case LEFT:
                switch (action) {
                    case STAND:
                        len = imgDict.get("left_stand").size();
                        img = imgDict.get("left_stand").get(step % len);
                        break;
                    case WALK:
                        len = imgDict.get("left_move").size();
                        img = imgDict.get("left_move").get(step % len);
                        break;
                    case HIT:
                        img = imgDict.get("left_hit").get(0);
                        break;
                    case DIE:
                        len = imgDict.get("left_die").size();
                        idx = step % (len + 1);
                        if (idx == len) {
//                            new MusicThread("boss/Die.mp3", false).start();
                            msc.mobList.remove(this);
                            return;
                        }
                        img = imgDict.get("left_die").get(idx);
                        break;
                    case ATTACK:
                        len = imgDict.get("left_attack_2").size();
                        idx = step % (len + 1);
                        if (idx == len) {
                            action = Action.STAND;
                            attacking = false;
                            img = imgDict.get("left_stand").get(0);
                            break;
                        } else if (idx == len / 2) {
                            if (getAttackRange().intersects(msc.hero.getRectangle())) {
                                msc.hitList.add(new Hit(msc, imgDict.get("hit_attack_2"), msc.hero.x - 12, msc.hero.y - 20, 2));
                                int dmg = (int) (random.nextDouble() * 50000 + 100000);
                                msc.hero.HP -= dmg;
                            }
                        }
                        img = imgDict.get("left_attack_2").get(idx);
                        break;
                    default:
                        break;
                }
                break;
            case RIGHT:
                switch (action) {
                    case STAND:
                        len = imgDict.get("right_stand").size();
                        img = imgDict.get("right_stand").get(step % len);
                        break;
                    case WALK:
                        len = imgDict.get("right_move").size();
                        img = imgDict.get("right_move").get(step % len);
                        break;
                    case HIT:
                        img = imgDict.get("right_hit").get(0);
                        break;
                    case DIE:
                        len = imgDict.get("right_die").size();
                        idx = step % (len + 1);
                        if (idx == len) {
//                            new MusicThread("boss/Die.mp3", false).start();
                            msc.mobList.remove(this);
                            return;
                        }
                        img = imgDict.get("right_die").get(idx);
                        break;
                    case ATTACK:
                        len = imgDict.get("right_attack_2").size();
                        idx = step % (len + 1);
                        if (idx == len) {
                            action = Action.STAND;
                            attacking = false;
                            img = imgDict.get("right_stand").get(0);
                            break;
                        } else if (idx == len / 2) {
                            if (getAttackRange().intersects(msc.hero.getRectangle())) {
                                msc.hitList.add(new Hit(msc, imgDict.get("hit_attack_2"), msc.hero.x - 12, msc.hero.y - 20, 2));
                                int dmg = (int) (random.nextDouble() * 50000 + 100000);
                                msc.hero.HP -= dmg;
                            }
                        }
                        img = imgDict.get("right_attack_2").get(idx);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        if (!live) {
            if (dir == Direction.LEFT) {  // 更新右下角 x轴
                die_x_right = Math.max(die_x_right, x + img.getWidth(null));
            }
            g.drawImage(
                img,
                msc.bg.x + die_x_right - img.getWidth(null),
                msc.bg.y + origin_y_down - img.getHeight(null),
                null
            );
        } else {
            if (action == Action.ATTACK) {
                if (dir == Direction.LEFT) {
                    g.drawImage(
                        img,
                        msc.bg.x + x - 150,
                        msc.bg.y + y - 100,
                        null
                    );
                } else {
                    g.drawImage(
                        img,
                        msc.bg.x + x - 120,
                        msc.bg.y + y - 100,
                        null
                    );
                }
            } else {
                g.drawImage(
                    img,
                    msc.bg.x + x,
                    msc.bg.y + origin_y_down - img.getHeight(null),
                    null
                );
            }
            g.drawRect(msc.bg.x + x + width / 2 - 300, msc.bg.y + y + height / 2 - 200, 600, 400);
        }
        if (HP <= MAX_HP && live) {
            drawBloodBar(g);
            underAttack = true;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(msc.bg.x + x, msc.bg.y + y, width, height);
    }

    public Rectangle getAttackRange() {
        return new Rectangle(msc.bg.x + x + width / 2 - 300, msc.bg.y + y + height / 2 - 200, 600, 400 );
    }
}

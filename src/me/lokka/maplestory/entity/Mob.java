package me.lokka.maplestory.entity;

import me.lokka.maplestory.client.MapleStoryClient;
import me.lokka.maplestory.constant.Constant;
import me.lokka.maplestory.util.ImageUtil;
import me.lokka.maplestory.util.MusicThread;

import java.awt.*;
import java.util.List;

/**
 * @Description 怪物类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/4 8:29 AM
 */
public class Mob extends AbstractMapleStoryObject {

    /**
     * 最大血量
     */
    public static final int MAX_HP = (int) 1e6;

    public boolean underAttack = false;

    public Mob(List<Image> imgs) {
        this.imgs = imgs;
        this.width = imgs.get(0).getWidth(null);
        this.height = imgs.get(0).getHeight(null);
        this.x = 500;
        this.y = 700;
        this.HP = MAX_HP;
        this.speed = 3;
        this.left = true;
    }

    public Mob(MapleStoryClient msc, List<Image> imgs, int x, int y) {
        this(imgs);
        this.msc = msc;
        this.x = x;
        this.y = y;
        this.origin_y_down = this.y + this.height;
        this.dir = Direction.LEFT;
        this.action = Action.STAND;
    }

    public Mob(MapleStoryClient msc, List<Image> imgs, int x, int y, String name, int level, int HP, int MP, int EXP, int speed) {
        this(msc, imgs, x, y);
        this.msc = msc;
        this.name = name;
        this.level = level;
        this.HP = HP;
        this.MP = MP;
        this.EXP = EXP;
        this.speed = speed;
    }

    private int cnt = 0, step = -1;
    public int die_x_right, origin_y_down;
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
        if (x >= 1800) {
            x = 1800;
            if (right) {
                left = true;
                right = false;
            }
        }
    }

    private boolean flag = false;
    public boolean hit, left, right, attacking;
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

            if (Math.abs(msc.hero.x + msc.hero.width / 2 - (x + msc.bg.x + width / 2)) > 200 && !attacking) {
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
        Image bloodBlk = ImageUtil.getValue("common").get(0);
        int bloodBlkWidth = bloodBlk.getWidth(null);
        for (int i = 0; i <= (width / bloodBlkWidth - 1) * HP / MAX_HP; i++) {
            g.drawImage(bloodBlk, msc.bg.x + x + i * bloodBlkWidth, msc.bg.y + y - 12 , null);
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        calcStep();
        int idx = 0; // 图片序号
        switch (dir) {
            case LEFT:
                switch (action) {
                    case STAND:
                        idx = step % 6;
                        break;
                    case WALK:
                        idx = step % 12 + 12;
                        break;
                    case HIT:
                        idx = 36;
                        break;
                    case DIE:
                        idx = step % 8 + 38;
                        if (idx == 38) {
                            new MusicThread("mossysnail/Die.mp3", false).start();
                        }
                        if (idx == 45) {
                            msc.mobList.remove(this);
                            return;
                        }
                        break;
                    case ATTACK:
                        idx = step % 23 + 52;
                        if (idx == 74) {
                            action = Action.STAND;
                            attacking = false;
                            return;
                        } else if (idx == 52 + 23 / 2) {
                            if (getAttackRange().intersects(msc.hero.getRectangle())) {
                                msc.hero.HP -= random.nextInt(50000);
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case RIGHT:
                switch (action) {
                    case STAND:
                        idx = step % 6 + 6;
                        break;
                    case WALK:
                        idx = step % 12 + 24;
                        break;
                    case HIT:
                        idx = 37;
                        break;
                    case DIE:
                        idx = step % 8 + 45;
                        if (idx == 45) {
                            new MusicThread("mossysnail/Die.mp3", false).start();
                        }
                        if (idx == 52) {
                            msc.mobList.remove(this);
                            return;
                        }
                        break;
                    case ATTACK:
                        idx = step % 23 + 74;
                        if (idx == 96) {
                            action = Action.STAND;
                            attacking = false;
                            return;
                        } else if (idx == 74 + 23 / 2) {
                            if (getAttackRange().intersects(msc.hero.getRectangle())) {
                                msc.hero.HP -= random.nextInt(1000000);
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        img = imgs.get(idx);
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
//            if (action == Action.ATTACK) {
//                g.drawImage(
//                        img,
//                        msc.bg.x + x,
//                        msc.bg.y + y,
//                        null
//                );
//            } else {
                g.drawImage(
                        img,
                        msc.bg.x + x,
                        msc.bg.y + origin_y_down - img.getHeight(null),
                        null
                );
//                g.drawRect(msc.bg.x + x + width / 2 - 200, msc.bg.y + y + height / 2 - 100, 400, 200);
//            }
        }
        if (HP < MAX_HP && live) {
            drawBloodBar(g);
            underAttack = true;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(msc.bg.x + x, msc.bg.y + y, width, height);
    }

    public Rectangle getAttackRange() {
        return new Rectangle(msc.bg.x + x + width / 2 - 200, msc.bg.y + y + height / 2 - 100, 400, 200 );
    }
}

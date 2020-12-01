package me.lokka.maplestory.util;

import me.lokka.maplestory.constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @Description 加载图片的工具类（从硬盘加载到内存
 * @Author: kainzhang (张健)
 * @Date: 2020/11/30 9:54 AM
 */
public class MapleStoryUtil {
    /**
     * 通过图片名获取图片对象
     * @param imgName 图片路径名（去掉前缀和扩展名
     * @return
     */
    public static Image getImage(String imgName) {
        URL url = MapleStoryUtil.class.getClassLoader().getResource(Constant.IMG_PRE + imgName + Constant.IMG_POST);
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}

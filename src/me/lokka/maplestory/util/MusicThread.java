package me.lokka.maplestory.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import me.lokka.maplestory.constant.Constant;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

/**
 * @Description 音乐类
 * @Author: kainzhang (张健)
 * @Date: 2020/12/18 8:42 AM
 */
public class MusicThread extends Thread {
    /**
     *  播放器类
     */
    private Player player;
    /**
     * MP3文件的路径
     */
    private String musicPath;
    /**
     * 是否循环
     */
    private boolean loop;
    public MusicThread(){

    }

    /**
     *
     * @param musicName MP3文件的相对路径
     */
    public MusicThread(String musicName){
        this.musicPath = Constant.AUD_PRE+musicName;
    }

    /**
     *
     * @param musicName MP3文件的相对路径
     * @param loop 是否循环
     */
    public MusicThread(String musicName,boolean loop){
        this.musicPath = Constant.AUD_PRE + musicName;
        this.loop = loop;
    }

    public void run(){
        try {
            if (loop){
                while (true){
                    play();
                }
            }else{
                play();
            }
        }catch (FileNotFoundException | JavaLayerException e){
            e.printStackTrace();
        }
    }

    private void play() throws FileNotFoundException,JavaLayerException{
        BufferedInputStream buffer = new BufferedInputStream(MusicThread.class.getClassLoader().getResourceAsStream(musicPath));
        // 创建播放器类
        player = new Player(buffer);
        // 播放
        player.play();
    }

//    public static void main(String[] args) {
//        new MusicThread("bgm.mp3",true).start();
//    }
}

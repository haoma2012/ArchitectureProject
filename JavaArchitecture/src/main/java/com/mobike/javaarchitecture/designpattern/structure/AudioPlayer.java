package com.mobike.javaarchitecture.designpattern.structure;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午5:11
 */
public class AudioPlayer implements MediaPlayer {

    private Adapter mAdapter;

    @Override
    public void play(String audioType, String fileName) {
        //播放 mp3 音乐文件的内置支持
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }
        //mediaAdapter 提供了播放其他文件格式的支持
        else if (audioType.equalsIgnoreCase("vlc")
                || audioType.equalsIgnoreCase("mp4")) {
            mAdapter = new Adapter(audioType);
            mAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " +
                    audioType + " format not supported");
        }

    }
}

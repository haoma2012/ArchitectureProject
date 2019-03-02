package com.mobike.javaarchitecture.designpattern.structure;

/**
 * 适配器模式(Adapter Pattern) ：
 * 将一个接口转换成客户希望的另一个接口，适配器模式使接口不兼容的那些类可以一起工作，
 * 其别名为包装器(Wrapper)。
 * 适配器模式既可以作为类结构型模式，也可以作为对象结构型模式。
 *
 * 优点：
 * 提高了类的复用性，适配器能让一个类有更广泛的用途。
 * 提高了灵活性，更换适配器就能达到不同的效果。不用时也可以随时删掉适配器，对原系统没影响。
 * 符合开放封闭原则，不用修改原有代码。没有任何关系的类通过增加适配器就能联系在一起。
 *
 * 缺点：
 * 过多的使用适配器，会让系统非常零乱，不易整体进行把握。明明调用A接口，却被适配成B接口
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午4:45
 */
public class Adapter implements MediaPlayer{

    AdvancedMediaPlayer advancedMusicPlayer;

    public Adapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc") ){
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

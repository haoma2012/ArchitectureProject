package com.mobike.javaarchitecture.collection;

import java.io.Serializable;

/**
 * 序列号对象
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/22 下午6:15
 */
public class Hero implements Serializable {
    public String name;
    public int value;

    public float hp;
    public int damage;


    public synchronized void recover() {
        hp = hp + 1;
        this.notify();
    }

    public synchronized void hurt() {
        if (hp == 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hp = hp - 1;
    }

    public void attackHero(Hero h) {
        h.hp -= damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n", name, h.name, h.name, h.hp);
        if (h.isDead())
            System.out.println(h.name + "死了！");
    }

    public boolean isDead() {
        return 0 >= hp ? true : false;
    }
}
package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * 造者模式(Builder Pattern)：
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 *
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午3:14
 */
public  class MealBuilder {

    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new BurgerVeg());
        meal.addItem(new CokeDrink());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new BurgerChicken());
        meal.addItem(new PepsiDrink());
        return meal;
    }

}

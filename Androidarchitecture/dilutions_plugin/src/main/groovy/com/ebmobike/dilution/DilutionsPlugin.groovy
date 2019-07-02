package com.ebmobike.dilution

import org.gradle.api.Plugin
import org.gradle.api.Project

// 自己定义Gradle插件
class DilutionsPlugin implements Plugin<Project> {

    void apply(Project project) {
        System.out.println("----------start-----------")
        System.out.println("自定义插件：继承Plugin，实现apply方法")
        System.out.println("----------end-----------")

    }

}
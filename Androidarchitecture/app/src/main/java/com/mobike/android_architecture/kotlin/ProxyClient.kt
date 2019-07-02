package com.mobike.android_architecture.kotlin

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import kotlin.reflect.KProperty

/**
 * 代理类
 * Created by yangdehao@xiaoyouzi.com  on 2019-04-29 23:06
 */
class ProxyClient : Subject {

    override fun operate() {

        val realSubject = RealSubject()
        realSubject.operate()
    }
}

/**
 * 动态代理类
 */
class DynamicProxy : InvocationHandler {

    private var target: Subject? = null
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        return method!!.invoke(target)
    }

    fun DynamicProxy(target: Subject) {
        this.target = target
    }

}

/**
 * //抽象主题类
 */
interface Subject {
    fun operate()
}

/**
 * 真实主题类-->委托类
 */
class RealSubject : Subject {
    override fun operate() {
        println(" real subject 委托类")
    }

}


/// kotlin 委托

/**
 * 1.类的委托即一个类中定义的方法实际是调用另一个类的对象的方法来实现的。
 *
 */

interface Base {
    fun print()
}

// 实现此接口的被委托的类
class BaseImpl(val x: Int) : Base {
    override fun print() {
        println("BaseImpl 输出 $x")
    }
}

// 通过关键字 by 建立委托类
class Derived(base: Base) : Base by base

class Example {
    var p: String by Delegate()
}

// 委托的类
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了 ${property.name} 属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }
}







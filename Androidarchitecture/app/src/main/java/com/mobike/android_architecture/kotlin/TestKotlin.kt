package com.mobike.android_architecture.kotlin

import java.lang.reflect.Proxy

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/28 下午5:53
 */

fun main(args: Array<String>) {
    // 输出语句
    println("hello world")
    println('a')
    println(13)
    println(14f)
    println(12.00)

    val greeter = Greeter("盖伦")
    val faShi = Greeter("邪恶大法师", 5)
    greeter.greet()
    faShi.greet()
    print(greeter.firstName)
    // 使用策略模式计算结果
    greeter.calc(AddStrategy(), 10.0, 10.0)
    greeter.calc(SubStrategy(), 39.0, 10.0)
    greeter.calc(MultiStrategy(), 10.0, 10.0)
    greeter.calc(DivStrategy(), 30.0, 10.0)
    // 使用委托
    val print = Printer("中间Printer类")
    print.print()
    testDerived()
    // 使用反射
    testInvokeMethod()
    // 代理类
    //testProxy()
    val proxyClient = ProxyClient()
    proxyClient.operate()

    val l = mutableListOf(1, 2, 3)
    l.swap(0, 2)
    println(l.toString())
    val jack = User(name = "Jack", age = 29)
    val olderJack = jack.copy(age = 28)
    println(jack)
    println(olderJack)

}

// kotlin委托
fun testDerived() {
    val b = BaseImpl(10)
    Derived(b).print()

}


fun testProxy() {
    //创建一个真实对象
    val real =  RealSubject()
    //创建一个动态代理对象
    val proxy =  DynamicProxy()
    proxy.DynamicProxy(real)
    //获取被代理类的ClassLoader对象
    val loader = real::class.java.classLoader
    //动态构造一个代理对象
    val subject = Proxy.newProxyInstance(loader, arrayOf<Class<*>>(Subject::class.java), proxy) as Subject    //调用方法
    subject.operate()

}


// 扩展函数 Greeter 新增内部swap函数
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp

}

// 主构造函数
class Greeter(val name: String) {

    var firstName: String? = "green"
    var heroAge: Int = 1

    // 次构造函数
    constructor(name: String, age: Int) : this(name) {
        //
        this.firstName = name
        heroAge = age
    }

    // 扩展函数
    fun Greeter.PrintName() {
        println("我是扩展函数：输出$name")
    }

    // 使用策略模式计算
    fun calc(strategy: Strategy, paramsA: Double, paramsB: Double): Double {
        val calc = Calc(strategy)
        return calc.calc(paramsA, paramsB)
    }

    fun greet() {
        println("hello, $name")
        testVararg(1, 3, 5, 7, 9)
        AddStrategy().calc(10.0, 20.0)

    }

    // 可变长参数函数
    private fun testVararg(vararg v: Int) {
        for (vt in v) {
            print(vt)
        }

    }

    // 循环
    private fun testForAndWhile() {

        for (i in 1..10) {

            if (i == 3) continue
            print(i)
            if (i > 5) break
        }
        // 使用loop标签限制break continue
        loop@ for (i in 1..100) {
            if (i == 50) continue
            print(i)
            if (i > 59) break@loop
        }
    }


    // 内部类
    inner class Inner {
        fun foo() = firstName
    }
}


// 创建数据类
data class User(val name: String, val age: Int)

// 密封类
sealed class Expr

data class Const(val name: String) : Expr()
data class Sum(val paramsA: Expr, val paramsB: Expr) : Expr()

enum class Color {
    RED, BLACK, WHITE, BLUE, GREEN
}

/**
 * 计算上下文
 */
class Calc(var strategy: Strategy) {

    fun calc(paramsA: Double, paramsB: Double): Double {
        val value = strategy.calc(paramsA, paramsB)
        println("使用策略模式计算的结果：$value")
        return value

    }
}

// 抽象策略类
interface Strategy {
    fun calc(paramsA: Double, paramsB: Double): Double

    // 允许默认 已经实现
    fun foo() {
        print("foo")
    }
}

// 具体抽象实现类
class AddStrategy : Strategy {

    override fun calc(paramsA: Double, paramsB: Double): Double {
        val sum = paramsA + paramsB
        println("策略求和运算$sum")
        return sum
    }
}

/**
 * 减法实现类
 */
class SubStrategy : Strategy {
    override fun calc(paramsA: Double, paramsB: Double): Double {
        val subValue = paramsA - paramsB
        println("求差运算$subValue")
        return subValue
    }
}

/**
 * 乘法实现类
 */
class MultiStrategy : Strategy {
    override fun calc(paramsA: Double, paramsB: Double): Double {
        val subValue = paramsA * paramsB
        println("求积运算$subValue")
        return subValue
    }
}

/**
 * 除法实现类
 */
class DivStrategy : Strategy {
    override fun calc(paramsA: Double, paramsB: Double): Double {
        val subValue = paramsA / paramsB
        println("求商运算$subValue")
        return subValue
    }
}

// 委托模式 & 代理模式
class RealPrinter {
    fun print() {
        println("RealPrinter print something")
    }
}

// 中间类the "delegator"
class Printer(val name: String) {
    val printer = RealPrinter() // create the delegate

    fun print() {
        println("我是中间类$name")
        printer.print() // delegation
    }
}


abstract class Animal {
    open fun print() {
        println("Animal.print()")
    }
}

internal class Cat : Animal() {
    override fun print() {
        println("Cat.print()")
    }

}

// 反射调用InvokeObj
fun testInvokeMethod() {
    val clazz = InvokeObj::class.java
    val methods = clazz.methods
    System.out.println("以下输出InvokeObj类的方法：")
    for (method in methods) {
        System.out.println(method)
    }
    System.out.println("InvokeObj类的无参show()方法：")
    val method1 = clazz.getMethod("show")
    //会执行无参show()方法
    val obj = method1.invoke(InvokeObj())
    System.out.println("输出无参show()方法的返回值：$obj")
    System.out.println("InvokeObj类的show()方法： ")
    val method2 = clazz.getMethod("show", String::class.java)
    val obj1 = method2.invoke(InvokeObj(), "hello,world")
    System.out.println("输出有参数show()方法的返回值：$obj1")


}

class InvokeObj {
    fun show() {
        System.out.println("无参show()方法。")
    }

    fun show(name: String) {
        System.out.println("show方法：$name")
    }

    fun arrayShow(arr: Array<String>): Array<String> {
        return arr
    }

    fun StringShow(str: String): String {
        return str
    }

    fun intShow(num: Int): Int {
        return num
    }
}







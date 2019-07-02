## framework 库 三方核心库


#### 1. 事件传递EventBus

- [github 地址]https://github.com/greenrobot/EventBus
- [EventBus 官网](http://greenrobot.org/eventbus/)

![Eventbus 设计图](https://upload-images.jianshu.io/upload_images/4267785-fe0839045cda4944.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 2.



***
#### 3.切面编程AOP实践【迁移到主工程最好】

> AOP为Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。
AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

**AOP实现方式**
- AspectJ: 一个 JavaTM 语言的面向切面编程的无缝扩展（适用Android）。
- Javassist for Android: 用于字节码操作的知名 java 类库 Javassist 的 Android 平台移植版。
- DexMaker: Dalvik 虚拟机上，在编译期或者运行时生成代码的 Java API。
- ASMDEX: 一个类似 ASM 的字节码操作库，运行在Android平台，操作Dex字节码。


***
#### 4.热修复框架


***

#### 5.RxJava & RxAndroid


- https://github.com/ReactiveX/RxJava
- https://github.com/ReactiveX/RxAndroid

 RxAndroid是RxJava的一个针对Android平台的扩展，主要用于 Android 开发

 > Rxjava由于其基于事件流的链式调用、逻辑简洁 & 使用简单的特点，深受各大
 > Android开发者的欢迎。RxJava 在 GitHub 主页上的自我介绍是
 > `a library for composing asynchronous and event-based programs using observable sequences for the Java VM`
 >（一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库）。这就是 RxJava ，概括得非常精准。
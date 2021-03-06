## JavaArchitecture Java 体系架构
> 总结汇总Java知识体系

## 1.Java设计模式

![](https://upload-images.jianshu.io/upload_images/4267785-7c3f4827ffdb3fee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![23中设计模式](https://upload-images.jianshu.io/upload_images/4267785-56eee15d373c5edb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 设计模式:设计模式在设计上是一种流行的思考设计问题的方法，是一套被反复使用，多数人知晓的，经过分类编目的，代码设计经验的总结。
学习设计模式为了使代码具有可重用性，让代码更容易被他人理解和保证代码的可靠性。

#### 1.设计模式六大原则:
[设计模式六大原则](https://wiki.jikexueyuan.com/project/java-design-pattern-principle/)
- 单一职责原则  *简单说就是一个类只做一件事*
- 里氏替换原则  *简单说就是父类出现的地方子类也可以出现，替换子类也是可以的*
**子类可以扩展父类的功能，但不能改变父类原有的功能。**它包含以下4层含义：
```
子类可以实现父类的抽象方法，但不能覆盖父类的非抽象方法。
子类中可以增加自己特有的方法。
当子类的方法重载父类的方法时，方法的前置条件（即方法的形参）要比父类方法的输入参数更宽松。
当子类的方法实现父类的抽象方法时，方法的后置条件（即方法的返回值）要比父类更严格。
```
- 依赖倒置原则 *简单说就是面向接口编程，或者说是面向抽象编程，这里的抽象指的是接口或者抽象类。面向接口编程是面向对象精髓之一。*

依赖倒置原则这个名字看着有点不好理解，“依赖”还要“倒置”，这到底是什么意思？
依赖倒置原则的几个关键点如下:
```
高层模块不应该依赖低层模块，两者都应该依赖其抽象；
抽象不应该依赖细节；
细节应该依赖抽象。
```
- 开闭原则  *开闭原则的定义是 : 一个软件实体如类、模块和函数应该对扩展开放，对修改关闭。*
- 接口隔离原则 *客户端不应该依赖它不需要的接口；一个类对另一个类的依赖应该建立在最小的接口上。根据接口隔离原则，当一个接口太大时，我们需要将它分割成一些更细小的接口，使用该接口的客户端仅需知道与之相关的方法即可。*
- 迪米特原则 *一个对象应该对其他对象有最少的了解。*

#### 2.设计模式分类与作用

总体来说设计模式分为三大类：

- 创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
- 结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
- 行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。

其实还有两类：并发型模式和线程池模式。

- (1) 设计模式来源众多专家的经验和智慧
- (2) 设计模式提供了一套通用的设计词汇和一种通用的形式来方便开发人员之间沟通和交流，使得设计方案更加通俗易懂。
- (3) 大部分设计模式都兼顾了系统的可重用性和可扩展性
- (4)在学习每一个设计模式时至少应该掌握如下几点：这个设计模式的意图是什么，它要解决一个什么问题，什么时候可以使用它；它是如何解决的，掌握它的结构图，记住它的关键代码；能够想到至少两个它的应用实例，一个生活中的，一个软件中的；这个模式的优缺点是什么，在使用时要注意什么。当你能够回答上述所有问题时，恭喜你，你了解一个设计模式了，至于掌握它，那就在开发中去使用吧，用多了你自然就掌握了。

> 推荐书籍《设计模式》《设计模式之禅》《大话设计模式》《设计模式：可复用面向对象软件的基础》
- [Android开发代码装逼过程——设计模式](https://haoma2012.github.io/2018/03/22/Android%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E4%BD%BF%E7%94%A8/)

#### 3.设计模式汇总
> 设计模式分类：
> 创建型模式:与对象的创建有关
> 结构型模式:处理类或对象的组合
> 行为型模式:对类或对对象怎样交互和怎样分配职责进行描述

![设计模式之间的关系](https://upload-images.jianshu.io/upload_images/4267785-8e74e6b15243cc71.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#### 2.Java数据结构与算法




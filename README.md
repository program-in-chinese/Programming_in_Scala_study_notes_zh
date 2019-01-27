# Programming in Scala第三版学习笔记, 示例代码中文命名

前言: 本书已有中文版, 此笔记并不是对原教程的翻译, 而是围绕示例进行选摘, 并顺便将所有示例改成中文命名(不拘泥于原本命名用词, 而是融入中文特色).

**代码在Scala 2.12, Java 1.8.0_45下测试通过. 前十章Scala 2.12.4, 之后2.12.8**

## 目录

[第一章 普适的语言](章/第一章%20普适的语言.md)

[第二章 蹒跚学步](章/第二章%20蹒跚学步.md)

[第三章 上路](章/第三章%20上路.md)

[第四章 类和对象](章/第四章%20类和对象.md)

[第五章 基本类型和操作](章/第五章%20基本类型和操作.md)

[第六章 功能对象](章/第六章%20功能对象.md)

[第七章 内置控制结构](章/第七章%20内置控制结构.md)

[第八章 函数和闭包](章/第八章%20函数和闭包.md)

[第九章 控制抽象](章/第九章%20控制抽象.md)

[第十章 组合和继承](章/第十章%20组合和继承.md)

[第十一章 Scala层级](章/第十一章%20Scala层级.md)

[第十六章 使用列表](章/第十六章%20使用列表.md)

[第二十三章 For表达式再学习](章/第二十三章%20For表达式再学习.md)

[第三十二章 Future和并行](章/第三十二章%20Future和并行.md)

### 发现的中文相关问题

#### 命名问题
10.14 中, 可复现问题如下:
```
scala> for ((行1, 行2) <- Array(1,2) zip Array("a", "b"))
     | yield 行1 + 行2
<console>:12: error: not found: value 行1
       for ((行1, 行2) <- Array(1,2) zip Array("a", "b"))
             ^
<console>:12: error: not found: value 行2
       for ((行1, 行2) <- Array(1,2) zip Array("a", "b"))
                 ^
<console>:13: error: not found: value 行1
       yield 行1 + 行2
             ^
<console>:13: error: not found: value 行2
       yield 行1 + 行2
                  ^

scala> for ((l1, l2) <- Array(1,2) zip Array("a", "b"))
     | yield l1 + l2
res1: Array[String] = Array(1a, 2b)
```
深究后基本确定为语言设计限制: [Scala疑似中文命名问题后续](https://zhuanlan.zhihu.com/p/52114604)

#### 环境问题
命令行交互环境中, 错误信息对中文字符的定位不准. 这很干扰排错. 比较如下两个同样出错信息:
```
scala> println(["2"])
<console>:1: error: illegal start of simple expression
       println(["2"])
               ^

scala> 打印参数(["2"])
<console>:1: error: illegal start of simple expression
       打印参数(["2"])
            ^
```
the3rdVariableIsHere
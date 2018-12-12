# Programming in Scala第三版学习笔记, 示例代码中文命名

前言: 本书已有中文版, 此笔记并不是对原教程的翻译, 而是围绕示例进行选摘, 并顺便将所有示例改成中文命名(不拘泥于原本命名用词, 而是融入中文特色).

**代码在Scala 2.12, Java 1.8.0_45下测试通过. 前十章Scala 2.12.4, 之后2.12.8**

## 目录

[第一章 普适的语言](%E7%AB%A0/%E7%AC%AC%E4%B8%80%E7%AB%A0%20%E6%99%AE%E9%80%82%E7%9A%84%E8%AF%AD%E8%A8%80.md)

[第二章 蹒跚学步](%E7%AB%A0/%E7%AC%AC%E4%BA%8C%E7%AB%A0%20%E8%B9%92%E8%B7%9A%E5%AD%A6%E6%AD%A5.md)

[第三章 上路](%E7%AB%A0/%E7%AC%AC%E4%B8%89%E7%AB%A0%20%E4%B8%8A%E8%B7%AF.md)

[第四章 类和对象](%E7%AB%A0/%E7%AC%AC%E5%9B%9B%E7%AB%A0%20%E7%B1%BB%E5%92%8C%E5%AF%B9%E8%B1%A1.md)

[第五章 基本类型和操作](%E7%AB%A0/%E7%AC%AC%E4%BA%94%E7%AB%A0%20%E5%9F%BA%E6%9C%AC%E7%B1%BB%E5%9E%8B%E5%92%8C%E6%93%8D%E4%BD%9C.md)

[第六章 功能对象](%E7%AB%A0/%E7%AC%AC%E5%85%AD%E7%AB%A0%20%E5%8A%9F%E8%83%BD%E5%AF%B9%E8%B1%A1.md)

[第七章 内置控制结构](%E7%AB%A0/%E7%AC%AC%E4%B8%83%E7%AB%A0%20%E5%86%85%E7%BD%AE%E6%8E%A7%E5%88%B6%E7%BB%93%E6%9E%84.md)

[第八章 函数和闭包](%E7%AB%A0/%E7%AC%AC%E5%85%AB%E7%AB%A0%20%E5%87%BD%E6%95%B0%E5%92%8C%E9%97%AD%E5%8C%85.md)

[第九章 控制抽象](%E7%AB%A0/%E7%AC%AC%E4%B9%9D%E7%AB%A0%20%E6%8E%A7%E5%88%B6%E6%8A%BD%E8%B1%A1.md)

[第十章 组合和继承](%E7%AB%A0/%E7%AC%AC%E5%8D%81%E7%AB%A0%20%E7%BB%84%E5%90%88%E5%92%8C%E7%BB%A7%E6%89%BF.md)

[第十一章 Scala层级](章/第十一章%20Scala层级)

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

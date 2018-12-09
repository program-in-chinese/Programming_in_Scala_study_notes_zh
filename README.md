# Programming in Scala第三版学习笔记, 示例代码中文命名

前言: 本书已有中文版, 此笔记并不是对原教程的翻译, 而是围绕示例进行选摘, 并顺便将所有示例改成中文命名(不拘泥于原本命名用词, 而是融入中文特色).

**本文代码在Scala 2.12.4, Java 1.8.0_45下测试通过**

## 目录

[第一章 普适的语言](#%E7%AC%AC%E4%B8%80%E7%AB%A0-%E6%99%AE%E9%80%82%E7%9A%84%E8%AF%AD%E8%A8%80)

[第二章 蹒跚学步](#%E7%AC%AC%E4%BA%8C%E7%AB%A0-%E8%B9%92%E8%B7%9A%E5%AD%A6%E6%AD%A5)

[第三章 上路](#%E7%AC%AC%E4%B8%89%E7%AB%A0-%E4%B8%8A%E8%B7%AF)

[第四章 类和对象](#%E7%AC%AC%E5%9B%9B%E7%AB%A0-%E7%B1%BB%E5%92%8C%E5%AF%B9%E8%B1%A1)

[第五章 基本类型和操作](#%E7%AC%AC%E4%BA%94%E7%AB%A0-%E5%9F%BA%E6%9C%AC%E7%B1%BB%E5%9E%8B%E5%92%8C%E6%93%8D%E4%BD%9C)

[第六章 功能对象](#%E7%AC%AC%E5%85%AD%E7%AB%A0-%E5%8A%9F%E8%83%BD%E5%AF%B9%E8%B1%A1)

[第七章 内置控制结构](#%E7%AC%AC%E4%B8%83%E7%AB%A0-%E5%86%85%E7%BD%AE%E6%8E%A7%E5%88%B6%E7%BB%93%E6%9E%84)

[第八章 函数和闭包](#%E7%AC%AC%E5%85%AB%E7%AB%A0-%E5%87%BD%E6%95%B0%E5%92%8C%E9%97%AD%E5%8C%85)

### 第一章 普适的语言

Scala这个名字来源于"scalable语言", 当初的设想是能够用在小到脚本大到系统. Scala在标准Java平台上运行, 可以和Java库无缝集成. 技术上说, 它是一个包含了面向对象和函数式编程概念的静态类型语言, 设计的希望是取众家之长.

这章是简介Scala特性以及它能做什么, 但并不是真正的入门(啥??). 如果希望马上写Scala程序, 还是从第二章开始吧.

#### 1.1 

```scala
var 首都 = Map("中国" -> "北京", "俄罗斯" -> "莫斯科")
首都 += ("德国" -> "柏林")
println(首都("俄罗斯"))
```
(待续. 先从第二章开始)

### 第二章 蹒跚学步

此章将从0开始介绍Scala,为后两章打基础. 看完之后应该能用Scala写实用程序了(好的,那么这个笔记的第一个小目标就是看完前四章)

#### 第一步 
安装了Scala之后,在命令行(控制台)中输入`scala`之后, 就启动了解释器运行环境(理解输入的代码,运行并显示结果):
```
$ scala
Welcome to Scala 2.12.4 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_45).
Type in expressions for evaluation. Or try :help.

scala>
```
试试最简单的加法:
```
scala> 1 + 2
```
解释器打印:
```
res0: Int = 3
```
意思是: res0(自动生成的名称, 就是result 0的简写)是个整数(Int),值为(=)3. 它可以在之后被引用, 比如:
```
scala> res0 * 3
res1: Int = 9
```
不免俗地问个好吧:
```
  scala> println("吃了么?")
  吃了么?
```
println把字符串输出到标准输出(显示屏), 类似Java的System.out.println

#### 第二步 定义变量

有两种, val在定义后就不能被重新赋值, 类似Java中的final. var就可以随便赋值. 例如:
```
scala> val 问话 = "吃了么?"
问话: String = 吃了么?
```
如果习惯了Java中定义变量的方式, 也许会惊讶于为何不用显式声明`String`, 这是因为Scala在这里能自行推导出`问话`是`String`类型. 当然它的推导是很机械的, 有时也不能猜到你想要的类型. 因此也有显式定义类型的方法, 只是格式和Java有点区别:
```
scala> val 又问话: String = "吃了好"
又问话: String = 吃了好
```
定义了`问话`之后,就可以用了:
```
scala> println(问话)
吃了么?
```
问话因为是val,它就不能被重新赋值. 即使赋的是完全相同的值, 解释器也会报错:
```
scala> 问话 = "吃了么?"
<console>:12: error: reassignment to val
       问话 = "吃了么?"
```
如果重新赋值是必要的, 那么就用var, 比如:
```
scala> var 问好 = "早啊"
问好: String = 早啊
```
如果快到吃饭的时候了,问好就可以改成:
```
scala> var 问好 = "吃了么?"
问好: String = 吃了么?
```
如果输入的是多行, 只要换行后继续输入即可. 解释器会自动在接续的行前加`|`:
```
scala> var 多行 =
     | "这是又一行"
多行: String = 这是又一行
```
如果输错了, 而解释器还在等待新输入, 只要换两次行, 解释器就会重新等待新输入:
```
scala> var 放空中 =
     | 
     | 
You typed two blank lines.  Starting a new command.

scala> 
```
在以后的示例中, 将把这些`|`省略,以便阅读, 以及拷贝黏贴到解释器自行运行.

#### 第三步. 定义函数

用过了变量, 不妨来写点函数(这不是最简单的函数):
```
scala> def 最大值(x: Int, y: Int): Int = {
        if (x > y) x
        else y
      }
最大值: (x: Int, y: Int)Int
```
def表示后面的是函数定义, `最大值`是函数名称, `()`内的是函数参数, `: Int`正如之前的变量定义类似, 是表示函数的返回值类型. `{}`包括的是函数体.

和变量定义类似, 有时可以让推导返回值, 就不用声明返回值类型. 如果函数体只有一条声明, 就可以作如下省略:
```
scala> def 最大值(x: Int, y: Int) = if (x > y) x else y
最大值: (x: Int, y: Int)Int
```
定义函数之后, 就可以用了:
```
scala> 最大值(3, 5)
res0: Int = 5
```
下面的函数没有参数也没有返回值:
```
scala> def 问好() = println("吃了么?")
问好: ()Unit
```
Scala中的Unit类型类似于Java中的void. Java中所有的void返回值的方法都对应到返回值是Unit类型的Scala方法.
下面把Scala代码放在文件中运行. `:quit`或者`:q`退出解释器:
```
scala> :q
```

#### 第四步. 写脚本
运行:
```
$ scala 你好.scala
```
用args可以引入命令行参数, 索引从0开始. 运行:
```
$ scala 带参数问好.scala 大黄
```
输出:
```
你好, 大黄
```

#### 第五步. while循环; if条件判断

```scala
var 索引 = 0
while (索引 < args.length) {
  if (索引 != 0) {
    print(" ");
  }
  print(args(索引))
  索引 += 1
}
println()
```
Scala可以省略句尾的分号.

#### 第六步. foreach 和 for

```
args.foreach(参数 => println(参数))
```
效果和前例相同. 只要程序用不着`索引`, 就不用while结构了

如果要显式定义`参数`的类型, 就用`(参数: String)`

如果函数只有一个参数, 就可以进一步简化成这样:
```
  args.foreach(println)
```
for循环的例子:
```
for(参数 <- args)
  println(参数)
```
其中`参数`省略了val, 也就是说它不可以被重新赋值

(第二章完)

### 第三章 上路

此章之后, 应该可以用Scala写有用的脚本了. 不妨动手尝试.

#### 第七步. 带类型的数组

下面初始化一个BigInteger, 并初始化为`12345`
```
var 大数 = new java.math.BigInteger("12345")
```
带类型的数组示例:
```scala
val 问候字段 = new Array[String](3)

问候字段(0) = "你好"
问候字段(1) = ", "
问候字段(2) = "吃了么?\n"

for (索引 <- 0 to 2)
  print(问候字段(索引))
```
上面其实是下面的简写版:
```scala
  val 问候字段 = new Array[String](3)
  
  问候字段.update(0, "你好")
  问候字段.update(1, ", ")
  问候字段.update(2, "吃了么\n")
  
  for (索引 <- 0.to(2))
    print(问候字段.apply(索引))
```
新建数组可以很简单:
```
val 数字 = Array("零", "一", "二")
```
因为初始化使用的是字符串, 因此"数字"理应是字符串数组, 即使没有显式声明

上面代码是下面的简化版. 实际上Array.apply是个静态方法, 可以创建一个数组, 也称为工厂方法.
```
var 数字 = Array.apply("零", "一", "二")
```

#### 第八步. 列表
Scala中的列表scala.List是不可变的. 而java.util.List是可变的.

下面是创建列表:
```scala
val 一二三 = List(1, 2, 3)
```
因为它的内容是不可变的, 因此很多看似会改变列表的方法, 实际上是新建了一个列表. 比如`:::`是合并列表:
```scala
  val 一二 = List(1, 2)
  val 三四 = List(3, 4)
  val 一二三四 = 一二 ::: 三四
  println(一二 + " 和 " + 三四 + " 没有改变.")
  println("所以, " + 一二三四 + " 是新列表.")
```
会返回:
```
List(1, 2) 和 List(3, 4) 没有改变.
所以, List(1, 2, 3, 4) 是新列表.
```
也许最常用的列表操作符会是`::`, 就是`加塞`, 可以把一个元素加到一个列表的头上, 返回一个新列表.
```scala
val 二三 = List(2, 3)
val 一二三 = 1 :: 二三
```
将会看到`List(1,2,3)`
这里需要说明, 操作符只要末尾有`:`, 它就是作用在它右边的操作数上; 否则作用在左边的操作数.
比如`a * b`, 就是`a.*(b)`(*就是个方法名), 而`1 :: 二三`就是`二三.::(1)`

Nil是空列表, 因此初始化也可以挨个加塞元素, 比如:
```
val 一二三 = 1 :: 2 :: 3 :: Nil
```
取列表元素和数组一样, 也是用(i)

在列表后追加元素可以用`:+`, 但很少用, 因为它的耗时随列表长度线性增加. 而`::`耗时是恒定的. 如果想要以追加元素的方式创建列表, 那么就用`::`, 再调用`reverse`. 同样是创建列表, 前者耗时是O(N^2), 后者是O(N).
列表的内置方法有很多. 详见文档. 十六章将介绍更多列表功能.

#### 第九步 使用元组

元组(Tuple)比列表更加灵活, 因为它允许存放多种类型的数据. 比如:
```scala
val 对 = (99, "久久")
println(对._1)
println(对._2)
```
它会打印第一个和第二个元素. 元组的类型取决于长度和元素类型, 比如"对"的类型是Tuple2(Int, String). 不像列表注意元组的索引开始于1, 因为其他语言(Haskell/ML)中的元组也是如此).

#### 第十步 使用集合(Set)和映射(Map)
**(这里开始仅包含例程与极简说明, 如有空再补详细说明)**

不可变集合. 如果将var改为val, 会报不能赋值的错, 因为这里的`+=`等价于`客机厂商 = 客机厂商 + "商飞"`, 而默认的`scala.collection.immutable.Set`不能被再次赋值
```scala
var 客机厂商 = Set("空客", "波音")
客机厂商 += "商飞"
println(客机厂商.contains("大疆"))
```

可变集合
```scala
import scala.collection.mutable

val 电影 = mutable.Set("舌尖一", "舌尖二")
电影 += "舌尖三"
println(电影)
```
如需指定使用HashSet, 就`import scala.collection.immutable.HashSet`

可变映射
```scala
import scala.collection.mutable

val 寻宝指南 = mutable.Map[Int, String]()
寻宝指南 += (1 -> "上荒岛")
寻宝指南 += (2 -> "在地上找个那啥")
寻宝指南 += (3 -> "开挖")
println(寻宝指南(2))
```

不变映射
```scala
val 中文数字 = Map(1 -> "一", 2 -> "二", 3 -> "三", 4 -> "四", 5 -> "五")
println(中文数字(4))
```

#### 第十一步 学习函数风格
改自第二章例子:
```scala
def 打印参数(参数: Array[String]): Unit = {
  var i = 0
  while (i < 参数.length) {
    println(参数(i))
    i += 1
  }
}
```
如下可以省去var
```scala
def 打印参数(参数: Array[String]): Unit = {
  for (某参数 <- 参数)
    println(某参数)
}
```
或更简单:
```scala
def 打印参数(参数: Array[String]): Unit = {
  参数.foreach(println)
}
```
函数返回类型为Unit就是有副作用的迹象, 下面是无副作用的函数(不打印输出, 也没有var):
```scala
def 格式化参数(参数: Array[String]) = 参数.mkString("\n")
```

用`assert`测试:
```scala
val 结果 = 格式化参数(Array("一", "二", "三"))
assert(结果 == "一\n二\n三")
```
更多测试相关见14章

#### 第十二步 从文件读行
```scala
import scala.io.Source

// 下面"args"如改写为"参数"后报错: error: not found: value 参数
if (args.length > 0) {
  for (行 <- Source.fromFile(args(0)).getLines
    println(行.length + " " + 行)
}
else
  Console.err.println("请输入文件名")
```
运行`scala 统计字符1.scala  统计字符1.scala`后输出:
```
22 import scala.io.Source
0
50 // 下面"args"如改写为"参数"后报错: error: not found: value 参数
22 if (args.length > 0) {
48   for (行 <- Source.fromFile(args(0)).getLines)
31     println(行.length + " " + 行)
1 }
4 else
31   Console.err.println("请输入文件名")
```
如想输出更漂亮, 下面是最终版:
```scala
import scala.io.Source

def 字符数宽度(文本: String) = 文本.length.toString.length

if (args.length > 0) {
  val 行 = Source.fromFile(args(0)).getLines.toList
  val 最长行 = 行.reduceLeft(
    (行1, 行2) => if (行1.length > 行2.length) 行1 else 行2
  )
  val 最大宽度 = 字符数宽度(最长行)
  for (某行 <- 行) {
    val 空格数 = 最大宽度 - 字符数宽度(某行)
    val 缩进 = " " * 空格数
    println(缩进 + 某行.length + " | " + 某行)
  }
}
else
  Console.err.println("请输入文件名")
```
运行`scala 统计字符2.scala  统计字符2.scala`输出如下:
```
22 | import scala.io.Source
 0 |
49 | def 字符数宽度(文本: String) = 文本.length.toString.length
 0 |
22 | if (args.length > 0) {
52 |   val 行 = Source.fromFile(args(0)).getLines.toList
25 |   val 最长行 = 行.reduceLeft(
53 |     (行1, 行2) => if (行1.length > 行2.length) 行1 else 行2
 3 |   )
23 |   val 最大宽度 = 字符数宽度(最长行)
17 |   for (某行 <- 行) {
30 |     val 空格数 = 最大宽度 - 字符数宽度(某行)
22 |     val 缩进 = " " * 空格数
40 |     println(缩进 + 某行.length + " | " + 某行)
 3 |   }
 1 | }
 4 | else
31 |   Console.err.println("请输入文件名")
```

(第三章完)

### 第四章 类和对象

#### 4.1 类, 变量, 方法

```scala
class 校验累加器 {
}
```
`new 校验累加器`创建对象

添加变量
```scala
class 校验累加器 {
  var 和 = 0
}
```
两个对象
```scala
val 累加器 = new 校验累加器
val 保留进位加法器 = new 校验累加器
```
可以置变量:
```scala
累加器.和 = 3
```
由于`累加器`为val, 不能重赋值如下, 会编译出错
```scala
累加器 = new 校验累加器
```
如果在`var 和`前加`private`, 与Java的`private`相同, 在类外部访问此变量将编译出错. 可以内部访问:
```scala
class 校验累加器 {
  private var 和 = 0

  def 加(值: Byte): Unit = {
    和 += 值
  }

  def 校验(): Int = {
    return ~(和 & 0xFF) + 1
  }
}
```
方法参数不可赋值, 如在`加`方法中, `b = 1`会编译出错

方法可以简写如下:
```scala
class 校验累加器 {
  private var 和 = 0
  def 加(值: Byte) = 和 += 值
  def 校验() = ~(和 & 0xFF) + 1
}
```
虽然编译器可以推导方法类型, 为可读性考虑, 最好写出方法返回类型:
```scala
class 校验累加器 {
  private var 和 = 0
  def 加(值: Byte): Unit = { 和 += 值 }
  def 校验(): Int = ~(和 & 0xFF) + 1
}
```

#### 4.2 分号

一行中多个声明需用分号分隔. 否则末尾可以省略
```scala
val 文本 = "你好"; println(文本)
```

下面两行会被分隔为`x`和`+y`两个声明:
```scala
x
+y
```
如果需要表示`x+y`, 则用括号或将中缀运算符`+`置于末尾:
```scala
x +
y
```

下面几种情况不会被识别为省略分号:
- 行尾词不可能为声明的结尾, 如句号或中缀运算符
- 下一行第一个词不可能为声明开头
- 行结束在括号()或[]的中间, 因为其中不可能有多个声明

#### 4.3 单例对象

用`object`代替`class`, 就成了单例对象. 如果与类名相同, 称为伴随对象, 可以与类互访私有变量:
```scala
// 在文件"校验累加器.scala"中
import scala.collection.mutable

object 校验累加器 {
  private val 缓存 = mutable.Map.empty[String, Int]

  def 计算(文本: String): Int =
    if (缓存.contains(文本))
      缓存(文本)
    else {
      val 累加器 = new 校验累加器
      for (字符 <- 文本)
        累加器.加(字符.toByte)
      val 校验码 = 累加器.校验()
      缓存 += (文本 -> 校验码)
      校验码
    }
}
```
与类比较, 单例对象不能传入参数. 如果没有伴随类, 仅有单例对象, 不能创建`校验累加器`类型的对象. 单例对象可用于功用方法或应用入口(见下节)

#### 4.4 一个Scala应用

建议文件名与包含类名相同
```scala
// 夏季.scala
import 校验累加器.计算

object 夏季 {
  def main(参数: Array[String]) = {
    for (某参数 <- 参数)
      println(某参数 + ": " + 计算(某参数))
  }
}
```
运行`fsc 校验累加器.scala 夏季.scala`进行编译后运行:
```
$ scala 夏季 的 浪漫
的: -132
浪漫: -149
```

#### 4.5 App接口

```scala
import 校验累加器.计算

object 春夏秋冬 extends App {
  for (季节 <- List("秋", "冬", "春"))
    println(季节 + ": " + 计算(季节))
}
```
运行`fsc 春夏秋冬.scala`后运行如下:
```
$ scala 春夏秋冬
秋: -203
冬: -172
春: -37
```


### 第五章 基本类型和操作
#### 5.1 基本类型
- Byte
- Short
- Int
- Long
- Char
- String
- Float
- Double
- Boolean

#### 5.2 字面量

整数字面量. 交互环境显示的是十进制.
```scala
val 十六进制1 = 0x5
val 十六进制2 = 0x00FF
val 密 = 0xbabababa

val 十进制1 = 31
val 十进制2 = 255

// 末尾L或l为长整数
val 长密 = 0xbabababaL
val 三十一 = 31l

// 如果指定Short或Byte类型, 只要在范围内即可
val 小: Short = 367
val 更小: Byte = 38
```

浮点字面量
```scala
val 大 = 1.2345
val 大的多 = 1.2345e1
val 大的多的多 = 123E45

// 结尾F或f为单浮点数
val 小 = 1.2345F
val 小大点 = 3e5f

// 上面也可以是Double类型
val 双浮点 = 3e5
val 又一个 = 3e5D
```

字符字面量
```scala
val a = 'A'
// Unicode码: \u后跟四位数字
val d = '\u0041'
val f = '\u0044'

// 可用Unicode码命名:
val B\u0041\u0044 = 1

val 反斜杠 = '\\'
```
特殊字符需转义
```
\n \b \t \f \r \" \' \\
```

字符串字面量
```scala
val 你好 = "你好"
val 转义 = "\\\"\'"

// 如果用"""包括字符串, 其中不需转义字符, 比如下面的换行, 空格, 和双引号:
println("""欢迎.
           选择"帮助"""")

// 第二行开头的空格可除去:
println("""|欢迎.
           |选择"帮助"""".stripMargin)
```

符号字面量
```scala
def 根据名称更新数据库条目(符号: Symbol, 值: Any) = {}

// `根据名称更新数据库条目(书, "我来编程")` 报错
根据名称更新数据库条目('书, "我来编程")

// 可取符号名称
val 符号 = '一个符号
val 名字 = 符号.name
```

布尔字面量
```scala
val 布尔量 = true
val 布尔量 = false
```

#### 5.3 字符串内插
在字符串字面量中, 插入表达式
```scala
val 名字 = "读者"
println(s"你好, $name!")

// 如果表达式包括非标识符, 需要在$后紧接{}包裹表达式
s"结果是${6 * 7}"

// raw与s类似, 但不识别转义符, 如下显示4个反斜杠
raw"无\\\\转义"

// f类似printf
f"${math.Pi}%.5f"

// 如果不带%, 就等同于s
val π = "π"
// 下面$后变量必须跟着空格
f"$π 大约是${math.Pi}%.8f"
```

#### 5.4 运算符是方法
```scala
// 等价于1+2
1.+(2)

// 等价于 "你好吃好".indexOf('好')
"你好吃好" indexOf '好'

// 当有多个参数, 需用括号囊括. 等价于 "你好吃好".indexOf('好', 2), 
"你好吃好" indexOf ('好', 2)

// 任何方法都可以是运算符. 除了双操作数的(对象在前, 参数在后), 还有前置和后置:
// 下面等价于 -2.0, Scala自动将'-'方法前加"unary_"并调用. 可前置的只有 + - ! ~
2.0.unary_-

// 后置运算符是无参数方法. 如果方法无副作用, 可以省去(), 否则如果包含println()则不省略(??)
"LaLaLaLa".toLowerCase
// 或
"LaLaLaLa" toLowerCase
```

下面三节跳过, 与Java基本相同
#### 5.5 算术运算
#### 5.6 比较和逻辑运算
#### 5.7 位运算

#### 5.8 对象比较

与Java == 不同的是, 如果对象内容相同, 也返回真:
```scala
List(1,2,3) == List(1,2,3)

1 == 1.0

("你好" + "吃好") == "你好吃好"
```

#### 5.9 运算符优先级和结合
```
* / %
+ -
:
= !
< >
&
^
|
所有字母
所有赋值运算符
```
如果方法用':'结尾, 则从右到左结合, 如`a:::b:::c`处理顺序为`a:::(b:::c)`

#### 5.10 更多内置方法和封装类型
```scala
0 max 5
0 min 5
-2.7 abs
-2.7 round
1.5 isInfinity
(1.0/0) isInfinity
4 to 6
"bob" capitalize
"robert" drop 2
```
所有基本类型都有对应封装类, 提供额外功能. 除String是`scala.collection.immutable.StringOps`, 其他类型都对应`scala.runtime.Rich_`, 如Int对应`scala.runtime.RichInt`

### 第六章 功能对象

以一个有理数类为例演示Scala面向对象编程的各个方面
#### 6.1 有理数定义
设想中的功能:
```scala
val 一半 = new 有理数(1, 2) // 结果为1/2
val 三分之二 = new 有理数(2, 3)
(一半 / 7) + (1 - 三分之二) // 结果为有理数17/42
```

#### 6.2 构造
```scala
class 有理数(分子: Int, 分母: Int)
```
不可变对象有好处, 也有短处. 这里创建不可变对象.

任何类内不为变量或者方法声明的部分都会被置于首要构建器.
```scala
class 有理数(分子: Int, 分母: Int) {
  println("已创建" + 分子 + "/" + 分母)
}
```
调用`new 有理数(1, 2)`会打印`已创建1/2`

#### 6.3 重新实现toString方法

```scala
class 有理数(分子: Int, 分母: Int) {
  override def toString = 分子 + "/" + 分母
}
```
运行:
```
scala> new 有理数(1, 2)
res1: 有理数 = 1/2
```

#### 6.4 检查先决条件
```scala
class 有理数(分子: Int, 分母: Int) {
  require(分母 != 0)
  override def toString = 分子 + "/" + 分母
}
```

#### 6.5 加变量
```scala
class 有理数(分子: Int, 分母: Int) {
  require(分母 != 0)
  val 分子值: Int = 分子
  val 分母值: Int = 分母
  override def toString = 分子值 + "/" + 分母值
  def 加(数: 有理数): 有理数 =
    new 有理数(
      分子值 * 数.分母值 + 数.分子值 * 分母值,
      分母值 * 数.分母值
    )
}
```
运行:
```
scala> val 一半 = new 有理数(1, 2)
一半: 有理数 = 1/2

scala> val 三分之二 = new 有理数(2, 3)
三分之二: 有理数 = 2/3

scala> 一半 加 三分之二
res3: 有理数 = 7/6
```
也可取对象内变量值:
```
scala> 一半.分子值
res4: Int = 1

scala> 一半.分母值
res5: Int = 2
```

#### 6.6 自引用
```scala
def 小于(数: 有理数) =
  this.分子值 * 数.分母 < 数.分子 * this.分母

def 最大(数: 有理数) =
  if (this.小于(数)) 数 else this
```

#### 6.7 辅助构建器
所有辅助构建器的第一句必须调用另一个构建器, 因此必须是`this(...)`格式
```scala
def this(数: Int) = this(数, 1)
```

#### 6.8 私有变量和方法
在有理数类中, 新添如下:
```scala
class 有理数(分子: Int, 分母: Int) {
  require(分母 != 0)
  private val 公约数 = 最大公约数(分子.abs, 分母.abs)

  val 分子值: Int = 分子 / 公约数
  val 分母值: Int = 分母 / 公约数
  override def toString = 分子值 + "/" + 分母值
  def 加(数: 有理数): 有理数 =
    new 有理数(
      分子值 * 数.分母值 + 数.分子值 * 分母值,
      分母值 * 数.分母值
    )
  private def 最大公约数(甲: Int, 乙: Int): Int =
    if (乙 == 0) 甲 else 最大公约数(乙, 甲 % 乙)
}
```
运行:
```scala
scala> new 有理数(66, 42)
res7: 有理数 = 11/7
```

#### 6.9 定义运算符
定义了+和*:
```scala
class 有理数(分子: Int, 分母: Int) {
  require(分母 != 0)
  private val 公约数 = 最大公约数(分子.abs, 分母.abs)

  val 分子值: Int = 分子 / 公约数
  val 分母值: Int = 分母 / 公约数

  def this(数: Int) = this(数, 1)

  def + (数: 有理数): 有理数 =
    new 有理数(
      分子值 * 数.分母值 + 数.分子值 * 分母值,
      分母值 * 数.分母值
    )

  def * (数: 有理数): 有理数 =
    new 有理数(分子值 * 数.分子值, 分母值 * 数.分母值)

  override def toString = 分子值 + "/" + 分母值

  private def 最大公约数(甲: Int, 乙: Int): Int =
    if (乙 == 0) 甲 else 最大公约数(乙, 甲 % 乙)
}
```
运行. 运算符优先级见5.9:
```
val x = new 有理数(1, 2)
val y = new 有理数(2, 3)
x + y
x + x * y
(x + x) * y
x + (x * y)
```

#### 6.10 Scala标识符

之前看到了两种标识符: 数字字母混合, 运算符

与Java不同的是, 常量在Java中是大写加下划线分隔, 如`X_OFFSET`; Scala只要求驼峰命名而且开头大写, 如`XOffset`

运算符标识符也有不同, x<-y在Java中被识别为`x < - y`, 但Scala中将`<-`解析为单个标识符

混合标识符, 如`unary_+`定义一元+运算符, `myvar_=`定义赋值运算符 (第18章更多)

字面量标识符如`x`, `yield`, `<clinit>`. 一个用法是访问Java的`Thread`类, 由于`yield`是Scala保留字, 不能写`Thread.yield()`, 但可以用反引号: Thread.\`yield\`()

#### 6.11 方法重载
支持有理数+整数, 顺便加上減和除. 源码在[有理数.scala](有理数.scala)
```scala
class 有理数(分子: Int, 分母: Int) {
  require(分母 != 0)
  private val 公约数 = 最大公约数(分子.abs, 分母.abs)

  val 分子值: Int = 分子 / 公约数
  val 分母值: Int = 分母 / 公约数

  def this(数: Int) = this(数, 1)

  def + (数: 有理数): 有理数 =
    new 有理数(
      分子值 * 数.分母值 + 数.分子值 * 分母值,
      分母值 * 数.分母值
    )

  def + (数: Int): 有理数 =
    new 有理数(分子值 + 数 * 分母值, 分母值)

  def - (数: 有理数): 有理数 =
    new 有理数(
      分子值 * 数.分母值 - 数.分子值 * 分母值,
      分母值 * 数.分母值
    )

  def - (数: Int): 有理数 =
    new 有理数(分子值 - 数 * 分母值, 分母值)

  def * (数: 有理数): 有理数 =
    new 有理数(分子值 * 数.分子值, 分母值 * 数.分母值)

  def * (数: Int): 有理数 =
    new 有理数(分子值 * 数, 分母值)

  def / (数: 有理数): 有理数 =
    new 有理数(分子值 * 数.分母值, 分母值 * 数.分子值)

  def / (数: Int): 有理数 =
    new 有理数(分子值, 分母值 * 数)

  override def toString = 分子值 + "/" + 分母值
  
  private def 最大公约数(甲: Int, 乙: Int): Int =
    if (乙 == 0) 甲 else 最大公约数(乙, 甲 % 乙)
}
```
运行:
```
scala> val x = new 有理数(2, 3)
x: 有理数 = 2/3

scala> x * x
res4: 有理数 = 4/9

scala> x * 2
res5: 有理数 = 4/3
```

#### 6.12 隐式转换
现在仍不支持`2*x`:
```
scala> 2 * x
<console>:13: error: overloaded method value * with alternatives:
  (x: Double)Double <and>
  (x: Float)Float <and>
  (x: Long)Long <and>
  (x: Int)Int <and>
  (x: Char)Int <and>
  (x: Short)Int <and>
  (x: Byte)Int
 cannot be applied to (有理数)
       2 * x
```
运行. 注意如果在类内声明, 就只在类内有效, 而在交互环境内就无效, 因此必须在交互环境声明:
```scala
implicit def 整数到有理数(数: Int) = new 有理数(数)
```
之后再运行成功. 更多隐式转换见21章
```
scala> val r = new 有理数(2, 3)
r: 有理数 = 2/3

scala> 2 * r
res0: 有理数 = 4/3
```

#### 6.13 注意点

运算符方法和隐式转换过头会导致代码可读性下降, 比如当运算符过于复杂或者晦涩, 而隐式转换的问题是它可能不写在源代码中. 因此需谨慎使用.

(第六章完)

### 第七章 内置控制结构

只有`if, while, for, try, match`. 更多在库中. 只有`while`无返回值.

#### 7.1 if表达式
命令式风格:
```scala
var 文件名 = "默认.txt"
if (!参数.isEmpty)
  文件名 = 参数[0]
```
改为if, 也避免了var:
```scala
val 文件名 =
  if (!参数.isEmpty) 参数[0]
  else "默认.txt"
```
这样某些情况下可以省去变量:
```scala
println(if (!参数.isEmpty) 参数[0] else "默认.txt")
```
建议尽量用val, 方便阅读也易于重构

#### 7.2 while循环
```scala
def 最大公约数循环(x: Long, y: Long): Long = {
  var a = x
  var b = y
  while (a != 0) {
    val 临时 = a
    a = b % a
    b = 临时
  }
  b
}
```
do-while循环读行至行为空:
```scala
var 行 = ""
do {
  行 = readLine()
  println("读: " + 行)
} while (行 != "")
```
while结构不是表达式, 因为它们只有`Unit`返回值. 下面演示它与`void`的不同:
```
scala> def 问好() = { println("嗨") }
问好: ()Unit

scala> () == 问好()
<console>:13: warning: comparing values of types Unit and Unit using `==' will always yield true
       () == 问好()
          ^
嗨
res0: Boolean = true
```
下面的赋值也返回unit值:
```scala
var 行 = ""
while ((行 = readLine()) != "") // () != "" 总为真, 因此无限循环
  println("读: " + 行)
```
上一章的`最大公约数`是递归实现, 建议尽量用它代替while循环. while循环中也常见var的使用, 或I/O.

#### 7.3 for表达式

**遍历**
```scala
val 此处文件 = (new java.io.File(".")).listFiles

for (文件 <- 此处文件)
  println(文件)
```
遍历i从1到4
```
scala> for (i <- 1 to 4)
     |   println("迭代" + i)
迭代1
迭代2
迭代3
迭代4
```
不包括上限:
```
scala> for (i <- 1 until 4)
     |   println("迭代" + i)
迭代1
迭代2
迭代3
```
第一个文件遍历例子也可这样写, 但不常见:
```scala
for (i <- 0 until 此处文件.length)
  println(此处文件(i))
```
因为并不需要i, 也更易于出错

**过滤**

```scala
val 此处文件 = (new java.io.File(".")).listFiles
           
for (文件 <- 此处文件 if 文件.getName.endsWith(".scala"))
  println(文件)
```
等同于:
```scala
val 此处文件 = (new java.io.File(".")).listFiles
           
for (文件 <- 此处文件)
  if (文件.getName.endsWith(".scala"))
    println(文件)
```
也支持多个过滤条件:
```scala
for (
  文件 <- 此处文件
  if 文件.isFile
  if 文件.getName.endsWith(".scala")
) println(文件)
```

**嵌套迭代**
下面等价于两层for遍历
```scala
def 从文件读行(文件: java.io.File) =
  scala.io.Source.fromFile(文件).getLines.toList

def 匹配文本(模式: String) =
  for (
    文件 <- 此处文件
    if 文件.getName.endsWith(".scala");
    行 <- 从文件读行(文件)
    if 行.trim.matches(模式)
  ) println(文件 + ": " + 行.trim)

匹配文本(".*gcd.*")
```

**临时(Mid-stream)变量绑定**
上面例子中.trim调用两次. 下面用一个变量避免, 前面省略val

为何用{}? -- 因为有额外的赋值语句
```scala
def 从文件读行(文件: java.io.File) =
  scala.io.Source.fromFile(文件).getLines.toList

def 匹配文本(模式: String) =
  for {
    文件 <- 此处文件
    if 文件.getName.endsWith(".scala");
    行 <- 从文件读行(文件)
    已修剪 = 行.trim
    if 已修剪.matches(模式)
  } println(文件 + ": " + 已修剪)

匹配文本(".*gcd.*")
```

**生成新集合**
```scala
def scala文件 =
  for {
    文件 <- 此处文件
    if 文件.getName.endsWith(".scala")
  } yield 文件
```
注意格式为`for 子句 yield 块`. 不能将yield置于for之内

上一节的例子改为生成int数组:
```scala
val for行长度 =
  for {
    文件 <- 此处文件
    if 文件.getName.endsWith(".scala");
    行 <- 从文件读行(文件)
    已修剪 = 行.trim
    if 已修剪.matches(".*for.*")
  } yield 已修剪.length
```

#### 7.4 try表达式处理异常

**抛异常**
```scala
throw new IllegalArgumentException
```
throw的返回类型为`Nothing`, 详见11.3
```scala
val 一半 =
  if (n % 2 == 0)
    n / 2
  else
    throw new RuntimeException("n需为偶数")
```

**捕捉异常**
```scala
import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

try {
  val 文件 = new FileReader("输入.txt")
} catch {
  case 异常: FileNotFoundException => // 处理无此文件情况
  case 异常: IOException => // 处理其他I/O错误
}
```

注意与Java不同, Scala不需用`throws`声明所有异常

**finally子句**
```scala
import java.io.FileReader

val 文件 = new FileReader("输入.txt")
try {
  // 使用文件
} finally {
  文件.close()
}
```
loan模式见9.4

**产生一个值**
catch可有返回值:
```scala
import java.net.URL
import java.net.MalformedURLException

def 取url(路径: String) =
  try {
    new URL(路径)
  } catch {
    case e: MalformedURLException =>
      new URL("某路径")
  }
```
finally如果显式返回一个值, 将会覆盖try或catch中返回的值, f()返回2:
```scala
def f(): Int = try return 1 finally return 2
```
如果隐式返回一个值, 则不会覆盖, g()返回1:
```scala
def g(): Int = try 1 finally 2
```
由于不很想当然, 建议finally仅用于善后的副作用操作, 如关闭文件

#### 7.5 match表达式

基本功能类似于switch, 但match可用户匹配任意模式(详见15章)
```scala
val 首参数 = if (参数.length > 0) 参数[0] else ""

首参数 match {
  case "甜酱" => println("辣酱")
  case "羊肉" => println("泡馍")
  case "皮蛋" => println("豆腐")
  case _ => println("啥?")
}
```
与swtich的最大区别是, match有返回值:
```scala
val 首参数 = if (参数.length > 0) 参数[0] else ""

val 伙伴 =
  首参数 match {
    case "甜酱" => println("辣酱")
    case "羊肉" => println("泡馍")
    case "皮蛋" => println("豆腐")
    case _ => println("啥?")
  }
println(伙伴)
```

#### 7.6 没有break和continue怎么过
Java例程:
```java
int i = 0;
boolean 找到了 = false;
while (i < 参数.length) {
    if (参数[i].startsWith("-")) {
        i = i + 1;
        continue;
    }
    if (参数[i].endsWith(".scala")) {
        找到了 = true;
        break;
    }
    i = i + 1;
}
```
对应Scala:
```scala
var i = 0
var 找到了 = false

while (i < 参数.length && !找到了) {
  if (!参数(i).startsWith("-")) {
    if (参数(i).endsWith(".scala"))
      找到了 = true
  }
  i = i + 1
}
```
如想避免var, 用递归:
```scala
def 开始搜(i: Int): Int =
  if (i >= 参数.length) -1
  else if (参数(i).startsWith("-")) 开始搜(i + 1)
  else if (参数(i).endsWith(".scala")) i
  else 开始搜(i + 1)

val i = 开始搜(0)
```
尾递归优化详见8.9

如果一定要使用break, 有scala.util.control提供

#### 7.7 变量作用域

```scala
def 乘法表() = {
  var i = 1
  // 仅i
  
  while (i <= 10) {
    var j = 1
    // i和j
    
    while (j <= 10) {
      var 乘积 = (i * j).toString
      // i, j, 乘积
      var 长度 = 乘积.length
      // i, j, 乘积, 长度
      
      while (长度 < 4) {
        print(" ")
        长度 += 1
      }
      
      print(乘积)
      j += 1
    }
    
    // i, j; 乘积, 长度已出作用域
    println()
    i += 1
  }
  // 仅i
}
```

与Java一个不同是, 允许在嵌套作用域中使用同名变量, 下面先打印2后打印1:
```scala
val a = 1;
{
  val a = 2
  println(a)
}
println(a)
```
交互环境中, 每个声明都在一个新嵌套的作用域中:
```
scala> val a = 1
a: Int = 1

scala> val a = 2
a: Int = 2

scala> println(a)
2
```
可看成:
```
val a = 1;
{
  val a = 2
  {
    println(a)
  }
}
```

#### 7.8 重构命令风格代码
改为函数式风格:
```scala
def 创建行序列(行: Int) =
  for (列 <- 1 to 10) yield {
    val 乘积 = (行 * 列).toString
    val 缩进 = " " * (4 - 乘积.length)
    缩进 + 乘积
  }

def 创建行(行: Int) = 创建行序列(行).mkString

def 乘法表() = {
  val 表序列 =
    for (行 <- 1 to 10)
    yield 创建行(行)

  表序列.mkString("\n")
}
```

(第七章完)

### 第八章 函数和闭包

#### 8.1 方法

```scala
import scala.io.Source

object 长行 {

  def 处理文件(文件名: String, 宽度: Int) = {
    val 源 = Source.fromFile(文件名)
    for (行 <- 源.getLines())
      处理行(文件名, 宽度, 行)
  }
  
  private def 处理行(文件名: String, 宽度: Int, 行: String) = {
    if (行.length > 宽度)
      println(文件名 + ": " + 行.trim)
  }
}
```
为在命令行使用`长行`:
```scala
object 搜索长行 {
  def main(参数: Array[String]) = {
    val 宽度 = 参数(0).toInt
    for (某参数 <- 参数.drop(1))
      长行.处理文件(某参数, 宽度)
  }
}
```
先编译: `$ scalac 长行.scala`
运行: 
```
$ scala 搜索长行 45 长行.scala
长行.scala: private def 处理行(文件名: String, 宽度: Int, 行: String) = {
```
或者直接运行:
```
$ scala 长行.scala 45 长行.scala
长行.scala: private def 处理行(文件名: String, 宽度: Int, 行: String) = {
```

#### 8.2 本地函数

将`处理行`内置于`处理文件`:
```scala
  def 处理文件(文件名: String, 宽度: Int) = {
    def 处理行(文件名: String, 宽度: Int, 行: String) = {
      if (行.length > 宽度)
        println(文件名 + ": " + 行.trim)
    }
    
    val 源 = Source.fromFile(文件名)
    for (行 <- 源.getLines())
      处理行(文件名, 宽度, 行)
  }
```
可以直接使用外围函数的参数:
```scala
  def 处理文件(文件名: String, 宽度: Int) = {
    def 处理行(行: String) = {
      if (行.length > 宽度)
        println(文件名 + ": " + 行.trim)
    }
    
    val 源 = Source.fromFile(文件名)
    for (行 <- 源.getLines())
      处理行(文件名, 宽度, 行)
  }
```

#### 8.3 头等函数

函数除了调用, 还可以作为值进行传递, 如:
```
(x: Int) => x + 1
```
```
scala> var 递增 = (数: Int) => 数 + 1
递增: Int => Int = $$Lambda$1024/713464342@ae202c6

scala> 递增(10)
res0: Int = 11
```
可赋值`递增`:
```
scala> 递增 = (数: Int) => 数 + 9999
递增: Int => Int = $$Lambda$1081/1947060963@4e140497

scala> 递增(10)
res1: Int = 10009
```
如函数有多声明, {}包裹:
```
scala> 递增 = (数: Int) => {
     |   println("咱")
     |   println("在")
     |   println("这!")
     |   数 + 1
     | }
递增: Int => Int = $$Lambda$1084/1698322791@5a85b4e6

scala> 递增(10)
咱
在
这!
res2: Int = 11
```
很多库使用函数作为参数, 如`foreach`:
```
scala> val 几个数 = List(-11, -10, -5, 0, 5, 10)
几个数: List[Int] = List(-11, -10, -5, 0, 5, 10)

scala> 几个数.foreach((数: Int) => println(数))
-11
-10
-5
0
5
10
```
过滤:
```
scala> 几个数.filter((数: Int) => 数 > 0)
res4: List[Int] = List(5, 10)
```

#### 8.4 简写函数
省去参数类型:
```
scala> 几个数.filter((数) => 数 > 0)
res5: List[Int] = List(5, 10)
```
省去括号:
```
scala> 几个数.filter(数 => 数 > 0)
res6: List[Int] = List(5, 10)
```

#### 8.5 占位符语法
```
scala> 几个数.filter(_ > 0)
res8: List[Int] = List(5, 10)
```
编译器有时不能理解占位符所代表类型:
```
scala> val 函数 = _ + _
<console>:11: error: missing parameter type for expanded function ((x$1: <error>, x$2) => x$1.$plus(x$2))
       val 函数 = _ + _
                ^
<console>:11: error: missing parameter type for expanded function ((x$1: <error>, x$2: <error>) => x$1.$plus(x$2))
       val 函数 = _ + _
                    ^
```
可指定类型:
```scala
scala> val 函数 = (_: Int) + (_: Int)
函数: (Int, Int) => Int = $$Lambda$1118/119282849@20cf3ab3

scala> 函数(5, 10)
res9: Int = 15
```

#### 8.6 部分应用函数
```scala
几个数.foreach(println _)
```
等价于:
```scala
几个数.foreach(数 => println(数))
```
_可用于应用函数于某些参数
```
scala> def 求和(甲: Int, 乙: Int, 丙: Int) = 甲 + 乙 + 丙
求和: (甲: Int, 乙: Int, 丙: Int)Int

scala> 求和(1, 2, 3)
res13: Int = 6
```
部分应用函数就是在调用函数时不提供所有参数的表达式, 比如不提供参数:
```
scala> val a = 求和 _
a: (Int, Int, Int) => Int = $$Lambda$1142/899094347@24a38ef3

scala> a(1, 2, 3)
res14: Int = 6
```
等价于:
```
scala> a.apply(1, 2, 3)
res15: Int = 6
```
少提供一个参数:
```
scala> val b = 求和(1, _: Int, 3)
b: Int => Int = $$Lambda$1143/1555939899@c35b83c

scala> b(2)
res16: Int = 6
```
如果_代替的是所有参数, 可以省略, 比如:
```scala
几个数.foreach(println _)
```
等价于
```scala
几个数.foreach(println)
```
直接赋值函数如下会导致编译错误:
```
scala> val a = 求和
<console>:12: error: missing argument list for method 求和
Unapplied methods are only converted to functions when a function type is expected.
You can make this conversion explicit by writing `求和 _` or `求和(_,_,_)` instead of `求和`.
       val a = 求和
               ^
```

#### 8.7 闭包
下面的`数`是绑定变量, 而`增量`由于没有定义是自由变量:
```
scala> (数: Int) => 数 + 增量
<console>:12: error: not found: value 增量
       (数: Int) => 数 + 增量
                       ^
```
但只要有`增量`定义, 同样函数定义即合法:
```
scala> var 增量 = 1
增量: Int = 1

scala> val 累加 = (数: Int) => 数 + 增量
累加: Int => Int = $$Lambda$1146/681292833@8e164f2

scala> 累加(10)
res20: Int = 11
```
在运行时通过"捕捉"自由变量的绑定值来"闭合"函数就叫"闭包". 如果`增量`在闭包形成后改变, 闭包也随之改变:
```
scala> 增量 = 9999
增量: Int = 9999

scala> 累加(10)
res21: Int = 10009
```
同样如果变量在闭包中有修改, 外部也可见此修改:
```
scala> var 和 = 0
和: Int = 0

scala> 几个数.foreach(和 += _)

scala> 和
res24: Int = -11
```
在运行时, 闭包牵涉的变量值可能改变, 关键在于该闭包创建的时刻变量值如何:
```
scala> def 创建累加器(增量: Int) = (数: Int) => 数 + 增量
创建累加器: (增量: Int)Int => Int

scala> val 累加器1 = 创建累加器(1)
累加器1: Int => Int = $$Lambda$1168/1847678962@36f59005

scala> val 累加器9999 = 创建累加器(9999)
累加器9999: Int => Int = $$Lambda$1168/1847678962@4a83d668

scala> 累加器1(10)
res25: Int = 11

scala> 累加器9999(10)
res26: Int = 10009
```

#### 8.8 特殊的函数调用方式

**重复参数**
```
scala> def 回音(参数: String*) =
     |   for (某参数 <- 参数) println(某参数)
回音: (参数: String*)Unit

scala> 回音()

scala> 回音("一")
一

scala> 回音("吃了", "吗!")
吃了
吗!
```
如果传入数组, 编译报错:
```
scala> val 数组 = Array("咋", "样", "啊?") 
数组: Array[String] = Array(咋, 样, 啊?)

scala> 回音(数组)
<console>:14: error: type mismatch;
 found   : Array[String]
 required: String
       回音(数组)
          ^
```
可以通过`: _*`达成:
```
scala> 回音(数组: _*)
咋
样
啊?
```

**有名参数**
调用时, 参数可以用与函数定义时的顺序不同:
```
scala> def 速度(距离: Float, 时间: Float): Float =
     |   距离 / 时间
速度: (距离: Float, 时间: Float)Float

scala> 速度(100, 10)
res32: Float = 10.0

scala> 速度(时间 = 10, 距离 = 100)
res33: Float = 10.0
```

**默认参数值**

调用时可以选择省去指定值
```
scala> def 打印时间(输出: java.io.PrintStream = Console.out) =
     |   输出.println("时间 = " + System.currentTimeMillis())
打印时间: (输出: java.io.PrintStream)Unit

scala> def 打印时间2(输出: java.io.PrintStream = Console.out,
     |               被除数: Int = 1) =
     |   输出.println("时间 = " + System.currentTimeMillis()/被除数)
打印时间2: (输出: java.io.PrintStream, 被除数: Int)Unit

scala> 打印时间2(输出 = Console.err)
时间 = 1543303507916

scala> 打印时间2(被除数 = 1000)
时间 = 1543303528
```

#### 8.9 尾递归
7.2 中, 演示了通过递归将循环和var转变为函数式+val. 下面是递归例子(**不完整, 需另外定义`已够准`和`改进`**):
```scala
def 估摸(猜测值: Double): Double =
  if (已够准(猜测值)) 猜测值
  else 估摸(改进(猜测值))
```
循环版本:
```scala
def 估摸循环(最初猜测: Double): Double = {
  var 猜测值 = 最初猜测
  while (!已够准(猜测值))
    猜测值 = 改进(猜测值)
  猜测值
}
```
运行时两个版本几乎一样快, 递归版本中`估摸`是最后调用的函数, 即"尾递归"

**跟踪尾递归函数**
```scala
def 炸(数: Int): Int =
  if (数 == 0) throw new Exception("爆!") else 炸(数 - 1) + 1
```
此函数非尾递归, 由于在递归函数调用后有+操作, 运行时会爆:
```
scala> 炸(3)
java.lang.Exception: 爆!
  at .炸(<console>:12)
  at .炸(<console>:12)
  at .炸(<console>:12)
  at .炸(<console>:12)
  ... 29 elided
```
如果改为尾递归:
```scala
def 爆(数: Int): Int =
  if (数 == 0) throw new Exception("爆!") else 爆(数 - 1)
```
调用时可见一层堆栈:
```
scala> 爆(5)
java.lang.Exception: 爆!
  at .爆(<console>:12)
  ... 29 elided
```
为确认这是尾递归优化的效果, 可在scala交互环境启动时加`-g:notailcalls`除去尾递归优化. 之后再运行可见多层:
```
scala> 爆(5)
java.lang.Exception: 爆!
  at .爆(<console>:12)
  at .爆(<console>:12)
  at .爆(<console>:12)
  at .爆(<console>:12)
  at .爆(<console>:12)
  at .爆(<console>:12)
  ... 29 elided
```

**尾递归的局限**

如果递归函数不是直接调用, 优化不能进行:
```scala
def 为偶数(数: Int): Boolean =
  if (数 == 0) true else 为奇数(数 - 1)
def 为奇数(数: Int): Boolean =
  if (数 == 0) false else 为偶数(数 - 1)
```
即使是下面也不能:
```scala
val 函数值 = 嵌套函数 _
def 嵌套函数(数: Int): Unit = {
  if (数 != 0) { println(数); 函数值(数 - 1) }
}
```

(第八章完)

### 第九章 控制抽象

通过函数值创造控制抽象

#### 9.1 减少重复代码
下面的API匹配文件名结尾, 如".scala"
```scala
object 文件匹配 {
  private def 此处文件 = (new java.io.File(".")).listFiles
  
  def 文件名结尾(查询: String) =
    for (文件 <- 此处文件; if 文件.getName.endsWith(查询))
      yield 文件
}
```
添加一个API匹配文件名中任何子字符串:
```scala
  def 文件名包括(查询: String) =
    for (文件 <- 此处文件; if 文件.getName.contains(查询))
      yield 文件
```
再添加一个按正则表达式匹配的API:
```scala
  def 文件名正则匹配(查询: String) =
    for (文件 <- 此处文件; if 文件.getName.matches(查询))
      yield 文件
```
虽然直觉是这样:
```scala
  def 文件匹配(查询: String, 匹配函数) =
    for (文件 <- 此处文件; if 文件.getName.匹配函数(查询))
      yield 文件
```
但Scala不允许用方法名作参数. 可以用下面代替:
```scala
  def 文件匹配(查询: String, 匹配器: (String, String) => Boolean) =
    for (文件 <- 此处文件; if 匹配器(文件.getName, 查询))
      yield 文件
  
  def 文件名结尾(查询: String) =
    文件匹配(查询, _.endsWith(_))
  
  def 文件名包括(查询: String) =
    文件匹配(查询, _.contains(_))
  
  def 文件名正则匹配(查询: String) =
    文件匹配(查询, _.matches(_))
```
`_.endsWith(_)`等价于:
```scala
(文件名: String, 查询: String) => 文件名.endsWith(查询)
```
可省去`查询`传递:
```scala
  def 文件匹配(查询: String, 匹配器: (String) => Boolean) =
    for (文件 <- 此处文件; if 匹配器(文件.getName))
      yield 文件
  
  def 文件名结尾(查询: String) =
    文件匹配(_.endsWith(查询))
  
  def 文件名包括(查询: String) =
    文件匹配(_.contains(查询))
  
  def 文件名正则匹配(查询: String) =
    文件匹配(_.matches(查询))
```

#### 9.2 简化客户代码
```scala
def 包含负数(数: List[Int]): Boolean = {
  var 存在 = false
  for (某数 <- 数)
    if (某数 < 0)
      存在 = true
  存在
}
```
运行:
```
scala> 包含负数(List(1, 2, 3, 4))
res2: Boolean = false

scala> 包含负数(List(1, 2, -3, 4))
res3: Boolean = true
```
更简约的是调用高阶函数`exists`:
```scala
def 包含负数(数: List[Int]) = 数.exists(_ < 0)
```
如想找奇数:
```scala
def 包含奇数(数: List[Int]): Boolean = {
  var 存在 = false
  for (某数 <- 数)
    if (某数 % 2 == 1)
      存在 = true
  存在
}
```
类似也可用`exists`:
```scala
def 包含奇数(数: List[Int]) = 数.exists(_ % 2 == 1)
```

#### 9.3 Currying
简单的加法:
```
scala> def 原始求和(x: Int, y: Int) = x + y
原始求和: (x: Int, y: Int)Int

scala> 原始求和(1, 2)
res6: Int = 3
```
改为柯里形式:
```
scala> def 柯里求和(x: Int)(y: Int) = x + y
柯里求和: (x: Int)(y: Int)Int

scala> 柯里求和(1)(2)
res7: Int = 3
```
刚发现交互环境的tab可以自动补全, 比如上面定义过`柯里求和`后, 下面在`柯里`后tab, 就自动补全`柯里求和`

上面的形式例子拆开可由下面两步演示:
```
scala> def 第一(x: Int) = (y: Int) => x + y
第一: (x: Int)Int => Int

scala> val 第二 = 第一(1)
第二: Int => Int = $$Lambda$1270/2143477943@23327c53

scala> 第二(2)
res8: Int = 3
```
下面可以获取实际上`柯里求和`的第二个函数:
```
scala> val 加一 = 柯里求和(1)_
加一: Int => Int = $$Lambda$1281/2116219866@2d288c47

scala> 加一(2)
res9: Int = 3
```
类似可以加二:
```
scala> val 加二 = 柯里求和(2)_
加二: Int => Int = $$Lambda$1282/1859753031@7a977d23

scala> 加二(2)
res10: Int = 4
```

#### 9.4 编写新的控制结构
```
scala> def 两遍(操作: Double => Double, 数: Double) = 操作(操作(数))
两遍: (操作: Double => Double, 数: Double)Double

scala> 两遍(_ + 1, 5)
res11: Double = 7.0
```
之前的文件匹配, 也可以抽象出控制:
```scala
def 用打印写入器(文件: File, 操作: PrintWriter => Unit) = {
  val 写入器 = new PrintWriter(文件)
  try {
    操作(写入器)
  } finally {
    写入器.close()
  }
}
```
可以这样用:
```scala
用打印写入器(
  new File("数据.txt")
  写入器 => 写入器.println(new java.util.Date)
)
```
单参数可用大括号:
```
scala> println { "吃了么" }
吃了么
```
下面报错:
```
scala> val 文本 = "吃了么"
文本: String = 吃了么

scala> 文本.substring { 0, 2 }
<console>:1: error: ';' expected but ',' found.
       文本.substring { 0, 2 }
                       ^
```
如此可以:
```
scala> 文本.substring ( 0, 2 )
res15: String = 吃了
```
为避免问题, 可用柯里:
```scala
def 用打印写入器(文件: File)(操作: PrintWriter => Unit) = {
  val 写入器 = new PrintWriter(文件)
  try {
    操作(写入器)
  } finally {
    写入器.close()
  }
}
```
可如下调用:
```scala
val 文件 = new File("数据.txt")

用打印写入器(文件) { 写入器 =>
  写入器.println(new java.util.Date)
}
```

#### 9.5 基于名称的参数
```scala
var 断言使能 = true

def 我的断言(断言: () => Boolean) =
  if (断言使能 && !断言())
    throw new AssertionError
```
调用不便:
```scala
我的断言(() => 5 > 3)
```
下面定义:
```scala
def 按名称断言(断言: => Boolean) =
  if (断言使能 && !断言)
    throw new AssertionError

按名称断言(5>3)
```
当然也可以直接用Boolean类型:
```scala
def 布尔断言(断言: Boolean) =
  if (断言使能 && !断言)
     throw new AssertionError
```
区别在于, `按名称断言`在调用之前不会获取`断言`的值, 比如下面的调用, 前者会异常, 后者不会:
```
scala> 断言使能 = false
断言使能: Boolean = false

scala> 布尔断言(5/0 == 0)
java.lang.ArithmeticException: / by zero
  ... 29 elided

scala> 按名称断言(5/0 == 0)
```

(第九章完)

### 第十章 组合和继承

组合意为, 一个类包含另一个类的引用

#### 10.1 二维布局库
elem--元素; Element--元素类
```scala
元素(文本: String): 元素类
```
可以如下调用:
```scala
val 列1 = 元素("你好啊") 在上 元素("!!!")
val 列2 = 元素("~~~") 在上 元素("世界")
列1 在旁 列2
```
期望输出:
```
你好啊 !!!
~~~ 世界
```

#### 10.2 抽象类
```scala
abstract class 元素类 {
  def 内容: Array[String]
}
```
不能直接new:
```
scala> new 元素类
<console>:13: error: class 元素类 is abstract; cannot be instantiated
       new 元素类
       ^
```

#### 10.3 定义无参数方法
```scala
abstract class 元素类 {
  def 内容: Array[String]
  def 高度: Int = 内容.length
  def 宽度: Int = if (高度 == 0) 0 else 内容(0).length
}
```
注意, 三个方法定义都没有(). 好处是使用库的客户代码不需在乎它是方法还是属性, 比如下面的库改动不会影响客户代码:
```scala
abstract class 元素类 {
  def 内容: Array[String]
  val 高度 = 内容.length
  val 宽度 = if (高度 == 0) 0 else 内容(0).length
}
```
调用无参数方法时, 可以省去括号:
```scala
Array(1, 2, 3).toString
"abc".length   // 因为无副作用
println()   // 最好不要省略(), 因为有副作用. 如果省略, 客户调用时会误以为没有副作用
```

#### 10.4 扩展类

```scala
class 数组元素(数组内容: Array[String]) extends 元素类 {
  def 内容: Array[String] = 数组内容
}
```
可以调用`宽度`:
```
scala> val 某组 = new 数组元素(Array("你好啊", "世界"))
某组: 数组元素 = 数组元素@3b5a19ed

scala> 某组.宽度
res0: Int = 3
```
子类的实例也是父类的实例:
```scala
val 元素 = new 数组元素(Array("你好啊"))
```

#### 10.5 重写方法和域
Scala允许用域代替方法:
```scala
class 数组元素(数组内容: Array[String]) extends 元素类 {
  val 内容: Array[String] = 数组内容
}
```
同时, 不允许域和方法同名. 下面会编译出错:
```scala
class 不能编译 {
  private var 函数 = 0
  def 函数 = 1
}
```

#### 10.6 定义参数域
之前的`数组内容`变量只是为了与`内容`不重名, 属于"有味的代码". 可以通过"类参数"避免:
```scala
class 数组元素(
  val 内容: Array[String]
) extends 元素类
```
`内容`会在初始化时由参数赋值, 效果如同下面:
```scala
class 数组元素(某名: Array[String]) extends 元素类 {
  val 内容: Array[String] = 某名
}
```
类参数可以定义var. 也可以添加private/protected/override:
```scala
class 猫 {
  val 危险 = false
}
class 虎 (
  override val 危险: Boolean,
  private var 年龄: Int
) extends 猫
```
"虎"的定义是下面的简写:
```scala
class 虎(参数1: Boolean, 参数2: Int) extends 猫 {
  override val 危险 = 参数1,
  private var 年龄 = 参数2
}
```

### 发现的中文相关问题
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
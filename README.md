# Programming in Scala第三版学习笔记, 示例代码中文命名

前言: 本书已有中文版, 此笔记并不是对原教程的翻译, 而是围绕示例进行选摘, 并顺便将所有示例改成中文命名(不拘泥于原本命名用词, 而是融入中文特色).

**本文代码在Scala 2.12.4, Java 1.8.0_45下测试通过**

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

不可变集合
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
  for (行 <- Source.fromFile(args(0)).getLines())
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
48   for (行 <- Source.fromFile(args(0)).getLines())
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
  val 行 = Source.fromFile(args(0)).getLines().toList
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
52 |   val 行 = Source.fromFile(args(0)).getLines().toList
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

// 后置运算符是无参数方法. 如果方法无副作用, 可以省去(), 否则如println()不可省略
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

### 第六章

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
支持有理数+整数, 顺便加上減和除
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
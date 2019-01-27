人类定义:
```
scala> case class 人(名称: String,
     | 男的: Boolean,
     | 孩子: 人*)
```
几个人:
```scala
val 小红 = 人("小红", false)
val 小明 = 人("小明", true)
val 小花 = 人("小花", false, 小红, 小明)
val 人们 = List(小红, 小明, 小花)
```
找到所有的母亲和子女的对子:
```
scala> 人们 filter (某 => !某.男的) flatMap (某 =>
     | (某.孩子 map (子女 => (某.名称, 子女.名称))))
res9: List[(String, String)] = List((小花,小红), (小花,小明))
```
可用`withFilter`代替`filter`, 省去创建一个中间数据结构
```
scala> 人们 withFilter (某 => !某.男的) flatMap (某 =>
     | (某.孩子 map (子女 => (某.名称, 子女.名称))))
res10: List[(String, String)] = List((小花,小红), (小花,小明))
```
用for简化:
```
scala> for (某 <- 人们; if !某.男的; 子女 <- 某.孩子)
     | yield (某.名称, 子女.名称)
res11: List[(String, String)] = List((小花,小红), (小花,小明))
```
带`yield`的`for`表达式会被编译为`map`, `flatMap`和`withFilter`的组合, 无`yield`的编译为`withFilter`和`foreach`的组合

#### 23.1 for表达式

***待续***
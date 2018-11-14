import 校验累加器.计算

object 春夏秋冬 extends App {
  for (季节 <- List("秋", "冬", "春"))
    println(季节 + ": " + 计算(季节))
}
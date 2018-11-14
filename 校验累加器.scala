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

class 校验累加器 {
  private var 和 = 0
  def 加(值: Byte): Unit = { 和 += 值 }
  def 校验(): Int = ~(和 & 0xFF) + 1
}
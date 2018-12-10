object 元素类 {
  private class 数组元素(
              val 内容: Array[String]
            ) extends 元素类

  private class 线元素(文本: String) extends 元素类 {
    val 内容 = Array(文本)
    override def 宽度 = 文本.length
    override def 高度 = 1
  }

  private class 单一元素(
              字: Char,
              override val 宽度: Int,
              override val 高度: Int
            ) extends 元素类 {
    private val 线 = 字.toString * 宽度
    def 内容 = Array.fill(高度)(线)
  }

  def 元素(内容: Array[String]): 元素类 =
    new 数组元素(内容)

  def 元素(字: Char, 宽: Int, 高: Int): 元素类 =
    new 单一元素(字, 宽, 高)

  def 元素(线: String): 元素类 =
    new 线元素(线)
}

import 元素类.元素

abstract class 元素类 {
  def 内容: Array[String]

  def 宽度: Int = 内容(0).length
  def 高度: Int = 内容.length

  def 在上(另一: 元素类): 元素类 = {
    val 这个 = this 变宽 另一.宽度
    val 另一个 = 另一 变宽 this.宽度
    元素(这个.内容 ++ 另一个.内容)
  }

  def 在旁(另一: 元素类): 元素类 = {
    val 这个 = this 变高 另一.高度
    val 另一个 = 另一 变高 this.高度
    元素(
      // 报编译错误: error: not found: value 行1. 需深究!
      //for ((行1, 行2) <- 这个.内容 zip 另一个.内容)
      //  yield 行1 + 行2)
      for ((line1, line2) <- 这个.内容 zip 另一个.内容)
        yield line1 + line2)
  }

  def 变宽(宽: Int): 元素类 =
    if (宽 <= 宽度) this
    else {
      val 左 = 元素(' ', (宽 - 宽度) / 2, 高度)
      val 右 = 元素(' ', 宽 - 宽度 - 左.宽度, 高度)
      左 在旁 this 在旁 右
    }

  def 变高(高: Int): 元素类 =
    if (高 <= 高度) this
    else {
      val 上 = 元素(' ', 宽度, (高 - 高度) / 2)
      val 下 = 元素(' ', 宽度, 高 - 高度 - 上.高度)
      上 在上 this 在上 下
    }

  override def toString = 内容 mkString "\n"
}
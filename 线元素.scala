abstract class 元素类 {
  def 内容: Array[String]
  def 高度: Int = 内容.length
  def 宽度: Int = if (高度 == 0) 0 else 内容(0).length
}

class 数组元素(
            val 内容: Array[String]
          ) extends 元素类

class 线元素(文本: String) extends 数组元素(Array(文本)) {
  override def 宽度 = 文本.length
  override def 高 = 1
}
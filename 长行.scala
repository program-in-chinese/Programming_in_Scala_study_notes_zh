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

object 搜索长行 {
  def main(参数: Array[String]) = {
    val 宽度 = 参数(0).toInt
    for (某参数 <- 参数.drop(1))
      长行.处理文件(某参数, 宽度)
  }
}
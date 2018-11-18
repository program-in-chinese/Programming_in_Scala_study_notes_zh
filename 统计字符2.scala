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
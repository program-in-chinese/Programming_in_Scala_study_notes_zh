import scala.io.Source

// 下面"args"如改写为"参数"后报错: error: not found: value 参数
if (args.length > 0) {
  for (行 <- Source.fromFile(args(0)).getLines)
    println(行.length + " " + 行)
}
else
  Console.err.println("请输入文件名")
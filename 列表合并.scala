  val 一二 = List(1, 2)
  val 三四 = List(3, 4)
  val 一二三四 = 一二 ::: 三四
  println(一二 + " 和 " + 三四 + " 没有改变.")
  println("所以, " + 一二三四 + " 是新列表.")
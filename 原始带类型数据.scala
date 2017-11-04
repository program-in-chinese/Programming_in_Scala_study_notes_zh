  val 问候字段 = new Array[String](3)
  
  问候字段.update(0, "你好")
  问候字段.update(1, ", ")
  问候字段.update(2, "吃了么\n")
  
  for (索引 <- 0.to(2))
    print(问候字段.apply(索引))
### 第三十二章 Future和并行

32.1 麻烦
Scala支持Java的synchronized:
```scala
var 计数器 = 0
synchronized {
  // 只允许一个线程进入
  计数器 = 计数器 + 1
}
```

32.2 异步执行和try
```
scala> import scala.concurrent.Future
import scala.concurrent.Future

scala> val 未来 = Future {Thread.sleep(10000); 21 + 21 }
<console>:12: error: Cannot find an implicit ExecutionContext. You might pass
an (implicit ec: ExecutionContext) parameter to your method.

The ExecutionContext is used to configure how and on which
thread pools Futures will run, so the specific ExecutionContext
that is selected is important.

If your application does not define an ExecutionContext elsewhere,
consider using Scala's global ExecutionContext by defining
the following:

implicit val ec = ExecutionContext.global
       val 未来 = Future {Thread.sleep(10000); 21 + 21 }
```
可以像提示中那样导入一个Scala提供的全局执行上下文, 会使用线程池.
```
scala> import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext.Implicits.global

scala> val 未来 = Future {Thread.sleep(10000); 21 + 21 }
未来: scala.concurrent.Future[Int] = Future(<not completed>)
```
至少耗时10秒, 在完成之前:
```
scala> 未来.isCompleted
res52: Boolean = false

scala> 未来.value
res53: Option[scala.util.Try[Int]] = None
```
完成之后:
```
scala> 未来.isCompleted
res54: Boolean = true

scala> 未来.value
res55: Option[scala.util.Try[Int]] = Some(Success(42))
```
Try要么是Success[T], 要么是Failure[java.lang.Throwable]
```
scala> val 未来 = Future {Thread.sleep(10000); 21 / 0 }
未来: scala.concurrent.Future[Int] = Future(<not completed>)

scala> 未来.value
res56: Option[scala.util.Try[Int]] = None

scala> 未来.value
res57: Option[scala.util.Try[Int]] = Some(Failure(java.lang.ArithmeticException: / by zero))
```

#### 32.3 利用未来
用map转换
```
scala> val 未来 = Future {Thread.sleep(10000); 21 + 21 }
未来: scala.concurrent.Future[Int] = Future(<not completed>)

scala> val 结果 = 未来.map(x => x + 1)
结果: scala.concurrent.Future[Int] = Future(<not completed>)

scala> 结果.value
res58: Option[scala.util.Try[Int]] = Some(Success(43))
```
这三步(创建未来, 加法, +1)可能由三个线程完成

for转换
```
scala> val 未来1 = Future {Thread.sleep(10000); 21 + 21 }
未来1: scala.concurrent.Future[Int] = Future(<not completed>)

scala> val 未来2 = Future {Thread.sleep(10000); 23 + 23 }
未来2: scala.concurrent.Future[Int] = Future(<not completed>)

scala> for {
     |  x <- 未来1
     |  y <- 未来2
     | } yield x + y
res62: scala.concurrent.Future[Int] = Future(<not completed>)

scala> res62.value
res63: Option[scala.util.Try[Int]] = Some(Success(88))
```
for会串行化. 如果不在for之前创建未来, 它们不会并行运行. 比如下面, 至少需要20秒运行:
```
scala> for {
     |   x <- Future { Thread.sleep(10000); 21 + 21 }
     |   y <- Future { Thread.sleep(10000); 23 + 23 }
     | } yield x + y
res64: scala.concurrent.Future[Int] = Future(<not completed>)

scala> res64.value
res67: Option[scala.util.Try[Int]] = None

scala> // 至少20秒才完成

scala> res64.value
res68: Option[scala.util.Try[Int]] = Some(Success(88))
```

创建未来: Future.failed/successful/fromTry, 与Promise

`successful`工厂方法创建一个已成功的未来
```
scala> Future.successful { 21 + 21 }
res1: scala.concurrent.Future[Int] = Future(Success(42))
```
`failed`方法创建一个失败的未来
```
scala> Future.failed(new Exception("惨了!"))
res2: scala.concurrent.Future[Nothing] = Future(Failure(java.lang.Exception: 惨了!))
```
`fromTry`创建了一个`Try`已完成的未来
```
scala> import scala.util.{Success, Failure}
import scala.util.{Success, Failure}

scala> Future.fromTry(Success { 21 + 21 })
res3: scala.concurrent.Future[Int] = Future(Success(42))

scala> Future.fromTry(Failure(new Exception("惨了!")))
res4: scala.concurrent.Future[Nothing] = Future(Failure(java.lang.Exception: 惨了!))
```
一般可用`Promise`创建未来. 未来会在promise完成时完成.
```
scala> val 许诺 = Promise[Int]
许诺: scala.concurrent.Promise[Int] = Future(<not completed>)

scala> val 未来 = 许诺.future
未来: scala.concurrent.Future[Int] = Future(<not completed>)

scala> 未来.value
res5: Option[scala.util.Try[Int]] = None
```
许诺可用`success`, `failure`和`complete`完成.
```
scala> 许诺.success(42)
res6: 许诺.type = Future(Success(42))

scala> 未来.value
res7: Option[scala.util.Try[Int]] = Some(Success(42))
```
过滤: `filter`和`collect`

***待续***
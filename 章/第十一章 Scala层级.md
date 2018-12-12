### 第十一章 Scala层级

#### 11.1 类型层级
最高为`Any`类, 定义下面的方法:
```scala
final def ==(另一: Any): Boolean
final def !=(另一: Any): Boolean
def equals(另一: Any): Boolean
def ##: Int
def hashCode: Int
def toString: String
```
class 有理数(分子: Int, 分母: Int) {
  require(分母 != 0)
  private val 公约数 = 最大公约数(分子.abs, 分母.abs)

  val 分子值: Int = 分子 / 公约数
  val 分母值: Int = 分母 / 公约数
  override def toString = 分子值 + "/" + 分母值
  def 加(数: 有理数): 有理数 =
    new 有理数(
      分子值 * 数.分母值 + 数.分子值 * 分母值,
      分母值 * 数.分母值
    )
  private def 最大公约数(甲: Int, 乙: Int): Int =
    if (乙 == 0) 甲 else 最大公约数(乙, 甲 % 乙)
}
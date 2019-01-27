class 有理数(分子值: Int, 分母值: Int) {
  require(分母值 != 0)
  
  private val 公约数 = 最大公约数(分子值.abs, 分母值.abs)
  val 分子: Int = 分子值 / 公约数
  val 分母: Int = 分母值 / 公约数

  def this(数: Int) = this(数, 1)

  def + (数: 有理数): 有理数 =
    new 有理数(
      分子 * 数.分母 + 数.分子 * 分母,
      分母 * 数.分母
    )

  def + (数: Int): 有理数 =
    new 有理数(分子 + 数 * 分母, 分母)

  def - (数: 有理数): 有理数 =
    new 有理数(
      分子 * 数.分母 - 数.分子 * 分母,
      分母 * 数.分母
    )

  def - (数: Int): 有理数 =
    new 有理数(分子 - 数 * 分母, 分母)

  def * (数: 有理数): 有理数 =
    new 有理数(分子 * 数.分子, 分母 * 数.分母)

  def * (数: Int): 有理数 =
    new 有理数(分子 * 数, 分母)

  def / (数: 有理数): 有理数 =
    new 有理数(分子 * 数.分母, 分母 * 数.分子)

  def / (数: Int): 有理数 =
    new 有理数(分子, 分母 * 数)

  override def toString = 分子 + "/" + 分母
  
  private def 最大公约数(甲: Int, 乙: Int): Int =
    if (乙 == 0) 甲 else 最大公约数(乙, 甲 % 乙)
}
package it.paolopasianot.cosmobile.feature.dashboard.utility

object OrderSizes {

  private val sizeTypes = listOf(
    SizeType(matchPattern = "\\d+".toRegex(), order = GroupOrder.NUMBER),
    SizeType(matchPattern = "[XSML]+".toRegex(), order = GroupOrder.SIZE),
    SizeType(matchPattern = "IT \\d+".toRegex(), order = GroupOrder.IT),
    SizeType(matchPattern = "FR \\d+".toRegex(), order = GroupOrder.FR),
    SizeType(matchPattern = "UK \\d+".toRegex(), order = GroupOrder.UK),
  )

  fun order(unorderedSizes: List<String>): List<String> {
    return unorderedSizes
      .mapNotNull { size ->
        sizeTypes.firstOrNull { sizeType -> size.matches(sizeType.matchPattern) }?.copy(
          value = size,
        )
      }
      .groupBy(SizeType::order)
      .toSortedMap()
      .mapValues { (order, values) ->
        values.sortedWith(
          order.comparator ?: compareBy { it.value },
        )
      }
      .flatMap { it.value }
      .map(SizeType::value)

  }

  internal enum class GroupOrder(
    val comparator: Comparator<SizeType>? = null,
  ) {
    NUMBER,
    SIZE(
      comparator = compareBy{
        when {
          it.value.matches(Regex("^S.*")) -> 0
          it.value.matches(Regex("^M.*")) -> 1
          it.value.matches(Regex("^L.*")) -> 2
          else -> 3
        }
      },
    ),
    IT,
    FR,
    UK,
  }

  internal data class SizeType(
    val matchPattern: Regex,
    val value: String = "",
    val order: GroupOrder,
    val comparator: Comparator<String>? = null,
  )

}

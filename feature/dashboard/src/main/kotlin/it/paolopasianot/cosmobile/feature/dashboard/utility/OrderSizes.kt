package it.paolopasianot.cosmobile.feature.dashboard.utility

object OrderSizes {

  private val sizeTypes = listOf(
    SizeType("\\d+".toRegex(), "Numbers", order = 1),
    SizeType("[XSML]+".toRegex(), "Classic", order = 2),
    SizeType("IT \\d+".toRegex(), "Italian", order = 3),
    SizeType("FR \\d+".toRegex(), "French", order = 4),
    SizeType("UK \\d+".toRegex(), "United Kingdom", order = 5),
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
      .mapValues { (_, values) -> values.sortedBy(SizeType::value) }
      .flatMap { it.value }
      .map(SizeType::value)

  }

  internal data class SizeType(
    val matchPattern: Regex,
    val label: String,
    val value: String = "",
    val order: Int = 0,
  )

}

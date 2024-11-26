package it.paolopasianot.cosmobile.feature.dashboard.utility

object OrderSizes {

  private val sizeTypes = listOf(
    SizeType(matchPattern = "\\d+".toRegex(), order = 1),
    SizeType(matchPattern = "[XSML]+".toRegex(), order = 2),
    SizeType(matchPattern = "IT \\d+".toRegex(), order = 3),
    SizeType(matchPattern = "FR \\d+".toRegex(), order = 4),
    SizeType(matchPattern = "UK \\d+".toRegex(), order = 5),
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
    val value: String = "",
    val order: Int = 0,
  )

}

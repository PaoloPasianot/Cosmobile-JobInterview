package it.paolopasianot.cosmobile

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class CosmobileBuildType(val applicationIdSuffix: String? = null) {
  DEBUG(".debug"),
  RELEASE,
}

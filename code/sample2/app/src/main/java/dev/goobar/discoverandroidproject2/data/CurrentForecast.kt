package dev.goobar.discoverandroidproject2.data

data class CurrentForecast(
  val location: String,
  val description: String,
  val high: Float,
  val low: Float
)

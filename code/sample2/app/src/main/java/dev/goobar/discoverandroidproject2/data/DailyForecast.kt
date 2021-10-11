package dev.goobar.discoverandroidproject2.data

data class DailyForecast(
  val high: Float,
  val low: Float,
  val description: String,
  val timestamp: Long
)
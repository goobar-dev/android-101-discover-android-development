package dev.goobar.discoverandroidproject2.data

data class DailyForecast(
  val high: String,
  val low: String,
  val description: String,
  val timestamp: Long,
  val icon: String
)
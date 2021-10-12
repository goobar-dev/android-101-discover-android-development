package dev.goobar.discoverandroidproject2.data

data class CurrentForecast(
  val location: String,
  val description: String,
  val temp: String,
  val icon: String
)

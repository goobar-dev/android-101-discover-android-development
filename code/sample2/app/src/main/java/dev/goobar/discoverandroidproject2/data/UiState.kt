package dev.goobar.discoverandroidproject2.data

data class UiState(
  val currentForecast: CurrentForecast? = null,
  val weeklyForecast: List<DailyForecast>? = null
)
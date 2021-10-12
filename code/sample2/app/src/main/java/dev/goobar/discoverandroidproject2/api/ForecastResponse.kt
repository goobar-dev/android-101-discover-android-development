package dev.goobar.discoverandroidproject2.api

data class WeatherResponse(
  val main: String,
  val description: String
)
data class CurrentWeatherResponse(
  val dt: Long,
  val temp: Float,
  val weather: List<WeatherResponse>
)
data class TempResponse(
  val min: Float,
  val max: Float
)
data class DailyWeatherResponse(
  val dt: Long,
  val temp: TempResponse,
  val weather: List<WeatherResponse>
)
data class ForecastResponse(
  val current: CurrentWeatherResponse,
  val daily: List<DailyWeatherResponse>
)
package dev.goobar.discoverandroidproject2.api

import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
  @GET("geo/1.0/zip")
  suspend fun geocode(
    @Query("zip") zipcode: String,
    @Query("appid") apiKey: String
  ): GeocodeResponse

  @GET("data/2.5/onecall")
  suspend fun forecast(
    @Query("lat") lat: Float,
    @Query("lon") lon: Float,
    @Query("appid") apiKey: String,
    @Query("exclude") exclude: String = "minutely,hourly,alerts",
    @Query("units") units: String = "imperial"
  ): ForecastResponse
}
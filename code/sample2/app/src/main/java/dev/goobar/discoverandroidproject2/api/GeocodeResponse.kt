package dev.goobar.discoverandroidproject2.api

data class GeocodeResponse(
  val zip: String,
  val name: String,
  val lat: Float,
  val lon: Float
)
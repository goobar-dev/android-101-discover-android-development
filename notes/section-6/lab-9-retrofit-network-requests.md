# 🖥 Lab: Making Network Requests Using Retrofit
In this lab, you'll use Retrofit to load weather data from an external HTTP api.

## Objectives
- Use the [OpenWeatherMap One Call API](https://openweathermap.org/api/one-call-api) to load weather data
- Load the weather data for the entered zipcode
- Parse the JSON response
- Display the parsed weather response in the UI

## One Call API Notes

### Geocoding API
To convert a US-based zipcode to a lat/lon, we can use the Geocoding API
http://api.openweathermap.org/geo/1.0/zip?zip={zip}&appid={api key}

### Forecast Data
To request the current weather, and the 7-day daily forecast, for a lat/lon location
https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude=minutely,hourly,alerts&units=imperial&appid={api key}
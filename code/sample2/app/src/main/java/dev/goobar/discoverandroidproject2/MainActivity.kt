package dev.goobar.discoverandroidproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dev.goobar.discoverandroidproject2.api.ForecastResponse
import dev.goobar.discoverandroidproject2.api.GeocodeResponse
import dev.goobar.discoverandroidproject2.api.WeatherService
import dev.goobar.discoverandroidproject2.data.CurrentForecast
import dev.goobar.discoverandroidproject2.data.DailyForecast
import dev.goobar.discoverandroidproject2.data.UiState
import dev.goobar.discoverandroidproject2.ui.theme.DiscoverAndroidProject2Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
  private var retrofit = Builder()
    .baseUrl("http://api.openweathermap.org/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

  private var service: WeatherService = retrofit.create(WeatherService::class.java)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val uiState = remember { mutableStateOf(UiState())}

      DiscoverAndroidProject2Theme {
        SearchScreen(uiState.value) { zipcode ->
          searchForWeather(zipcode, uiState)
        }
      }
    }
  }

  private fun searchForWeather(zipcode: String, uiState: MutableState<UiState>) {
    lifecycleScope.launch(Dispatchers.IO) {
      val geocodeResponse = service.geocode(zipcode, "04bd94947f2731939baea95f2b059310")
      val forecastResponse = service.forecast(geocodeResponse.lat, geocodeResponse.lon, "04bd94947f2731939baea95f2b059310")

      uiState.value = createUiState(geocodeResponse, forecastResponse)
    }
  }

  private fun createUiState(geocodeResponse: GeocodeResponse, forecastResponse: ForecastResponse): UiState {
    return UiState(
      currentForecast = CurrentForecast(
        location = geocodeResponse.name,
        description = forecastResponse.current.weather[0].description,
        temp = "${forecastResponse.current.temp.toString()}°"
      ),
      weeklyForecast = forecastResponse.daily.map { dailyForecastResponse ->
        DailyForecast(
          high = dailyForecastResponse.temp.max,
          low = dailyForecastResponse.temp.min,
          dailyForecastResponse.weather[0].description,
          timestamp = dailyForecastResponse.dt * 1000
        )
      }
    )
  }
}

@Composable
fun SearchScreen(
  state: UiState,
  onSearch: (String) -> Unit) {
  Scaffold(
    topBar = { SearchAppBar(onSearch) }
  ) {
    Column {
      if (state.currentForecast != null) {
        CurrentForecast(state.currentForecast)
      }
      if (state.weeklyForecast != null) {
        ForecastList(state.weeklyForecast)
      }
    }
  }
}

@Composable
fun SearchAppBar(
  onSearch: (String) -> Unit
) {
  val zipcode = remember { mutableStateOf(TextFieldValue()) }

  TopAppBar(
    elevation = 4.dp,
    modifier = Modifier.height(120.dp)
  ) {
    OutlinedTextField(
      value = zipcode.value,
      onValueChange = { zipcode.value = it },
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Search
      ),
      keyboardActions = KeyboardActions { onSearch(zipcode.value.text) },
      leadingIcon = {
        Icon(Icons.Rounded.Search, "search")
      },
      label = {
        Text("Enter zipcode")
      },
      colors = TextFieldDefaults.textFieldColors(
        textColor = Color.White,
        unfocusedLabelColor = Color.White,
        focusedLabelColor = Color.White,
        leadingIconColor = Color.White,
        unfocusedIndicatorColor = Color.White,
        focusedIndicatorColor = Color.White,
      ),
      maxLines = 1,
      modifier = Modifier
        .fillMaxWidth(1f)
        .padding(24.dp)
    )
  }
}

@Composable
fun ColumnScope.CurrentForecast(currentForecast: CurrentForecast) {
  Column(
    modifier = Modifier
      .weight(1f)
      .padding(24.dp)
  ) {
    Text(currentForecast.location, style = MaterialTheme.typography.h2)
    Text(currentForecast.description, style = MaterialTheme.typography.h4)
    Spacer(modifier = Modifier.height(20.dp))
    Text(currentForecast.temp, style = MaterialTheme.typography.h5)
  }
}

@Composable
fun ColumnScope.ForecastList(forecast: List<DailyForecast>) {
  LazyRow(
    contentPadding = PaddingValues(24.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(forecast) { forecastItem ->
      Card(
        modifier = Modifier
          .height(240.dp)
          .width(160.dp),
        elevation = 2.dp
      ) {
        Column(
          verticalArrangement = Arrangement.Center,
          modifier = Modifier.padding(20.dp)
        ) {
          Text(text = forecastItem.description, style = MaterialTheme.typography.h5)
          Text(forecastItem.high.toString())
          Text(text = forecastItem.low.toString(), modifier = Modifier.padding(bottom = 20.dp))
          Spacer(modifier = Modifier.weight(1f))

          val date = SimpleDateFormat.getDateInstance().format(Date(forecastItem.timestamp))
          Text(date, style = MaterialTheme.typography.subtitle1)
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  DiscoverAndroidProject2Theme {
    SearchScreen(UiState()) {

    }
  }
}
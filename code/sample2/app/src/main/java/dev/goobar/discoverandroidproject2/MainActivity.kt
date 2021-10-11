package dev.goobar.discoverandroidproject2

import android.os.Bundle
import android.widget.Toast
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
import dev.goobar.discoverandroidproject2.data.CurrentForecast
import dev.goobar.discoverandroidproject2.data.DailyForecast
import dev.goobar.discoverandroidproject2.ui.theme.DiscoverAndroidProject2Theme
import java.text.SimpleDateFormat
import java.util.Date

private val SAMPLE_CURRENT_FORECAST = CurrentForecast("Seattle", "Sunny", 51.3f, 42.1f)

private val SAMPLE_FORECAST = listOf(
  DailyForecast(43.4f, 32.2f, "Cloudy", 0L),
  DailyForecast(53.4f, 32.2f, "Sunny", 0L),
  DailyForecast(51.4f, 42.2f, "Sunny", 0L),
  DailyForecast(41.4f, 36.2f, "Rain", 0L),
  DailyForecast(43.4f, 32.2f, "Cloudy", 0L),
  DailyForecast(40.4f, 32.2f, "Rain", 0L),
  DailyForecast(49.4f, 32.2f, "Sunny", 0L),
)

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      DiscoverAndroidProject2Theme {
        SearchScreen()
      }
    }
  }
}

@Composable
fun SearchScreen() {
  Scaffold(
    topBar = { SearchAppBar() }
  ) {
    Column {
      CurrentForecast(SAMPLE_CURRENT_FORECAST)
      ForecastList(forecast = SAMPLE_FORECAST)
    }
  }
}

@Composable
fun SearchAppBar() {
  val zipcode = remember { mutableStateOf(TextFieldValue()) }
  val context = LocalContext.current

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
      keyboardActions = KeyboardActions {
              Toast.makeText(context, "Searched for ${zipcode.value.text}", Toast.LENGTH_SHORT ).show()
      },
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
    Text(currentForecast.location, style = MaterialTheme.typography.h1)
    Text(currentForecast.description, style = MaterialTheme.typography.h4)
    Spacer(modifier = Modifier.height(20.dp))
    Text(currentForecast.high.toString(), style = MaterialTheme.typography.h5)
    Text(currentForecast.low.toString(), style = MaterialTheme.typography.h5)
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
    SearchScreen()
  }
}
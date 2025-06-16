
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelmap.presentation.routeDetails.pastelColors
import com.example.travelmap.presentation.routeDetails.plans
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.max


data class Place(val time: String, val title: String, val subtitle: String)
data class DayPlan(val dateLabel: String, val places: List<Place>)



@Composable
fun RouteDetailsScreen(onBack: () -> Unit = {}) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { plans.size }
    )

    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {

        TopAppBar(
            backgroundColor = Color.White,
            elevation = 0.dp,
            title = { Text("Route Details Plan") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "back")
                }
            }
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("From: 09 July", fontSize = 13.sp, color = Color.Gray)
            Spacer(Modifier.width(8.dp))
            Text("To: 11 July", fontSize = 13.sp, color = Color.Gray)
        }

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent
        ) {
            plans.forEachIndexed { index, plan ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Text(
                        plan.dateLabel,
                        modifier = Modifier.padding(vertical = 12.dp),
                        fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val offset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val alpha = (1f - max(0f, offset.absoluteValue).coerceAtMost(1f) * 0.7f)
            DayPlanList(plans[page], Modifier.alpha(alpha))
        }
    }
}


@Composable
private fun DayPlanList(plan: DayPlan, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(plan.places) { i, place ->
            val bg = pastelColors[i % pastelColors.size]
            PlaceCard(place, bg)
        }
    }
}

@Composable
private fun PlaceCard(place: Place, bgColor: Color) {
    Row(verticalAlignment = Alignment.Top) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(Modifier.size(14.dp)) {
                drawCircle(color = Color(0xFFFD8771))
            }
            Canvas(Modifier
                .width(2.dp)
                .height(96.dp)) {
                val dash = PathEffect.dashPathEffect(floatArrayOf(6f, 6f))
                drawLine(
                    color = Color(0xFFE6E6E6),
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = 2f,
                    pathEffect = dash,
                    cap = StrokeCap.Round
                )
            }
        }

        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                place.time,
                fontSize = 15.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(6.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = bgColor,
            ) {
                Column(Modifier.padding(18.dp).fillMaxSize()) {
                    Text(place.title, fontSize = 17.sp, fontWeight = FontWeight.Bold)

                    Spacer(Modifier.height(4.dp))

                    Text(place.subtitle, fontSize = 15.sp, color = Color.Gray)
                }
            }
        }
    }
}

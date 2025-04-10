package com.example.clcred.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.example.clcred.R
import com.example.clcred.model.dataClass.SliderItem
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.math.absoluteValue

@Composable
fun ImageSlider(items: List<SliderItem>) {
    val pager = rememberPagerState(initialPage = 0) {
        items.size
    }

    LaunchedEffect(pager) {
        while (true) {
            delay(5000)
            val nextPage = (pager.currentPage + 1) % pager.pageCount
            pager.animateScrollToPage(nextPage)
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pager,
            contentPadding = PaddingValues(horizontal = 100.dp, vertical = 0.dp),
        ) { index ->
            CardContent(index, pager, items[index].imageRes)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = items[pager.currentPage].brand.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFA8A0A0),
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            letterSpacing = 1.5.sp,
        )
        Text(
            text = items[pager.currentPage].name,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
        if (items[pager.currentPage].isFeatured) {
            Text(
                text = stringResource(R.string.featured).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray,
                fontSize = 8.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center,
                letterSpacing = 1.5.sp,
            )
        }
    }
}

@Composable
fun CardContent(index: Int, pager: PagerState, image: Int) {
    val pageOffset = (pager.currentPage - index) + pager.currentPageOffsetFraction
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .graphicsLayer {
                lerp(
                    start = 0.45f.dp,
                    stop = 1f.dp,
                    fraction = 1.5f - pageOffset.absoluteValue.coerceIn(0f, 1.5f)
                ).also { scale ->
                    scaleX = scale.value
                    scaleY = scale.value
                }
                alpha = lerp(
                    start = 0.5f.dp,
                    stop = 1f.dp,
                    fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                ).value
            }
    )
}

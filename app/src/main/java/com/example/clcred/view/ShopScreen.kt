package com.example.clcred.view

import androidx.compose.foundation.Canvas
import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.clcred.R
import com.example.clcred.databinding.NeoButtonBinding
import com.example.clcred.model.dataClass.SpinItem
import com.example.clcred.model.dataClass.StoreSpecialItem
import com.example.clcred.model.dataClass.brandFocusList
import com.example.clcred.model.dataClass.handpickList
import com.example.clcred.model.dataClass.sliderItems
import com.example.clcred.model.dataClass.spinItems
import com.example.clcred.model.dataClass.storeSpecialItems
import kotlinx.coroutines.delay
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(navController: NavController) {


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TopBarTitle()
                },
                navigationIcon = {
                    TopBarNavIcon()
                },
                actions = {
                    TopBarActions()
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                CollectionBrandHeader()
            }
            item {
                DealOfTheDayContainer()
            }
            item {
                BeginShopBanner()
            }
            item {
                SpecialFirstPurchaseContainer()
            }
            item {
                LightningDealContainer()
            }
            item {
                HandpickedCurationContainer()
            }
            item {
                SpinTheWheelContainer()
            }
            item {
                BrandInFocusContainer()
            }
            item {
                BrandIconGrid(onClick = {
                    navController.navigate("Store")
                })

            }
            item {
                GadgetCentralBox()
            }
            item {
                MileStoneBox()
            }
            item {
                LastText()
            }
        }
    }
}

@Composable
fun TopBarActions(isFromShop: Boolean = true) {
    if (isFromShop) {
        CustomActionIconButton(
            onClick = { },
            contentDescription = "Orders",
            iconResource = R.drawable.ic_box,
            iconSize = 18.dp
        )
    }
    Spacer(modifier = Modifier.width(12.dp))
    CustomActionIconButton(
        onClick = { },
        contentDescription = "Basket",
        iconResource = R.drawable.ic_basket,
        iconSize = 22.dp
    )
    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
fun TopBarNavIcon(onClick: () -> Unit = {}) {
    IconButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .semantics { contentDescription = "Back" }
            .padding(start = 10.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun BrandInFocusContainer() {
    val scrollState = rememberLazyListState()
    val autoScroll by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        while (autoScroll) {
            delay(2000)
            if (scrollState.firstVisibleItemIndex == brandFocusList.lastIndex) {
                scrollState.animateScrollToItem(0)
            } else {
                scrollState.animateScrollToItem(scrollState.firstVisibleItemIndex + 1)
            }
        }
    }
    SectionHeaderText(R.string.in_focus)
    Space()
    LazyRow(
        state = scrollState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        items(brandFocusList) { item ->
            BrandInFocusCard(item)
        }
    }
    Space()
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 18.dp),
        thickness = 0.5.dp,
        color = Color.Gray
    )
}

@Composable
private fun SectionHeaderText(header: Int) {
    Text(
        text = stringResource(header).uppercase(Locale.getDefault()),
        style = MaterialTheme.typography.bodySmall,
        color = Color(0xFF000000),
        fontFamily = FontFamily.SansSerif,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        textAlign = TextAlign.Left,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun SpinTheWheelContainer() {
    SectionHeaderText(R.string.spin)
    Space()
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        items(spinItems) { item ->
            SpinItemCard(item)
        }
    }
    Space()
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 18.dp),
        thickness = 0.5.dp,
        color = Color.Gray
    )
    Space()
}

@Composable
private fun HandpickedCurationContainer() {
    SectionHeaderText(R.string.discover)
    Space()
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        items(handpickList) { item ->
            Image(
                painter = painterResource(item),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 18.dp),
        thickness = 0.5.dp,
        color = Color.Gray
    )
}

@Composable
private fun LightningDealContainer() {
    SectionHeaderText(R.string.light_deals)
    Spacer(modifier = Modifier.height(18.dp))
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 12.dp),
        painter = painterResource(R.drawable.ic_cred_drop),
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
    )
    Spacer(modifier = Modifier.height(18.dp))
}

@Composable
private fun SpecialFirstPurchaseContainer() {
    SectionHeaderText(R.string.first_purchase)
    Spacer(modifier = Modifier.height(18.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            horizontal = 14.dp
        )
    ) {
        items(storeSpecialItems) { product ->
            ProductDealCard(product = product)
        }
    }
    Spacer(modifier = Modifier.height(18.dp))
}

@Composable
private fun BeginShopBanner() {
    SectionHeaderText(R.string.begin_journey)
    Spacer(modifier = Modifier.height(18.dp))
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(R.drawable.banner_shop),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Spacer(modifier = Modifier.height(18.dp))
}

@Composable
private fun DealOfTheDayContainer() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.deal_of).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(percent = 50)
                    )
                    .height(18.dp)
            ) {
                Text(
                    text = stringResource(R.string.time_deal).uppercase(Locale.getDefault()),
                    style = TextStyle(fontSize = 10.sp),
                    letterSpacing = 1.5.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
        Text(
            text = stringResource(R.string.day).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
    Spacer(modifier = Modifier.height(32.dp))
    ImageSlider(sliderItems)
    Spacer(modifier = Modifier.height(18.dp))
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawArc(
                color = Color.Gray,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 0.5.dp.toPx(), cap = StrokeCap.Round),
                topLeft = Offset(0f, 0f),
                size = Size(canvasWidth, canvasHeight)
            )
        }
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                val binding = NeoButtonBinding.inflate(
                    LayoutInflater.from(context),
                    null,
                    false
                )
                binding.root
            }
        )
    }
    Spacer(modifier = Modifier.height(25.dp))
    UsingCoinText()
    Space()
}

@Composable
private fun CollectionBrandHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        IconTextBox(
            icon = painterResource(R.drawable.ic_collection),
            text = stringResource(R.string.collections),
            modifier = Modifier.weight(1f),
            contentDescription = "collections",
            onClick = {}
        )
        IconTextBox(
            icon = painterResource(R.drawable.ic_brands),
            text = stringResource(R.string.brands),
            modifier = Modifier.weight(1f),
            contentDescription = "brands",
            onClick = {}
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun UsingCoinText() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.by_using),
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFA8A0A0),
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = 10.sp,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .height(25.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 3.dp, horizontal = 12.dp)
                    .height(40.dp)
                    .width(40.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .border(
                        width = 0.5.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(percent = 50)
                    )
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "100", style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFA8A0A0),
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.wrapContentWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 10.sp
                )
            }
            Image(painter = painterResource(R.drawable.ic_coin_bg), contentDescription = null)
        }
    }

}

@Composable
fun LastText() {
    val annotatedString = buildAnnotatedString {

        withStyle(style = SpanStyle(color = Color(0xFFB8B8B8))) {
            append(stringResource(R.string.manage))
        }

        withStyle(
            style = SpanStyle(
                color = Color(0xFF616161),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(stringResource(R.string.dream))
        }
    }

    Text(
        text = annotatedString,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun MileStoneBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 12.dp)
            .background(color = Color(0xFFF3F2EE)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = stringResource(R.string.amount).uppercase(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.SansSerif
                    )
                )
                Space()
                Text(
                    text = stringResource(R.string.join_text),
                    style = TextStyle(
                        lineHeight = 20.sp,
                        fontSize = 12.sp,
                        color = Color(0xFF92918E)
                    )
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_coin),
                modifier = Modifier
                    .width(100.dp)
                    .height(75.dp),
                contentDescription = "logo"
            )
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun GadgetCentralBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            contentPadding = PaddingValues(
                horizontal = 150.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(spinItems) { item ->
                SpinItemCard(item)
            }
        }
    }
    Space()
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 18.dp),
        thickness = 0.5.dp,
        color = Color.Gray
    )
    Space()
}

@Composable
fun BrandInFocusCard(item: StoreSpecialItem) {
    Box(
        modifier = Modifier
            .size(width = 145.dp, height = 250.dp)
            .clip(RoundedCornerShape(50))
            .background(color = Color(0xFFE18E12)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .padding(top = 8.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(item.imageRes),
                    contentDescription = "Product",
                    modifier = Modifier.size(130.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(item.brandImage),
                contentDescription = "Brand",
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "UP TO ${item.discount} % OFF",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                ),
                maxLines = 1,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun SpinItemCard(item: SpinItem) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(320.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
                    .background(Color(0xFFFFDAA6))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .background(Color(0xFFDB983F))
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = item.brand.uppercase(Locale.getDefault()),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.spin),
                    contentDescription = "Spin icon",
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .width(24.dp),
                thickness = 0.5.dp,
                color = Color.DarkGray
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "₹${item.price}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "₹${item.oldPrice}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .offset(y = 40.dp)
            ) {
                Image(
                    painter = painterResource(item.imageRes),
                    contentDescription = "Product image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                )
                if (item.isLive) {
                    Row(
                        modifier = Modifier
                            .background(Color.White)
                            .align(Alignment.BottomEnd),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(modifier = Modifier.width(3.dp))
                        Box(
                            modifier = Modifier
                                .size(5.dp)
                                .background(
                                    color = Color.Red,
                                    shape = CircleShape
                                )
                        )

                        Spacer(modifier = Modifier.width(3.dp))

                        Text(
                            text = stringResource(R.string.live).uppercase(Locale.getDefault()),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 8.sp
                            ),
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column {
                            Text(
                                text = stringResource(R.string.launching).uppercase(Locale.getDefault()),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 8.sp
                                )
                            )
                            Text(
                                text = item.launchDateTime.uppercase(Locale.getDefault()),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 8.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .background(
                                    color = Color.White,
                                    shape = RectangleShape,
                                )
                                .border(
                                    color = Color.Black,
                                    width = 0.5.dp
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.padlock),
                                contentDescription = "Locked",
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable(onClick = { })
                            .border(
                                width = 0.dp,
                                color = Color.White,
                                shape = CircleShape,
                            )
                            .align(Alignment.BottomStart),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_bell),
                                contentDescription = "Notify",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Text(
                                text = stringResource(R.string.notify),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProductDealCard(product: StoreSpecialItem) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Image(
            painter = painterResource(product.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.brand.uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            maxLines = 1,

            )

        Text(
            text = product.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "₹${product.price}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "₹${product.oldPrice}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.LineThrough
                ),
                color = Color.Gray
            )

            Text(
                text = "${product.discount}%",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = Color.Green
                )
            )
        }
    }
}

@Composable
fun IconTextBox(
    onClick: () -> Unit,
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
    contentDescription: String,
) {
    Box(
        modifier = modifier
            .border(1.dp, Color(0xFFC0BABA), RectangleShape)
            .clip(RectangleShape)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }

    }
}

@Composable
fun CustomActionIconButton(
    onClick: () -> Unit,
    contentDescription: String,
    iconResource: Int,
    iconSize: Dp = 24.dp,
    borderColor: Color = Color(0xFFD3D0D0),
    size: Dp = 34.dp
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(0.5.dp, borderColor, CircleShape)
            .size(size)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.semantics { this.contentDescription = contentDescription }
        ) {
            Icon(
                modifier = Modifier.size(iconSize),
                painter = painterResource(iconResource),
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
fun TopBarTitle() {
    Row {
        Text(
            text = stringResource(R.string.Cred).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            fontFamily = FontFamily.SansSerif,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = stringResource(R.string.Store).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            letterSpacing = 1.5.sp,
        )
    }
}

@Composable
fun BrandIconGrid(onClick: () -> Unit) {
    SectionHeaderText(R.string.loved_brand)
    Space()
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BrandIconItem(
                imageResId = R.drawable.adonit,
                contentDescription = ""
            )
            VerticalDivider(
                modifier = Modifier
                    .height(100.dp)
                    .width(0.25.dp),
                color = Color.LightGray
            )
            BrandIconItem(
                imageResId = R.drawable.foot_locker,
                contentDescription = ""
            )
            VerticalDivider(
                modifier = Modifier
                    .height(100.dp)
                    .width(0.25.dp),
                color = Color.LightGray
            )
            BrandIconItem(
                imageResId = R.drawable.vercel,
                contentDescription = ""
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(0.25.dp),
            color = Color.LightGray
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BrandIconItem(
                imageResId = R.drawable.lovi,
                contentDescription = ""
            )
            VerticalDivider(
                modifier = Modifier
                    .height(100.dp)
                    .width(0.25.dp),
                color = Color.LightGray
            )
            BrandIconItem(
                imageResId = R.drawable.ubiquiti,
                contentDescription = ""
            )
            VerticalDivider(
                modifier = Modifier
                    .height(100.dp)
                    .width(0.25.dp),
                color = Color.LightGray
            )
            BrandIconItem(
                imageResId = R.drawable.adonit,
                contentDescription = ""
            )
        }
    }
    Spacer(modifier = Modifier.height(18.dp))
}

@Composable
private fun Space() {
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun BrandIconItem(
    imageResId: Int,
    contentDescription: String
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Transparent, shape = RectangleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(65.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Fit
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewShopScreen() {
    DealOfTheDayContainer()
}



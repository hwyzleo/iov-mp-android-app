package net.hwyz.iov.mp.app.ui.page.vehicle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.ui.page.login.LoginIntent
import net.hwyz.iov.mp.app.ui.page.login.LoginViewEvent
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.widget.bar.CustomProgressBar
import net.hwyz.iov.mp.app.ui.widget.bar.SNACK_ERROR
import net.hwyz.iov.mp.app.ui.widget.bar.SNACK_INFO
import net.hwyz.iov.mp.app.ui.widget.bar.popupSnackBar
import net.hwyz.iov.mp.app.ui.widget.button.CircularImageButton
import net.hwyz.iov.mp.app.ui.widget.button.ImageButton

/**
 * 爱车页面
 */
@Composable
fun VehicleView(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: VehicleViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    val coroutineState = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.viewEvents.collect {
            when (it) {
                is VehicleViewEvent.PopBack -> navCtrl.popBackStack()
                is VehicleViewEvent.ErrorMessage -> popupSnackBar(
                    coroutineState,
                    scaffoldState,
                    label = SNACK_ERROR,
                    it.message
                )

                is VehicleViewEvent.InfoMessage -> popupSnackBar(
                    coroutineState,
                    scaffoldState,
                    label = SNACK_INFO,
                    it.message
                )
            }
        }
        viewModel.intent(VehicleIntent.OnLaunched)
    }
    VehicleScreen(
        navCtrl = navCtrl,
        intent = { intent: VehicleIntent -> viewModel.intent(intent) },
        viewState = viewStates
    )
}

@Composable
fun VehicleScreen(
    navCtrl: NavHostController,
    intent: (VehicleIntent) -> Unit,
    viewState: VehicleState
) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .padding(20.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "我的爱车", fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(5.dp))
                    ImageButton(
                        painter = painterResource(id = R.drawable.icon_arrow_up_down),
                        contentDescription = "选择车辆",
                        modifier = Modifier
                            .size(20.dp),
                    ) {

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    ImageButton(
                        painter = painterResource(id = R.drawable.icon_scan_qrcode),
                        contentDescription = "扫描二维码",
                        modifier = Modifier
                            .size(30.dp),
                    ) {

                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    ImageButton(
                        painter = painterResource(id = R.drawable.icon_setting),
                        contentDescription = "车辆设置",
                        modifier = Modifier
                            .size(30.dp),
                    ) {

                    }
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_vehicle),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.icon_arrow_right),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.icon_cellular),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.icon_bluetooth),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "停放中")
                    Image(
                        painter = painterResource(id = R.drawable.icon_vertical_line),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "无法获取位置")
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            item {
                Spacer(modifier = Modifier.height(60.dp))
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "798", fontSize = 50.sp, fontWeight = FontWeight.Bold)
                    Column(modifier = Modifier.padding(start = 10.dp, top = 15.dp)) {
                        Text(text = "km")
                        Text(text = "WLTC")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Spacer(modifier = Modifier.width(30.dp))
                    Column(modifier = Modifier.width(120.dp)) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_battery),
                                contentDescription = "",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(text = "210", fontSize = 18.sp)
                            Text(
                                text = "km",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 3.dp, top = 4.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.icon_vertical_line),
                                contentDescription = "",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "85%",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        CustomProgressBar(
                            progress = 0.85f,
                            height = 5.dp,
                            color = Color(0xFF65C466)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(modifier = Modifier.width(120.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_battery),
                                contentDescription = "",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(text = "588", fontSize = 18.sp)
                            Text(
                                text = "km",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 3.dp, top = 4.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.icon_vertical_line),
                                contentDescription = "",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "57%",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        CustomProgressBar(
                            progress = 0.85f,
                            height = 5.dp,
                            color = Color(0xFF3478F6)
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                }
            }
            item {
                AsyncImage(
                    model = "https://pic.imgdb.cn/item/65f1bd8b9f345e8d03cf10cc.png",
                    contentDescription = "车体图",
                    modifier = Modifier
                        .size(200.dp),
                    placeholder = painterResource(R.drawable.default_vehicle),
                    error = painterResource(R.drawable.default_vehicle)
                )
            }
            item {
                Row {
                    Spacer(modifier = Modifier.width(20.dp))
                    CircularImageButton(
                        imageRes = R.drawable.icon_lock,
                        isLoading = false
                    ) {

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    CircularImageButton(
                        imageRes = R.drawable.icon_window,
                        isLoading = false
                    ) {

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    CircularImageButton(
                        imageRes = R.drawable.icon_trunk,
                        isLoading = false
                    ) {

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    CircularImageButton(
                        imageRes = R.drawable.icon_vehicle_search,
                        isLoading = viewState.findVehicleLoading,
                        countdownSeconds = viewState.findVehicleExecuteTime
                    ) {
                        intent(VehicleIntent.FindVehicle("HWYZTEST000000001"))
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun VehicleScreenPreview() {
    VehicleScreen(
        navCtrl = rememberNavController(),
        intent = {},
        viewState = VehicleState()
    )
}
package net.hwyz.iov.mp.app.ui.page.vehicle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.ui.page.RouteName
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.widget.button.ImageButton
import net.hwyz.iov.mp.app.utils.RouteUtils

@Composable
fun VehicleViewStatus(
    navCtrl: NavHostController, intent: (VehicleIntent) -> Unit, viewState: VehicleState
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
                Row {
                    Text(text = "开源汽车", fontSize = 19.sp, fontWeight = FontWeight.Bold)
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
                        contentDescription = "设置",
                        modifier = Modifier
                            .size(30.dp),
                    ) {

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun VehicleViewStatusPreview() {
    VehicleViewStatus(
        navCtrl = rememberNavController(), intent = {}, viewState = VehicleState()
    )
}
package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleModelConfigIntent
import net.hwyz.iov.mp.app.theme.AppTheme
import java.math.BigDecimal

/**
 * 车辆车型配置页 - 轮胎
 *
 * @author hwyz_leo
 */
@Composable
fun VehicleModelConfigPageWheel(
    navCtrl: NavHostController,
    wheels: List<SaleModelConfig>,
    selectWheel: String,
    intent: (VehicleModelConfigIntent) -> Unit
) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .onGloballyPositioned {
                if (selectWheel == "" && wheels.isNotEmpty()) {
                    intent(VehicleModelConfigIntent.OnTapWheel(code = wheels.first().typeCode))
                }
            }
    ) {
        val selectedTabIndex = remember { mutableStateOf(0) }
        Column {
            wheels.forEachIndexed { index, wheel ->
                if (selectedTabIndex.value == index) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(wheel.typeImage[0])
                            .setHeader("User-Agent", "Mozilla/5.0")
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(120.dp))
            Row(modifier = Modifier.padding(20.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                wheels.forEachIndexed { index, exterior ->
                    Text(
                        exterior.typeName,
                        modifier = Modifier.clickable {
                            selectedTabIndex.value = index
                            intent(VehicleModelConfigIntent.OnTapWheel(code = exterior.typeCode))
                        },
                        fontWeight = if (selectedTabIndex.value == index) FontWeight.Bold else FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun VehicleModelConfigPageWheelPreview() {
    VehicleModelConfigPageWheel(
        navCtrl = rememberNavController(),
        wheels = listOf(
            SaleModelConfig(
                saleCode = "H01",
                type = "WHEEL",
                typeCode = "CL04",
                typeName = "21寸轮毂(四季胎)枪灰色",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/67067e41d29ded1a8cb3ac99.png"),
                typeDesc = "标配倍耐力Scorpion轮胎",
                typeParam = ""
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "WHEEL",
                typeCode = "CL03",
                typeName = "21寸轮毂(四季胎)高亮黑",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/67067e41d29ded1a8cb3ac99.png"),
                typeDesc = "标配倍耐力Scorpion轮胎",
                typeParam = ""
            )
        ),
        selectWheel = "",
        intent = {}
    )
}
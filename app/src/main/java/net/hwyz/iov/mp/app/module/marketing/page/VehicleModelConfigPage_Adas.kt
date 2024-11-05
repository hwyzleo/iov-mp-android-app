package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleModelConfigIntent
import net.hwyz.iov.mp.app.theme.AppTheme
import java.math.BigDecimal

/**
 * 车辆车型配置页 - 智驾
 *
 * @author hwyz_leo
 */
@Composable
fun VehicleModelConfigPageAdas(
    navCtrl: NavHostController,
    adases: List<SaleModelConfig>,
    selectAdas: String,
    intent: (VehicleModelConfigIntent) -> Unit
) {
    var firstAdas = ""
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .onGloballyPositioned {
                if (selectAdas == "") {
                    intent(VehicleModelConfigIntent.OnTapAdas(code = firstAdas))
                }
            }
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            for (adas in adases) {
                Box(
                    modifier = Modifier
                        .height(180.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(android.graphics.Color.parseColor("#fbfbfb")))
                        .border(
                            width = 1.dp,
                            color = if (selectAdas == adas.typeCode) Color(
                                android.graphics.Color.parseColor(
                                    "#fbe8c9"
                                )
                            ) else Color.LightGray,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .clickable {
                            intent(VehicleModelConfigIntent.OnTapAdas(code = adas.typeCode))
                        }
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = adas.typeName)
                        Row {
                            if (adas.typePrice.toInt() > 0) {
                                Text(text = "￥${adas.typePrice}", fontSize = 14.sp)
                            } else {
                                Text(text = "价格已包含", fontSize = 14.sp)
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(adas.typeImage[0])
                                    .setHeader("User-Agent", "Mozilla/5.0")
                                    .build(),
                                contentDescription = "",
                                modifier = Modifier.height(100.dp)
                            )
                        }
                        Text(
                            text = adas.typeDesc,
                            fontSize = 12.sp,
                            color = AppTheme.colors.fontSecondary
                        )
                    }
                }
                if (firstAdas == "") {
                    firstAdas = adas.typeCode
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Preview
@Composable
fun VehicleModelConfigPageAdasPreview() {
    VehicleModelConfigPageAdas(
        navCtrl = rememberNavController(),
        adases = listOf(
            SaleModelConfig(
                saleCode = "H01",
                type = "ADAS",
                typeCode = "X02",
                typeName = "高价智驾",
                typePrice = BigDecimal(20000),
                typeImage = listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"),
                typeDesc = "",
                typeParam = ""
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "ADAS",
                typeCode = "X00",
                typeName = "标准智驾",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/670674cfd29ded1a8cac9cb3.png"),
                typeDesc = "",
                typeParam = ""
            )
        ),
        selectAdas = "",
        intent = {}
    )
}
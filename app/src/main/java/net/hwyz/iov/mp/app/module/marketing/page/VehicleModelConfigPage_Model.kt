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
 * 车辆车型配置页 - 车型
 *
 * @author hwyz_leo
 */
@Composable
fun VehicleModelConfigPageModel(
    navCtrl: NavHostController,
    models: List<SaleModelConfig>,
    selectModel: String,
    intent: (VehicleModelConfigIntent) -> Unit
) {
    var firstModel = ""
    var firstModelName = ""
    var firstModelPrice = BigDecimal.ZERO
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .onGloballyPositioned {
                if (selectModel == "") {
                    intent(
                        VehicleModelConfigIntent.OnTapModel(
                            code = firstModel,
                            name = firstModelName,
                            price = firstModelPrice
                        )
                    )
                }
            }
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            for (model in models) {
                Box(
                    modifier = Modifier
                        .height(180.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(android.graphics.Color.parseColor("#fbfbfb")))
                        .border(
                            width = 1.dp,
                            color = if (selectModel == model.typeCode) Color(
                                android.graphics.Color.parseColor(
                                    "#fbe8c9"
                                )
                            ) else Color.LightGray,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .clickable {
                            intent(
                                VehicleModelConfigIntent.OnTapModel(
                                    code = model.typeCode,
                                    name = model.typeName,
                                    price = model.typePrice
                                )
                            )
                        }
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = model.typeName)
                        Row {
                            Text(text = "￥${model.typePrice}", fontSize = 14.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(model.typeImage[0])
                                    .setHeader("User-Agent", "Mozilla/5.0")
                                    .build(),
                                contentDescription = "",
                                modifier = Modifier.height(100.dp)
                            )
                        }
                        Text(
                            text = model.typeDesc,
                            fontSize = 12.sp,
                            color = AppTheme.colors.fontSecondary
                        )
                    }
                }
                if (firstModel == "") {
                    firstModel = model.typeCode
                    firstModelName = model.typeName
                    firstModelPrice = model.typePrice
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Preview
@Composable
fun VehicleModelConfigPageModelPreview() {
    VehicleModelConfigPageModel(
        navCtrl = rememberNavController(),
        models = listOf(
            SaleModelConfig(
                saleCode = "H01",
                type = "MODEL",
                typeCode = "H0106",
                typeName = "寒01 6座版",
                typePrice = BigDecimal(88888),
                typeImage = listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"),
                typeDesc = "2-2-2六座，双侧零重力航空座椅，行政奢华",
                typeParam = ""
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "MODEL",
                typeCode = "H0107",
                typeName = "寒01 7座版",
                typePrice = BigDecimal(88888),
                typeImage = listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"),
                typeDesc = "2-2-3七座，二排超宽通道，二三排可放平",
                typeParam = ""
            )
        ),
        selectModel = "",
        intent = { }
    )
}
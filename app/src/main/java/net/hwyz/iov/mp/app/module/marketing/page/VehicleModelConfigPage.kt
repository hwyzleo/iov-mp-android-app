package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.component.bar.TopBackTitleBar
import net.hwyz.iov.mp.app.component.button.RoundedCornerButton
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleModelConfigIntent
import net.hwyz.iov.mp.app.module.marketing.state.VehicleModelConfigState
import net.hwyz.iov.mp.app.module.marketing.viewmodel.VehicleModelConfigViewModel
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.utils.RouteUtils.back
import java.math.BigDecimal

/**
 * 车辆车型配置页
 *
 * @author hwyz_leo
 */
@Composable
fun VehicleModelConfigPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: VehicleModelConfigViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    LaunchedEffect(Unit) {
        viewModel.intent(VehicleModelConfigIntent.OnLaunched)
    }
    VehicleModelConfigPageContent(
        navCtrl = navCtrl,
        viewStates = viewStates,
        intent = viewModel::intent
    )
}

@Composable
fun VehicleModelConfigPageContent(
    navCtrl: NavHostController,
    viewStates: VehicleModelConfigState,
    intent: (VehicleModelConfigIntent) -> Unit
) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val tabs = listOf("车型", "备胎", "外观", "车轮", "内饰", "智驾")
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
    ) {
        Column {
            TopBackTitleBar(title = "配置爱车", onBack = { navCtrl.back() })
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                when (selectedTabIndex.value) {
                    0 -> VehicleModelConfigPageModel(
                        navCtrl = navCtrl,
                        models = viewStates.models,
                        selectModel = viewStates.selectModel,
                        intent = intent
                    )

                    1 -> VehicleModelConfigPageSpareTire(
                        navCtrl = navCtrl,
                        spareTires = viewStates.spareTires,
                        selectSpareTire = viewStates.selectSpareTire,
                        intent = intent
                    )

                    2 -> VehicleModelConfigPageExterior(
                        navCtrl = navCtrl,
                        exteriors = viewStates.exteriors,
                        selectExterior = viewStates.selectExterior,
                        intent = intent
                    )

                    3 -> VehicleModelConfigPageWheel(
                        navCtrl = navCtrl,
                        wheels = viewStates.wheels,
                        selectWheel = viewStates.selectWheel,
                        intent = intent
                    )

                    4 -> VehicleModelConfigPageInterior(
                        navCtrl = navCtrl,
                        interiors = viewStates.interiors,
                        selectInterior = viewStates.selectInterior,
                        intent = intent
                    )

                    5 -> VehicleModelConfigPageAdas(
                        navCtrl = navCtrl,
                        adases = viewStates.adases,
                        selectAdas = viewStates.selectAdas,
                        intent = intent
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                TabRow(
                    selectedTabIndex = selectedTabIndex.value,
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = selectedTabIndex.value == index,
                            onClick = { selectedTabIndex.value = index },
                            selectedContentColor = AppTheme.colors.fontPrimary,
                            unselectedContentColor = AppTheme.colors.fontSecondary,
                            modifier = Modifier.background(Color.White)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(modifier = Modifier.padding(20.dp)) {
                    Text("￥88888", fontSize = 18.sp, modifier = Modifier.padding(top = 6.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    RoundedCornerButton(text = "保存心愿单", modifier = Modifier.width(120.dp)) {

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    RoundedCornerButton(
                        text = "立即订购",
                        modifier = Modifier.width(120.dp),
                        bgColor = Color.Black,
                        textColor = Color.White
                    ) {

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun VehicleModelConfigPagePreview() {
    VehicleModelConfigPageContent(
        navCtrl = rememberNavController(),
        viewStates = VehicleModelConfigState(
            models = mutableListOf(
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
            )
        ),
        intent = {}
    )
}
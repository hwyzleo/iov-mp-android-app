package net.hwyz.iov.mp.app.data.bean

import java.math.BigDecimal

// 通用TSP响应
fun mockTspResponse(): TspResponse<Void> = TspResponse(
    code = 0,
    message = "",
    ts = System.currentTimeMillis(),
    data = null
)

fun mockTspResponse(data: Any): TspResponse<Any> = TspResponse(
    code = 0,
    message = "",
    ts = System.currentTimeMillis(),
    data = data
)

// 登录相应
fun mockLoginResponse(): LoginResponse = LoginResponse(
    mobile = "13917288107",
    nickname = "hwyz_leo",
    avatar = "https://pic.imgdb.cn/item/66e667a0d9c307b7e93075e8.png",
    token = "zgZA0dO9gTbhSb6PDBXCb_0mxFq",
    tokenExpires = System.currentTimeMillis() + 1000000000,
    refreshToken = "rWtoZhVVf6mZW-t1hhqkNazR0r92KkhxDItf05jfQYChT6SrnFi2IXaXD02irjVc",
    refreshTokenExpires = System.currentTimeMillis() + 1000000000
)

// 车辆销售订单列表
fun mockVehicleSaleOrderList(): List<VehicleSaleOrder> = listOf(
    VehicleSaleOrder(
        orderNum = "ORDERNUM001",
        orderState = 1,
        displayName = "寒01七座版"
    )
)

// 销售车型
fun mockSaleModelList(): List<SaleModelConfig> = listOf(
    SaleModelConfig(
        "H01", "ADAS", "X02", "高阶智驾", BigDecimal.valueOf(3000),
        listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"), "", ""
    ),
    SaleModelConfig(
        "H01", "ADAS", "X00", "标准智驾", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"), "", ""
    ),
    SaleModelConfig(
        "H01", "INTERIOR", "NS03", "霜雪白内饰", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/670685e4d29ded1a8cb9c55f.png"), "", "#dcdcd6"
    ),
    SaleModelConfig(
        "H01", "INTERIOR", "NS02", "珊瑚橙内饰", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/670687ecd29ded1a8cbb5280.png"), "", "#a35d31"
    ),
    SaleModelConfig(
        "H01", "INTERIOR", "NS01", "乌木黑内饰", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/670688dbd29ded1a8cbc1321.png"), "", "#424141"
    ),
    SaleModelConfig(
        "H01", "WHEEL", "CL04", "21寸轮毂(四季胎)枪灰色", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/67067e41d29ded1a8cb3ac99.png"), "标配倍耐力Scorpion轮胎", ""
    ),
    SaleModelConfig(
        "H01", "WHEEL", "CL03", "21寸轮毂(四季胎)高亮黑", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/67067e41d29ded1a8cb3ac99.png"), "标配倍耐力Scorpion轮胎", ""
    ),
    SaleModelConfig(
        "H01", "EXTERIOR", "WS06", "冰川白车漆", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/67064442d29ded1a8c8801fa.png"), "", "#e8e8e7"
    ),
    SaleModelConfig(
        "H01", "EXTERIOR", "WS05", "银河灰车漆", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/6706473ad29ded1a8c8aa3a9.png"), "", "#868888"
    ),
    SaleModelConfig(
        "H01", "EXTERIOR", "WS04", "星尘银车漆", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/6706487dd29ded1a8c8bb358.png"), "", "#cbcbce"
    ),
    SaleModelConfig(
        "H01", "EXTERIOR", "WS03", "天际蓝车漆", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/67064bc8d29ded1a8c8e461b.png"), "", "#4681ad"
    ),
    SaleModelConfig(
        "H01", "EXTERIOR", "WS02", "翡翠绿车漆", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/67065b68d29ded1a8c999b62.png"), "", "#3a5337"
    ),
    SaleModelConfig(
        "H01", "EXTERIOR", "WS01", "墨玉黑车漆", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"), "", "#0f0e11"
    ),
    SaleModelConfig(
        "H01", "SPARE_TIRE", "X05", "外挂式全尺寸备胎", BigDecimal.valueOf(6000),
        listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"), "含备胎车长5295毫米", ""
    ),
    SaleModelConfig(
        "H01", "SPARE_TIRE", "X00", "无备胎", BigDecimal.valueOf(0),
        listOf("https://pic.imgdb.cn/item/670674cfd29ded1a8cac9cb3.png"), "车长5050毫米", ""
    ),
    SaleModelConfig(
        "H01", "MODEL", "H0106", "寒01六座版", BigDecimal.valueOf(88888),
        listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"), "2-2-2六座，双侧零重力航空座椅，行政奢华", ""
    ),
    SaleModelConfig(
        "H01", "MODEL", "H0107", "寒01七座版", BigDecimal.valueOf(88888),
        listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"), "2-2-3七座，二排超宽通道，二三排可放平", ""
    )
)

package net.hwyz.iov.mp.app.data.bean

// 通用TSP响应
fun mockTspResponse(): TspResponse<Void> = TspResponse(
    code = 0,
    message = "",
    ts = System.currentTimeMillis(),
    data = null
)

// 登录相应
fun mockLoginResponse(): TspResponse<LoginResponse> = TspResponse(
    code = 0,
    message = "",
    ts = System.currentTimeMillis(),
    data = LoginResponse(
        mobile = "13917288107",
        nickname = "hwyz_leo",
        avatar = "https://pic.imgdb.cn/item/66e667a0d9c307b7e93075e8.png",
        token = "zgZA0dO9gTbhSb6PDBXCb_0mxFq",
        tokenExpires = System.currentTimeMillis() + 1000000000,
        refreshToken = "rWtoZhVVf6mZW-t1hhqkNazR0r92KkhxDItf05jfQYChT6SrnFi2IXaXD02irjVc",
        refreshTokenExpires = System.currentTimeMillis() + 1000000000
    )
)
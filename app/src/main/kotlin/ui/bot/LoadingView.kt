package com.youngerhousea.miraicompose.app.ui.bot

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.youngerhousea.miraicompose.app.utils.SkiaImageDecode
import com.youngerhousea.miraicompose.core.component.bot.SolvePicCaptcha
import com.youngerhousea.miraicompose.core.component.bot.SolveSliderCaptcha
import com.youngerhousea.miraicompose.core.component.bot.SolveUnsafeDeviceLoginVerify

@Composable
fun SolvePicCaptchaUi(solvePicCaptcha: SolvePicCaptcha) {
    val (value, setValue) = remember { mutableStateOf("") }
    Column {
        Text("Mirai PicCaptcha(${solvePicCaptcha.bot.id})")
        Image(SkiaImageDecode(solvePicCaptcha.data), null)
        TextField(value = value, onValueChange = setValue)
        Row {
            Button(onClick = solvePicCaptcha::onExcept) {
                Text("Exit")
            }
            Button(onClick = { solvePicCaptcha.onSuccess(value) }) {
                Text("Sure")
            }
        }
    }
}

@Composable
fun SolveSliderCaptchaUi(solveSliderCaptcha: SolveSliderCaptcha) {
    Column {
        QRCode(solveSliderCaptcha.url)
    }
}

@Composable
fun SolveUnsafeDeviceLoginVerifyUi(solveUnsafeDeviceLoginVerify: SolveUnsafeDeviceLoginVerify) = Box(
    Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
) {
    Column {
        Text("Mirai UnsafeDeviceLoginVerify(${solveUnsafeDeviceLoginVerify.bot.id})")

        QRCode(solveUnsafeDeviceLoginVerify.qrCodeUrl)

        Row {
            Button(onClick = solveUnsafeDeviceLoginVerify::onSuccess) {
                Text("Sure")
            }
            Button(onClick = solveUnsafeDeviceLoginVerify::onExcept) {
                Text("Return")
            }
        }
    }
}

@Composable
fun QRCode(string: String) {
    val bitMatrix = remember(string) { QRCodeWriter().encode(string, BarcodeFormat.QR_CODE, 200, 200) }
    Canvas(Modifier.size(200.dp)) {
        for (y in 0 until bitMatrix.height) {
            for (x in 0 until bitMatrix.width) {
                drawRect(
                    if (bitMatrix.get(x, y)) Color.White else Color.Black,
                    topLeft = Offset(x.toFloat(), y.toFloat()),
                    size = Size(1f, 1f)
                )
            }
        }
    }
}
package com.youngerhousea.miraicompose.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import com.youngerhousea.miraicompose.model.Model
import com.youngerhousea.miraicompose.ui.common.SelectEdgeText
import com.youngerhousea.miraicompose.ui.feature.about.About
import com.youngerhousea.miraicompose.ui.feature.bot.Bot
import com.youngerhousea.miraicompose.ui.feature.log.Log
import com.youngerhousea.miraicompose.ui.feature.plugin.PluginV
import com.youngerhousea.miraicompose.ui.feature.setting.Setting
import com.youngerhousea.miraicompose.utils.Component

sealed class Config : Parcelable {
    object Bot : Config()
    object Setting : Config()
    object About : Config()
    object Log : Config()
    object Plugin : Config()
}

class NavHost(
    component: ComponentContext,
    val model: Model,
    val loggerStorage: List<AnnotatedString>
) : Component, ComponentContext by component {

    private val router = router<Config, Component>(
        initialConfiguration = Config.Bot,
        handleBackButton = true,
        componentFactory = { config, componentContext ->
            when (config) {
                is Config.Bot ->
                    Bot(
                        componentContext,
                        model
                    )
                is Config.Setting ->
                    Setting(
                        componentContext,
                    )
                is Config.About ->
                    About(
                        componentContext,
                    )
                is Config.Log ->
                    Log(
                        componentContext,
                        loggerStorage
                    )
                is Config.Plugin -> {
                    PluginV(
                        componentContext
                    )
                }
            }
        },
    )


    val routerState get() = router.state

    @Composable
    override fun render() {
        Children(routerState) { child, config ->
            Surface {
                Row {
                    Column(
                        Modifier
                            .requiredWidth(160.dp)
                            .fillMaxHeight()
                            .background(Color.DarkGray),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        SelectEdgeText(
                            "机器人",
                            isWishWindow = config is Config.Bot
                        ) {
                            router.push(Config.Bot)
                        }
                        SelectEdgeText(
                            "插件",
                            isWishWindow = config is Config.Plugin
                        ) {
                            router.push(Config.Plugin)
                        }
                        SelectEdgeText(
                            "设置",
                            isWishWindow = config is Config.Setting
                        ) {
                            router.push(Config.Setting)
                        }

                        SelectEdgeText(
                            "日志",
                            isWishWindow = config is Config.Log
                        ) {
                            router.push(Config.Log)
                        }
                        SelectEdgeText(
                            "关于",
                            isWishWindow = config is Config.About
                        ) {
                            router.push(Config.About)
                        }
                    }
                    child.render()
                }
            }
        }
    }
}
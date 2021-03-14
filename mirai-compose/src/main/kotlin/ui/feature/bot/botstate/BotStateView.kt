package com.youngerhousea.miraicompose.ui.feature.bot.botstate

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.youngerhousea.miraicompose.console.MiraiComposeLogger.Companion.logs
import com.youngerhousea.miraicompose.ui.common.LogBox
import com.youngerhousea.miraicompose.utils.Component
import net.mamoe.mirai.Bot

class BotState(context: ComponentContext, val bot: Bot) : Component, ComponentContext by context {
    @Composable
    override fun render() {
        BotStateView(bot)
    }

}

@Composable
fun BotStateView(bot: Bot) =
    LogBox(
        bot.logs, Modifier.padding(horizontal = 40.dp, vertical = 20.dp)
    )

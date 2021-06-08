package com.youngerhousea.miraicompose.core.component.bot

import net.mamoe.mirai.utils.MiraiLogger


/**
 * bot的登录的界面
 */
interface InitLogin {
    val logger: MiraiLogger
    fun onLogin(account: Long, password: String)
}
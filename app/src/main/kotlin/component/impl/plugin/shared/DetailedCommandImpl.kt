package com.youngerhousea.miraicompose.component.impl.plugin.shared

import com.arkivanov.decompose.ComponentContext
import com.youngerhousea.miraicompose.component.plugin.shared.DetailedCommand
import net.mamoe.mirai.console.command.Command

class DetailedCommandImpl(
    componentContext: ComponentContext,
    override val commands: List<Command>,
) : DetailedCommand, ComponentContext by componentContext

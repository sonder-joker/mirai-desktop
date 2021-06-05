package com.youngerhousea.miraicompose.component.impl.plugin.shared

import com.arkivanov.decompose.ComponentContext
import com.youngerhousea.miraicompose.component.plugin.shared.DetailedData
import net.mamoe.mirai.console.data.PluginData

class DetailedDataImpl(
    componentContext: ComponentContext,
    override val data: List<PluginData>,
) : DetailedData, ComponentContext by componentContext

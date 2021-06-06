package com.youngerhousea.miraicompose.ui.plugin

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.youngerhousea.miraicompose.component.NavHost
import com.youngerhousea.miraicompose.component.plugin.PluginList
import com.youngerhousea.miraicompose.component.plugin.Plugins
import com.youngerhousea.miraicompose.component.plugin.SpecificPlugin


@OptIn(ExperimentalDecomposeApi::class, ExperimentalAnimationApi::class)
@Composable
fun PluginsUi(plugins: Plugins) {
    Children(plugins.state, animation = crossfadeScale()) { child ->
        when(val ch = child.instance) {
            is PluginList -> PluginListUi(ch)
            is SpecificPlugin -> SpecificPluginUi(ch)
        }
    }
}


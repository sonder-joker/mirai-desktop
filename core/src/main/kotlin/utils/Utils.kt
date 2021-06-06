package com.youngerhousea.miraicompose.core.utils

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.Navigator
import com.arkivanov.decompose.instancekeeper.InstanceKeeper
import com.arkivanov.decompose.instancekeeper.getOrCreate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import java.net.URL
import java.net.URLDecoder
import kotlin.collections.set

fun URL.splitQuery(): Map<String, String> {
    val queryPairs: MutableMap<String, String> = LinkedHashMap()
    val query: String = this.query
    val pairs = query.split("&".toRegex()).toTypedArray()
    for (pair in pairs) {
        val idx = pair.indexOf("=")
        queryPairs[URLDecoder.decode(pair.substring(0, idx), "UTF-8")] =
            URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
    }
    return queryPairs
}


fun <C : Any> Navigator<C>.pushIfNotCurrent(configuration: C) {
    navigate { if (it.last() != configuration) it + configuration else it }
}


class ComponentScope(private val scope: CoroutineScope = MainScope()) :
    InstanceKeeper.Instance,
    CoroutineScope by scope {
    override fun onDestroy() {
        scope.cancel()
    }
}

fun ComponentContext.componentScope() = instanceKeeper.getOrCreate(::ComponentScope)
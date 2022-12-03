package com.dongnaoedu.carhome.init

import android.content.Context
import androidx.startup.Initializer

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class AppInitializer : Initializer<Unit> {
    //这里进行组件初始化工作
    override fun create(context: Context) {
        AppHelper.init(context)
    }

    //返回需要初始化的列表，同时设置 App 启动时依赖库运行的顺序，
    //假设 LibaryC 依赖于 LibaryB，LibaryB 依赖于 LibaryA，App 启动运行时，会先运行 LibaryA 然后运行 LibaryB 最后运行 LibaryC。
    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
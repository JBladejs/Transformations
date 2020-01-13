package com.bladejs.transformations.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.bladejs.transformations.MainGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        with(config) {
            title = "transformations"
            width = 1280
            height = 720
        }
        LwjglApplication(MainGame(), config)
    }
}

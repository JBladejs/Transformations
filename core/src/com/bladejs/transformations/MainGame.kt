package com.bladejs.transformations

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.bladejs.transformations.objects.Planet
import com.bladejs.transformations.types.Vector

class MainGame : ApplicationAdapter() {
    private lateinit var renderer: ShapeRenderer
    private lateinit var camera: OrthographicCamera
    private lateinit var central: Planet
    private lateinit var moon: Planet
    private lateinit var smallMoon: Planet

    private var i = 0

    override fun create() {
        renderer = ShapeRenderer()
        camera = OrthographicCamera()
        camera.setToOrtho(false,1280f, 720f)
        central = Planet(Vector(Gdx.graphics.width / 2f ,Gdx.graphics.height / 2f), 30f, 30f, 0.005f)
        moon = Planet(Vector(Gdx.graphics.width / 2f ,Gdx.graphics.height * 3f / 4f), 15f, 45f, 0.005f, central.position)
        smallMoon = Planet(Vector(Gdx.graphics.width / 2f, moon.position.y + 100f ), 10f, 40f, 0.1f, moon.position)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        camera.update()
        with(renderer) {
            color = Color.BLACK
            projectionMatrix = camera.combined
            begin(ShapeRenderer.ShapeType.Line)
            central.render(this)
            moon.render(this)
            smallMoon.render(this)
            color = Color.GREEN
            //line(central.position.x, central.position.y, moon.position.x, moon.position.y)
            //line(moon.position.x, moon.position.y, smallMoon.position.x, smallMoon.position.y)
            end()
            color = Color.RED
            projectionMatrix = camera.combined
            begin(ShapeRenderer.ShapeType.Filled)
            smallMoon.renderDot(this)
            central.renderDot(this)
            moon.renderDot(this)
            end()
        }
        smallMoon.update()
        central.update()
        moon.update()
    }

    override fun dispose() {
        renderer.dispose()
    }
}

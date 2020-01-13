package com.bladejs.transformations.objects

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.bladejs.transformations.types.Matrix
import com.bladejs.transformations.types.RotationMatrix
import com.bladejs.transformations.types.TranslationMatrix
import com.bladejs.transformations.types.Vector

class Planet(var position: Vector, height: Float, width: Float, private var rotationAngle: Float, private var originPoint: Vector = position) {

    private var vertices: FloatArray
    private var prevX: Float = originPoint.x
    private var prevY: Float = originPoint.y

    init {
        val halfHeight = height / 2
        val halfWidth = width / 2
        vertices = floatArrayOf(position.x - halfWidth, position.y - halfHeight,
                position.x - halfWidth, position.y + halfHeight,
                position.x + halfWidth, position.y + halfHeight,
                position.x + halfWidth, position.y - halfHeight)
    }

    fun update() {
//        changePosition(originPoint.x - prevX, originPoint.y - prevY)
//        prevX = originPoint.x
//        prevY = originPoint.y
        val x = originPoint.x
        val y = originPoint.y
        translate(-x, -y)
        rotate()
        translate(x, y)
    }

    fun render(renderer: ShapeRenderer) {
        renderer.polygon(vertices)
    }

    fun renderDot(renderer: ShapeRenderer) {
        renderer.circle(position.x, position.y, 2f)
    }

    private fun changePosition(x: Float, y: Float) {
        println("$x $y")
        for (i in vertices.indices) {
            println(vertices[i])
            vertices[i] = if (i % 2 == 0) vertices[i] + y else vertices[i] + x
            println(vertices[i])
            println()
        }
        position.x += x
        position.y += y
    }

    private fun translate(x: Float, y: Float) = transform(TranslationMatrix(x, y), 1f)

    private fun rotate() = transform(RotationMatrix(rotationAngle))

    private fun transform(matrix: Matrix, z: Float? = null) {
        var i = 0
        while (i < vertices.size) {
            val vector = if (z == null) Vector(vertices[i], vertices[i + 1])
            else Vector(vertices[i], vertices[i + 1], z)
            val temp = matrix * vector
            vertices[i++] = temp!!.x
            vertices[i++] = temp.y
        }
        val vector = if (z == null) Vector(position.x, position.y)
        else Vector(position.x, position.y, z)
        val temp = matrix * vector
        position.x = temp!!.x
        position.y = temp.y
    }

}
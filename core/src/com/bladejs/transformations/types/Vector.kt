package com.bladejs.transformations.types

data class Vector(var x: Float, var y: Float) {
    var size = 2
    var z: Float? = null
        get() = if (size == 2) null else field

    constructor(x: Float, y: Float, z:Float) : this(x, y) {
        this.z = z
        size = 3
    }
}
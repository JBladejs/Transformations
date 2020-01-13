package com.bladejs.transformations.types

open class Matrix(private var a1: Float, private var a2: Float, private var b1: Float, private var b2: Float) {
    private var size = 2
    private var a3: Float? = null
    private var b3: Float? = null
    private var c1: Float? = null
    private var c2: Float? = null
    private var c3: Float? = null

    constructor(a1: Float, a2: Float, a3: Float, b1: Float, b2: Float, b3: Float, c1: Float, c2: Float, c3: Float) : this(a1, a2, b1, b2) {
        size = 3
        this.a3 = a3
        this.b3 = b3
        this.c1 = c1
        this.c2 = c2
        this.c3 = c3
    }

    operator fun times(vector: Vector) : Vector? {
        return if (vector.size == 2 && size == 2)
            Vector((vector.x * this.a1) + (vector.y * this.a2), (vector.x * this.b1) + (vector.y * this.b2))
        else if (vector.size == 3 && size == 3)
            Vector((vector.x * this.a1) + (vector.y * this.a2) + (vector.z!! * this.a3!!),
                    (vector.x * this.b1) + (vector.y * this.b2) + (vector.z!! * this.b3!!),
                    (vector.x * this.c1!!) + (vector.y * this.c2!!) + (vector.z!! * this.c3!!))
        else null
    }
}
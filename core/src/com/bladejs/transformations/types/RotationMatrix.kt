package com.bladejs.transformations.types

import kotlin.math.cos
import kotlin.math.sin

class RotationMatrix(angle: Float) : Matrix(cos(angle), -sin(angle), sin(angle), cos(angle))
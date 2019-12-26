package frc.team6502.loggerhead.component

import edu.wpi.first.wpilibj.shuffleboard.*
import frc.team6502.loggerhead.Rect

abstract class Component {
    var properties = mutableMapOf<String, Any>()
    var rect: Rect? = null

    abstract fun construct(c: ShuffleboardContainer)
    abstract fun update()
}
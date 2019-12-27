package frc.team6502.loggerhead.component.layout

import edu.wpi.first.wpilibj.shuffleboard.LayoutType
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer
import frc.team6502.loggerhead.Rect
import frc.team6502.loggerhead.component.Component

enum class LabelPosition {
    TOP, LEFT, RIGHT, BOTTOM, HIDDEN
}

abstract class Layout: Component() {
    abstract val name: String

    abstract var layoutType: LayoutType
    var components = arrayListOf<Component>()

    // properties
    var labelPosition: LabelPosition = LabelPosition.BOTTOM

    override fun construct(c: ShuffleboardContainer) {

        properties["Label position"] = labelPosition.name

        var layout = c.getLayout(name, layoutType).withProperties(properties)
        if(rect != null) layout = layout.withPosition(rect!!.x, rect!!.y).withSize(rect!!.w, rect!!.h)
        components.forEach { it.construct(layout) }
    }
}
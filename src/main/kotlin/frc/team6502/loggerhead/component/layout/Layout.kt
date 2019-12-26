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

        if(this is GridLayout) {
            properties["Number of rows"] = rows ?: (components.map { it.rect?.y ?: 0 }.max()?.plus(1)) ?: 3
            properties["Number of columns"] = cols ?: (components.map { it.rect?.x ?: 0 }.max()?.plus(1)) ?: 3
        }

        var layout = c.getLayout(name, layoutType).withProperties(properties)
        if(rect != null) layout = layout.withPosition(rect!!.x, rect!!.y).withSize(rect!!.w, rect!!.h)
        components.forEach { it.construct(layout) }
    }
}
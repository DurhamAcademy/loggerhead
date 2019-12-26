package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.*
import frc.team6502.loggerhead.Rect
import frc.team6502.loggerhead.component.Component

abstract class Widget: Component() {

    open var type: WidgetType = BuiltInWidgets.kTextView
    abstract var entry: NetworkTableEntry

    fun configure(w: SimpleWidget): SimpleWidget {
        var widget = w.withWidget(type).withProperties(properties)
        if(rect != null) widget = widget.withPosition(rect!!.x, rect!!.y).withSize(rect!!.w, rect!!.h)
        return widget
    }
}
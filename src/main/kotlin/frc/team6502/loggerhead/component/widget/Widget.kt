package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.*
import frc.team6502.loggerhead.component.Component

abstract class Widget: Component() {

    protected var widgetType: WidgetType? = null
    abstract var entry: NetworkTableEntry

    fun configure(w: SimpleWidget): SimpleWidget {
        var widget = w.withWidget(widgetType).withProperties(properties)
        if(rect != null) widget = widget.withPosition(rect!!.x, rect!!.y).withSize(rect!!.w, rect!!.h)
        return widget
    }

    fun configure(w: ComplexWidget): ComplexWidget {
        var widget = w.withProperties(properties)
        if(widgetType != null) widget = widget.withWidget(widgetType)
        if(rect != null) widget = widget.withPosition(rect!!.x, rect!!.y).withSize(rect!!.w, rect!!.h)
        return widget
    }

}
package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.*

enum class BooleanWidgetType(val t: WidgetType) {
    BOOLEAN_BOX(BuiltInWidgets.kBooleanBox),
    TOGGLE_BUTTON(BuiltInWidgets.kToggleButton),
    TOGGLE_SWITCH(BuiltInWidgets.kToggleSwitch)
}

class BooleanWidget(val name: String, private val supplier: () -> Boolean, private val consumer: (Boolean) -> Unit = {}, init: BooleanWidget.() -> Unit = {}) : Widget() {

    private var value: Boolean = false

    var type = BooleanWidgetType.BOOLEAN_BOX

    init {
        init()
        widgetType = type.t
    }

    override lateinit var entry: NetworkTableEntry

    override fun construct(c: ShuffleboardContainer) {
        val widget = configure(c.add(name, supplier()))
        entry = widget.entry
    }

    override fun update() {
        val current = entry.getBoolean(value)
        if(current != value) {
            value = current
            consumer(current)
            return
        }

        val v = supplier()
        if(current != v) entry.setBoolean(v)
    }
}
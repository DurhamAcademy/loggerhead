package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.*

class StringWidget(val name: String, private val supplier: () -> String, private val consumer: (String) -> Unit = {}, init: StringWidget.() -> Unit = {}) : Widget() {
    override var type: WidgetType = BuiltInWidgets.kTextView

    private var value: String = ""

    init {
        init()
    }

    override lateinit var entry: NetworkTableEntry

    override fun construct(c: ShuffleboardContainer) {
        val widget = configure(c.add(name, supplier()))
        entry = widget.entry
    }

    override fun update() {
        val current = entry.getString(value)
        if(current != value) {
            value = current
            consumer(current)
            return
        }

        val v = supplier()
        if(current != v) entry.setString(v)
    }
}
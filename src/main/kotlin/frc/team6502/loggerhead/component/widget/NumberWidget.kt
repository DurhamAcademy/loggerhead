package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.*

class NumberWidget(val name: String, private val supplier: () -> Number, private val consumer: (Number) -> Unit = {}, init: NumberWidget.() -> Unit = {}) : Widget() {
    override var type: WidgetType = BuiltInWidgets.kTextView

    private var value: Number = 0

    init {
        init()
    }

    override lateinit var entry: NetworkTableEntry

    override fun construct(c: ShuffleboardContainer) {
        val widget = configure(c.add(name, supplier()))
        entry = widget.entry
    }

    override fun update() {
        val current = entry.getNumber(value)
        if(current != value) {
            value = current
            consumer(current)
            return
        }

        val v = supplier()
        if(current != v) entry.setNumber(v)
    }
}
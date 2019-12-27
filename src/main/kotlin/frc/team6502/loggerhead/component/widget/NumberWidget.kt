package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.*

enum class Direction {
    HORIZONTAL,
    VERTICAL
}

enum class NumberWidgetType(val t: WidgetType) {
    TEXT_VIEW(BuiltInWidgets.kTextView),
}

class NumberWidget(val name: String, private val supplier: () -> Number, private val consumer: (Number) -> Unit = {}, init: NumberWidget.() -> Unit = {}) : Widget() {

    var type = NumberWidgetType.TEXT_VIEW

    private var value: Number = 0

    // properties
    var showText = true
    var min: Number = 0
    var max: Number = 100
    var center: Number = 0
    var tickMarks: Number = 5

    init {
        init()
        widgetType = type.t
    }



    override lateinit var entry: NetworkTableEntry

    override fun construct(c: ShuffleboardContainer) {

        properties["Min"] = min
        properties["Max"] = max
        properties["Center"] = center
        properties["Num tick marks"] = tickMarks
        properties["Show value"] = showText
        properties["Show text"] = showText

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
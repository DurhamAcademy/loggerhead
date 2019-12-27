package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.*

class GraphWidget(val name: String, private val supplier: () -> Array<Number>, init: GraphWidget.() -> Unit = {}) : Widget() {
    private var value: Array<Number> = arrayOf()

    var visibleTime: Number = 30
    var autoBounds = true
    var upperBound: Number = 1
    var lowerBound: Number = -1

    init {
        init()
        widgetType = BuiltInWidgets.kGraph
    }

    override lateinit var entry: NetworkTableEntry

    override fun construct(c: ShuffleboardContainer) {

        properties["Visible time"] = visibleTime
        properties["Automatic bounds"] = autoBounds
        properties["Upper bound"] = upperBound
        properties["Lower bound"] = lowerBound

        val widget = configure(c.add(name, supplier()))
        entry = widget.entry
    }

    override fun update() {
        val current = entry.getNumberArray(value)
        val v = supplier()
        if(!current.contentEquals(v)) entry.setNumberArray(v)
    }
}
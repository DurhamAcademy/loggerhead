package frc.team6502.loggerhead.component.layout

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts
import edu.wpi.first.wpilibj.shuffleboard.LayoutType
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer
import frc.team6502.loggerhead.component.Component

class GridLayout(override val name: String, init: GridLayout.() -> Unit) : Layout() {

    var cols: Int? = null
    var rows: Int? = null

    operator fun Component.unaryPlus() {
        components.add(this)
    }

    init {
        init()
    }

    override var layoutType = BuiltInLayouts.kGrid as LayoutType

    override fun construct(c: ShuffleboardContainer) {
        properties["Number of rows"] = rows ?: (components.map { it.rect?.y ?: 0 }.max()?.plus(1)) ?: 3
        properties["Number of columns"] = cols ?: (components.map { it.rect?.x ?: 0 }.max()?.plus(1)) ?: 3
        super.construct(c)
    }

    override fun update() {
        components.forEach(Component::update)
    }

}
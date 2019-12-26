package frc.team6502.loggerhead.component.layout

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts
import edu.wpi.first.wpilibj.shuffleboard.LayoutType
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer
import frc.team6502.loggerhead.component.Component

class ListLayout(override val name: String, init: ListLayout.() -> Unit) : Layout() {

    operator fun Component.unaryPlus() {
        components.add(this)
    }

    init {
        init()
    }

    override var layoutType = BuiltInLayouts.kList as LayoutType

    override fun update() {
        components.forEach(Component::update)
    }

}
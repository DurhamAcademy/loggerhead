package frc.team6502.loggerhead

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent
import frc.team6502.loggerhead.component.Component

class DashboardTab(val name: String) {
    val components = arrayListOf<Component>()
    val shuffleboardTab = Shuffleboard.getTab(name)

    fun update() {
        components.forEach(Component::update)
    }

    operator fun Component.unaryPlus() {
        components.add(this)
        this.construct(shuffleboardTab)
    }
}

fun tab(name: String, init: DashboardTab.() -> Unit) {
    val tab = DashboardTab(name)
    tab.init()
    Loggerhead.tabs.add(tab)
}
package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.shuffleboard.*
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import java.util.*

class SendableWidget(val name: String, private val sendable: Sendable, init: SendableWidget.() -> Unit = {}) : Widget() {

    init {
        init()
    }

    override lateinit var entry: NetworkTableEntry

    override fun construct(c: ShuffleboardContainer) {
        configure(c.add(name, sendable))
    }

    override fun update() { }
}
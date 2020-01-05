package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.shuffleboard.*
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import java.awt.geom.Point2D
import java.util.*

class Point2DWidget(val name: String, private val supplier: () -> Pair<Double, Double>, init: Point2DWidget.() -> Unit = {}) : Widget() {

    init {
        init()
        widgetType = WidgetType { "Point2D" }
    }

    override lateinit var entry: NetworkTableEntry

    private val sendable = SendablePoint2D(name, supplier())

    override fun construct(c: ShuffleboardContainer) {
        configure(c.add(name, sendable))
    }

    override fun update() {
        val v = supplier()
        sendable.point = v
    }
}

class SendablePoint2D(val pointName: String, var point: Pair<Double, Double>): Sendable, AutoCloseable {

    init {
        SendableRegistry.add(this, pointName)
    }

    override fun initSendable(builder: SendableBuilder?) {
        builder?.setSmartDashboardType("Point2D")
        builder?.addStringProperty("pointString", {"${point.first},${point.second}"}, null)
    }

    override fun close() {
        SendableRegistry.remove(this)
    }
}
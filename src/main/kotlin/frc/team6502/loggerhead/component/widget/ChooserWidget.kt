package frc.team6502.loggerhead.component.widget

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.*
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import java.util.*

enum class ChooserWidgetType(val t: BuiltInWidgets) {
    COMBO_BOX(BuiltInWidgets.kComboBoxChooser),
    SPLIT_BUTTON(BuiltInWidgets.kSplitButtonChooser)
}

inline fun <reified T: Enum<T>> asMap(): Map<String, T> {
    return enumValues<T>().associateBy { it.name.split("_").joinToString(" ") { w -> w[0].toUpperCase() + w.drop(1).toLowerCase() } }
}

class ChooserWidget<T>(val name: String, private val options: Map<String, T>, private val consumer: (T?) -> Unit, init: ChooserWidget<T>.() -> Unit = {}) : Widget() {

    private var chooser = SendableChooser<T>()
    var type = ChooserWidgetType.COMBO_BOX
    var default = ""

    init {
        if(options.isEmpty()) error("Must provide options to choose from")

        init()

        options.forEach {
            if(it.key != default) {
                chooser.addOption(it.key, it.value)
            } else {
                chooser.setDefaultOption(default, options[default])
            }
        }

        widgetType = type.t
    }

    private var value: T? = options[default]

    override lateinit var entry: NetworkTableEntry

    override fun construct(c: ShuffleboardContainer) {
        configure(c.add(name, chooser))
        consumer(chooser.selected)
    }

    override fun update() {
        val current = chooser.selected
        if(current != value) {
            value = current
            consumer(current)
        }
    }
}
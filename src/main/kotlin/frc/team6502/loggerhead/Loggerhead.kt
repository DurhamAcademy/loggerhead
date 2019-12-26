package frc.team6502.loggerhead

object Loggerhead {
    val tabs = arrayListOf<DashboardTab>()

    fun update(){
        tabs.forEach(DashboardTab::update)
    }
}
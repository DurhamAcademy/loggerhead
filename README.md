# loggerhead
shuffleboard kotlin DSL

looks somethin like this

```kotlin
tab("Main") {
    +GridLayout("buttons") {
        for(i in 1..9) {
            +BooleanWidget("$i", {false}, { if(it) println("pressed $i")}) {
                type = BuiltInWidgets.kToggleButton
                rect = Rect((i-1)%3, (i-1)/3, 1, 1)
            }
        }
        +BooleanWidget("0", {false}, { if(it) println("pressed 0")}) {
            type = BuiltInWidgets.kToggleButton
            rect = Rect(1, 3, 1, 1)
        }
        labelPosition = LabelPosition.HIDDEN
        rect = Rect(0, 0, 2, 3)
    }
}
```

when periodically running `Loggerhead.update()`

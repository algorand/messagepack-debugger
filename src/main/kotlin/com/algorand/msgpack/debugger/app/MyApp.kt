package com.algorand.msgpack.debugger.app

import com.algorand.msgpack.debugger.view.MainView
import javafx.application.Application
import tornadofx.*

class MyApp: App(MainView::class, Styles::class)

fun main(args: Array<String>) {
    Application.launch(MyApp::class.java, *args)
}


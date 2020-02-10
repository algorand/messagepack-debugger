package com.algorand.msgpack.debugger.view

import com.algorand.msgpack.debugger.app.Styles
import com.algorand.msgpack.debugger.app.unpack
import com.algorand.msgpack.debugger.models.*
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.geometry.Pos
import javafx.scene.control.ContentDisplay
import javafx.scene.control.TreeItem
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("Message Pack Debugger") {
    val comparisonMode = SimpleBooleanProperty(app.parameters.raw.size == 2)
    val leftTreeGroup = Group("root", mapToRootChildren(mapOf<Any,Any>()))
    val rightTreeGroup = Group("root", mapToRootChildren(mapOf<Any,Any>()))
    val leftString = makeTreeStringProperty(leftTreeGroup, rightTreeGroup)
    val rightString = makeTreeStringProperty(rightTreeGroup, leftTreeGroup)
    lateinit var viewBox: HBox

    init {
        // Apply them here instead of the initializer to make sure the bindings trigger
        leftString.value = app.parameters.raw.getOrNull(0)
        rightString.value = app.parameters.raw.getOrNull(1)
    }

    // Static functions
    companion object {
        fun calculateColors(left: List<Group>, right:List<Group>) {
            fun setAll(c: Color, grp: List<Group>) {
                grp.forEach {
                    it.color = c
                    setAll(c, it.children)
                }
            }

            val mismatch = Color.RED
            val match = Color.WHITE

            // Set all from the right group to mismatch so that we don't have to check for things in right but not left.
            setAll(mismatch, right)

            left.forEach {
                // If there is a single match, set the color to the match color
                val matches = right.filter {other -> it.name.equals(other.name) }.toList()
                if (matches.size == 1) {
                    it.color = match
                    matches[0].color = match
                    calculateColors(it.children, matches[0].children)
                }

                // If there are more than 1 or zero matches, set them all to the mismatch color
                else {
                    it.color = mismatch
                    setAll(mismatch, it.children)
                    setAll(mismatch, matches)
                }
            }
        }

        fun makeTreeStringProperty(treeGroup: Group, other: Group) = SimpleStringProperty().apply {
                onChange {
                    try {
                        it?.let {
                            val map = unpack(it)
                            val updatedGroup = mapToRootChildren(map)
                            calculateColors(updatedGroup, other.children)
                            treeGroup.children.clear()
                            treeGroup.children.addAll(updatedGroup)

                            // Trigger a change in the other one.
                            val group = Group("")
                            other.children.add(group)
                            other.children.remove(group)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        // Convert a color object to hex, i.e. #FFAA00
        fun Color.toHex(): String {
            return String.format( "#%02X%02X%02X",
                    ( this.getRed().toInt() * 255 ),
                    ( this.getGreen().toInt() * 255 ),
                    ( this.getBlue().toInt() * 255 ) )
        }
    }

    /**
     * Creates the treeview area, we have two of them:
     * -------------------------------------------
     * |                                         |
     * |                                         |
     * |                                         |
     * -------------------------------------------
     * |                    |                    |
     * |                    |                    |
     * |                    |                    |
     * |                    |                    |
     * |                    |                    |
     * |                    |                    |
     * |        this        |      and this      |
     * |                    |                    |
     * |                    |                    |
     * |                    |                    |
     * |                    |                    |
     * |                    |                    |
     * -------------------------------------------
     */
    fun makeMsgpackView(str: SimpleStringProperty, group: Group, isVisiblePredicate: () -> ObservableValue<Boolean> = { SimpleBooleanProperty(true) }) = vbox {
            hgrow = Priority.ALWAYS
            visibleWhen(isVisiblePredicate)
            managedWhen(isVisiblePredicate) // Hidden tree will take up space if managed.

            textfield(str)
            treeview(TreeItem(group)) {
                isShowRoot = false
                root.isExpanded = true
                cellFormat {
                    text = it.name
                    // Ignores this.isSelected
                    style = "-fx-background-color:${it.color.toHex()}; -fx-text-fill:black"
                }
                populate {
                    it.value.children
                }
            }
        }

    override val root = vbox {
        hbox {
            label(title) {
                addClass(Styles.heading)
            }
            vbox {
                alignment = Pos.CENTER
                checkbox("Comparison Mode", comparisonMode) {
                    contentDisplay = ContentDisplay.RIGHT
                    action {
                        if (comparisonMode.value) {
                            calculateColors(leftTreeGroup.children, rightTreeGroup.children)
                        } else {
                            calculateColors(leftTreeGroup.children, leftTreeGroup.children)
                        }
                    }
                }
            }
        }
        viewBox = hbox {
            this += makeMsgpackView(leftString, leftTreeGroup)
            this += makeMsgpackView(rightString, rightTreeGroup) { comparisonMode }
        }
    }
}

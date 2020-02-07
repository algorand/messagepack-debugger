package com.algorand.msgpack.debugger.models

import com.algorand.algosdk.util.Encoder
import javafx.scene.paint.Color
import tornadofx.*

data class Group(val name: String, val children: MutableList<Group> = mutableListOf(), var color: Color = Color.WHITE)

// Length of data to include in the parent, i.e. Group("amt (5000)") instead of Group("amt", listOf(Group("5000"))
private val INLINE_LEN = 15

/**
 * Convert a map of maps into a tree of groups.
 */
fun mapToRootChildren(map: Map<*,*>): MutableList<Group> {
    return anyToGroupList(map)
}

private fun anyToGroup(any: Any?): Group {
    return when(any) {
        is Map.Entry<*, *> -> {
            //println("anyToGroup Map.Entry -> $any, ${any.javaClass.kotlin.simpleName}")
            var g = Group(any.key.toString(), anyToGroupList(any.value))
            if (g.children.size == 1) {
                val children = if (g.children[0].name.length < INLINE_LEN) mutableListOf() else g.children
                g = Group( "${any.key.toString()} (${g.children.get(0).name.take(INLINE_LEN)})", children)
            }
            g
        }
        else -> {
            //println("anyToGroup ELSE -> $any, ${any?.javaClass?.kotlin?.simpleName}")
            Group (any.toString())
        }
    }
}

private fun anyToGroupList(any: Any?): MutableList<Group> {
    return when(any) {
        is Map<*,*> -> {
            //println("anyToGroupList Map-> $any, ${any?.javaClass?.kotlin?.simpleName}")
            any.entries
                    .map { anyToGroup(it) }
                    .toObservable()
        }
        // i.e. lsig args
        is Collection<*> -> {
            //println("anyToGroupList Collection-> $any, ${any?.javaClass?.kotlin?.simpleName}")
            any
                    .map { anyToGroup(it) }
                    .toObservable()
        }
        is ByteArray -> {
            //println("anyToGroupList ByteArray-> $any, ${any?.javaClass?.kotlin?.simpleName}")
            observableListOf(Group(Encoder.encodeToBase64(any)))
        }
        else -> {
            //println("anyToGroupList ELSE-> $any, ${any?.javaClass?.kotlin?.simpleName}")
            observableListOf(Group(any.toString()))
        }
    }
}

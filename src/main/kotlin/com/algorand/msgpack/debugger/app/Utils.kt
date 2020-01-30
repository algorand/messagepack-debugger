package com.algorand.msgpack.debugger.app

import com.algorand.algosdk.util.Encoder
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import org.msgpack.jackson.dataformat.MessagePackFactory
import java.io.ByteArrayInputStream

fun unpack(str: String): Map<*, *> {
    val ret = mutableMapOf<Any, Any>()
    var objectNum = 1

    val factory = MessagePackFactory()
    val inputStream = ByteArrayInputStream(Encoder.decodeFromBase64(str))
    val parser = factory.createParser(inputStream)
    val mapper = ObjectMapper(factory)
    mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

    try {
        while (true) {
            ret["object-${objectNum++}"] = mapper.readValue(parser, Map::class.java)
        }
    } catch (e: Exception) {
        // done.
    }
    return ret
}

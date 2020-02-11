package com.algorand.msgpack.debugger.app

import com.algorand.algosdk.util.Encoder
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.codec.binary.Base64
import org.msgpack.jackson.dataformat.MessagePackFactory
import java.io.ByteArrayInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Matcher

// The input could be a base64 encoded string or a file path with bytes that need encoding or base64 data.
private fun getDataFromInput(str: String): String {
    val p = Path.of(str)
    return if (Files.isReadable(p)) {
        val fileData = Files.readAllBytes(Path.of(str))
        val stringValue = fileData.toString(Charsets.UTF_8)
        if (Base64.isBase64(stringValue)) {
            stringValue
        } else {
            Encoder.encodeToBase64(fileData)
        }
    } else {
        str
    }
}
fun unpack(str: String): Map<*, *> {
    val processedStr = getDataFromInput(str)
    val ret = mutableMapOf<Any, Any>()
    var objectNum = 1

    val factory = MessagePackFactory()
    val inputStream = ByteArrayInputStream(Encoder.decodeFromBase64(processedStr))
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

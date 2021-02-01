package com.example.restaurantapp.core.api.adapters

import com.google.gson.*
import java.lang.reflect.Type

/**
 * Форматирование Boolean в запросах
 */
class GsonBooleanAdapter : JsonSerializer<Boolean?>, JsonDeserializer<Boolean?> {
    @Synchronized
    override fun serialize(
        value: Boolean?,
        type: Type,
        jsonSerializationContext: JsonSerializationContext
    ): JsonElement {
        return value?.let { JsonPrimitive(it) } ?: JsonNull.INSTANCE
    }

    @Synchronized
    override fun deserialize(
        jsonElement: JsonElement,
        type: Type,
        jsonDeserializationContext: JsonDeserializationContext
    ): Boolean? {
        return try {
            jsonElement.asString.toBoolean() || jsonElement.asString == "1"
        } catch (e: Exception) {
            null
        }
    }
}
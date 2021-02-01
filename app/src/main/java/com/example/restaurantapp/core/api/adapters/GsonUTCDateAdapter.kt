package com.example.restaurantapp.core.api.adapters

import android.text.TextUtils
import com.google.gson.*
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Форматирование даты-врмени в запросах
 */
class GsonUTCDateAdapter : JsonSerializer<Date?>, JsonDeserializer<Date?> {
    private val dateFormat: DateFormat

    @Synchronized
    override fun serialize(
        date: Date?,
        type: Type,
        jsonSerializationContext: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(dateFormat.format(date))
    }

    @Synchronized
    override fun deserialize(
        jsonElement: JsonElement,
        type: Type,
        jsonDeserializationContext: JsonDeserializationContext
    ): Date? {
        val dateStr = jsonElement.asString
        return try {
            if (TextUtils.isEmpty(dateStr)) null else dateFormat.parse(dateStr)
        } catch (e: Exception) {
            throw JsonParseException(e)
        }
    }

    companion object {
        private const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        private const val TIME_UTC = "UTC"
    }

    init {
        dateFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault())
        //This is the key line which converts the date to UTC which cannot be accessed with the default serializer
        dateFormat.timeZone = TimeZone.getTimeZone(TIME_UTC)
    }
}
sealed class TaggedValue {
    object Null : TaggedValue()
    data class BooleanValue(val value: Boolean) : TaggedValue()
    data class NumberValue(val value: Number) : TaggedValue()
    data class StringValue(val value: String) : TaggedValue()
    data class ArrayValue(val value: List<TaggedValue>) : TaggedValue()
    data class ObjectValue(val value: Map<String, TaggedValue>) : TaggedValue()
}


fun stringify(taggedValue: TaggedValue): String {
    return when (taggedValue) {
        TaggedValue.Null -> "null"
        is TaggedValue.BooleanValue -> taggedValue.value.toString()
        is TaggedValue.NumberValue -> taggedValue.value.toString()
        is TaggedValue.StringValue -> "\"${taggedValue.value}\""
        is TaggedValue.ArrayValue -> {
            val items = taggedValue.value.joinToString(separator = ", ") { stringify(it) }
            "[${items}]"
        }

        is TaggedValue.ObjectValue -> {
            val pairs = taggedValue.value.entries.joinToString(separator = ", ") { (key, value) ->
                "\"$key\": ${stringify(value)}"
            }
            "{$pairs}"
        }
    }
}

fun main() {
    val obj = TaggedValue.ObjectValue(
        mapOf(
            "name" to TaggedValue.StringValue("Alice"),
            "age" to TaggedValue.NumberValue(30),
            "isStudent" to TaggedValue.BooleanValue(false),
            "scores" to TaggedValue.ArrayValue(
                listOf(
                    TaggedValue.NumberValue(95),
                    TaggedValue.NumberValue(87),
                    TaggedValue.NumberValue(92)
                )
            ),
            "address" to TaggedValue.ObjectValue(
                mapOf(
                    "city" to TaggedValue.StringValue("Beijing"),
                    "country" to TaggedValue.StringValue("China")
                )
            ),
            "hobbies" to TaggedValue.Null
        )
    )

    println(stringify(obj))
}
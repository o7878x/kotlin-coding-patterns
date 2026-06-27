typealias Factory<T> = (args: List<Any>) -> T

class Registry<T> {
    private val entries = mutableMapOf<String, Factory<T>>()

    fun register(name: String, factory: Factory<T>) {
        if (entries.containsKey(name)) {
            throw IllegalArgumentException("$name already exists")
        }
        entries[name] = factory
    }

    fun get(name: String): Factory<T> {
        return entries[name] ?: throw IllegalArgumentException("$name not found")
    }

    fun create(name: String, args: List<Any>): T {
        return get(name)(args)
    }

    fun has(name: String): Boolean {
        return entries.containsKey(name)
    }

    fun list(): List<String> {
        return entries.keys.toList()
    }
}
class LRUCache<K, V>(private val maxSize: Int) {
    private val cache = object : LinkedHashMap<K, V>(maxSize, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
            return size > maxSize
        }
    }

    fun get(key: K): V? = cache[key]

    fun put(key: K, value: V) {
        cache[key] = value
    }

    fun remove(key: K): V? = cache.remove(key)

    fun clear() = cache.clear()

    fun size(): Int = cache.size

    fun containsKey(key: K): Boolean = cache.containsKey(key)

    fun keys(): Set<K> = cache.keys

    fun values(): Collection<V> = cache.values

    override fun toString(): String = cache.toString()
}

fun main() {
    val lruCache = LRUCache<String, Int>(3)

    lruCache.put("a", 1)
    lruCache.put("b", 2)
    lruCache.put("c", 3)

    println(lruCache)

    lruCache.get("a")
    lruCache.put("d", 4)
    println(lruCache)
}
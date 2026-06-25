class BloomFilter(private val size: Int, private val hashCount: Int) {
    private val bits = BooleanArray(size)

    private fun hashes(item: String): List<Int> {
        var h1 = 0L
        var h2 = 0L
        for (c in item) {
            h1 = (h1 * 31 + c.code) and 0xFFFFFFFFL
            h2 = (h2 * 37 + c.code) and 0xFFFFFFFFL
        }
        return (0..<hashCount).map {
            ((h1 + it * h2) % size).toInt()
        }
    }

    fun add(item: String) {
        for (pos in hashes(item)) {
            bits[pos] = true
        }
    }

    fun mightContain(item: String): Boolean {
        return hashes(item).all {
            bits[it]
        }
    }
}
import kotlin.random.Random

class SkipNode(val key: Int, var value: String, level: Int) {
    val forward = Array<SkipNode?>(level + 1) { null }
}

class SkipList {
    private var maxLevel = 16
    private var level = 0
    private val header = SkipNode(Int.MIN_VALUE, "", 16)

    private fun randomLevel(): Int {
        var curLevel = 0
        while (curLevel < this.maxLevel && Math.random() < 0.5) {
            ++curLevel
        }
        return curLevel
    }

    fun insert(key: Int, value: String) {
        val update = arrayOfNulls<SkipNode>(this.maxLevel + 1)
        var curNode = this.header
        for (i in this.level downTo 0) {
            while (curNode.forward[i] != null && curNode.forward[i]!!.key < key) {
                curNode = curNode.forward[i]!!
            }
            update[i] = curNode
        }
        if (curNode.forward[0]!!.key == key) {
            curNode.forward[0]!!.value = value
            return
        }
        val newLevel = this.randomLevel()
        if (newLevel > this.level) {
            for (i in (this.level + 1)..newLevel) {
                update[i] = this.header
            }
            this.level = newLevel
        }
        val node = SkipNode(key, value, newLevel)
        for (i in 0..newLevel) {
            node.forward[i] = update[i]!!.forward[i]
            update[i]!!.forward[i] = node
        }
    }

    fun search(key: Int): String? {
        var curNode = this.header
        for (i in this.level downTo 0) {
            while (curNode.forward[i] != null && curNode.forward[i]!!.key < key) {
                curNode = curNode.forward[i]!!
            }
        }
        return if (curNode.forward[0]!!.key == key) curNode.forward[0]!!.value else null
    }
}
import java.util.Vector

class RingBuffer<T>(private val capacity: Int) {
    private val buffer = MutableList<T?>(capacity) { null }
    private var head = 0
    private var tail = 0
    private var count = 0

    fun enqueue(item: T): Boolean {
        if (this.count == this.capacity) {
            return false
        }
        this.buffer[this.tail] = item
        this.tail = (this.tail + 1) % this.capacity
        this.count++
        return true
    }

    fun dequeue(): T? {
        if (this.count == 0) {
            return null
        }
        val item = this.buffer[this.head]
        this.head = (this.head + 1) % this.capacity
        this.count--
        return item
    }

    fun peek(): T? {
        return if (this.count > 0) (this.buffer[this.head]) else null
    }

    fun size(): Int = this.count

    fun isFull(): Boolean = this.count == this.capacity

    fun isEmpty(): Boolean = this.count == 0
}
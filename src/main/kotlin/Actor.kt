import java.util.Deque
import java.util.LinkedList

typealias MessageHandler<S, M> = (state: S, msg: M) -> S

class Actor<S, M>(private var state: S, private val msgHandler: MessageHandler<S, M>) {
    private var processing = false

    private var mailbox = LinkedList<M>()

    private fun processMailbox() {
        this.processing = true
        while (this.mailbox.isNotEmpty()) {
            val msg = this.mailbox.pop()
            this.state = this.msgHandler(this.state, msg)
        }
        this.processing = false
    }

    fun send(msg: M) {
        this.mailbox.push(msg)

        if (!this.processing) {
            processMailbox()
        }
    }

    fun getState() = this.state
}
typealias TaskHandler<T> = (taskId: Int) -> T

class WorkStealingScheduler<T>(private val workerCount: Int) {
    private val queues = Array(workerCount) { ArrayDeque<Int>() }

    fun submit(task: Int, workerIndex: Int) {
        queues[workerIndex].add(task)
    }

    fun run(handler: TaskHandler<T>): List<T> {
        val results = mutableListOf<T>()
        var anyWork = true
        while (anyWork) {
            anyWork = false
            for (workerId in queues.indices) {
                if (queues[workerId].isNotEmpty()) {
                    anyWork = true
                    val taskId = queues[workerId].removeLast()
                    results.add(handler(taskId))
                } else {
                    for (otherWorkId in queues.indices) {
                        if (otherWorkId != workerId && queues[otherWorkId].size > 1) {
                            anyWork = true
                            val stolenWorkId = queues[otherWorkId].removeFirst()
                            results.add(handler(stolenWorkId))
                            break
                        }
                    }
                }
            }
        }
        return results
    }
}
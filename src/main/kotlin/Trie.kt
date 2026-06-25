class TrieNode {
    var children = mutableMapOf<Char, TrieNode>()
    var isEnd = false
}

class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var node = root
        for (c in word) {
            node = node.children.getOrPut(c) { TrieNode() }
        }
        node.isEnd = true
    }

    fun search(word: String): Boolean {
        val node = find(word)
        return node != null && node.isEnd
    }

    fun startsWith(word: String): Boolean {
        return find(word) != null
    }

    private fun find(word: String): TrieNode? {
        var node = root
        for (c in word) {
            node = node.children[c] ?: return null
        }
        return node
    }
}
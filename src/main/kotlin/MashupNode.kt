import kotlin.math.min

internal class MashupNode(private var tree: RadixTree) {
    private val id = tree.idCounter.getAndIncrement()
    private val children = mutableListOf<Edge>()

    // Adds a MashupNode to a radix tree
    fun add(str: String) {
        for (child in children) {
            val x = prefixMatch(str, child.prefix)
            // If they share a common prefix
            if (x != -1) {
                // First suffix
                child.prefix.substring(x + 1).also { sub1 ->
                    // Checks if prefix is the str
                    if (sub1.isNotEmpty()) child.node.add(sub1)

                    // Second suffix
                    str.substring(x + 1).also { sub2 ->
                        // Checks if prefix is the str
                        if (sub2.isNotEmpty()) child.node.add(sub2)
                        child.apply {
                            prefix = prefix.substring(0, x + 1)
                            isWord = sub1.isEmpty() || sub2.isEmpty()
                        }
                    }
                }
                return
            }
        }
        children.add(Edge(str, MashupNode(tree)))
    }

    // Searches for a MashupNode in a radix tree and returns where the node is
    fun search(str: String): Int {
        for (child in children) {
            if (str == child.prefix && child.isWord)
                return child.node.id
            if (str.startsWith(child.prefix)) {
                val retval = child.node.search(str.substring(child.prefix.length))
                if (retval != -1)
                    return retval
            }
        }
        return -1
    }

    // Removes a string from a radix tree and returns its position
    fun remove(str: String): Int {
        for (child in children) {
            // If child is a match and is a word
            if (str == child.prefix && child.isWord) {
                // If child has no children
                when {
                    child.node.isEmpty -> {
                        children.remove(child)
                    }
                    child.node.numChildren == 1 -> {
                        val grandchild = child.node.children[0]
                        val childPre1 = child.prefix
                        val childPre2 = grandchild.prefix
                        child.isWord = grandchild.isWord
                        child.node = grandchild.node
                        child.prefix = childPre1 + childPre2
                    }
                    else -> {
                        child.isWord = false
                    }
                }
                return child.node.id
            }
            if (str.startsWith(child.prefix)) {
                val retval = child.node.remove(str.substring(child.prefix.length))
                if (retval != -1) return retval
            }
        }
        return -1
    }

    // Checks to see if node is a leaf node
    val isEmpty: Boolean
        get() = children.size == 0

    // Returns the number of children
    private val numChildren: Int
        get() = children.size

    // Modifies a list of strings by adding all words in a tree to the list
    fun nodeStrings(list: MutableList<String>, str: String, isWord: Boolean) {
        if (isWord) list.add(str)
        for (child in children) child.node.nodeStrings(list, str + child.prefix, child.isWord)
    }

    override fun toString(): String {
        return "" + children
    }

    companion object {
        // Calculates the length of the shared prefix of two words
        @JvmStatic
        fun prefixMatch(s1: String, s2: String): Int {
            var result = ""
            for (i in 0 until min(s1.length, s2.length)) {
                result += if (s1[i] == s2[i]) s1[i] else break
            }
            return result.length - 1
        }
    }
}
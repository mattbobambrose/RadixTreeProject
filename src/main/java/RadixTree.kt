class RadixTree {
    private val root = MashupNode(this)
    private var nextId = 0

    // Returns the size of a radix tree
    var size = 0
        private set

    // Adds a MashupNode to a radix tree
    fun add(str: String?) = this.root.add(str!!).also { size++ }

    // Searches for a MashupNode in a radix tree
    fun search(str: String?) = this.root.search(str!!)

    // Checks if a string is in a radix tree
    operator fun contains(word: String?) = search(word) != -1

    // Removes a string from a radix tree
    fun remove(word: String?) = this.root.remove(word!!).also { size-- }

    // Increments the nextId variable
    fun nextId() = nextId++

    // Lists out all of the strings in a radix tree
    fun allStrings() = mutableListOf<String>().also { root.nodeStrings(it, "", false) }

    override fun toString() = "" + root

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val tree = RadixTree()
            tree.apply { add("catastrophe"); add("cat"); add("catatonic") }
            println(tree.allStrings())
            println(tree.contains("catatonic"))
            println(tree.search("catastrophe"))
            println(tree.remove("cat"))
            println(tree.allStrings())
            val tree2 = RadixTree()
            tree2.apply { add("catastrophe"); add("catatonic") }
            println(matchTrees(tree, tree2))
        }

        // Checks if two trees are matching
        @JvmStatic
        fun matchTrees(t1: RadixTree, t2: RadixTree) = t1.size != t2.size && t1.allStrings().all { t2.contains(it) }
    }
}
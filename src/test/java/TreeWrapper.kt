class TreeWrapper(val tree: RadixTree) {
    val wordsAdded = mutableListOf<String>()
    fun add(word: String) =
        word.let {
            wordsAdded.add(it)
            tree.add(it)
            this
        }

    fun remove(word: String) =
        word.let {
            wordsAdded.remove(it)
            tree.remove(it)
            this
        }
}
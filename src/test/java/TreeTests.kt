import MashupNode.Companion.prefixMatch
import RadixTree.Companion.matchTrees
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TreeTests {
    @Test
    fun matchTest() {
        assertEquals(prefixMatch("cat", "dog").toLong(), -1)
        assertEquals(prefixMatch("cat", "can").toLong(), 1)
        assertEquals(prefixMatch("cat", "cat").toLong(), 2)
        assertEquals(prefixMatch("cat", "hat").toLong(), -1)
        assertEquals(prefixMatch("cat", "crtt").toLong(), 0)
        assertEquals(prefixMatch("cat", "c").toLong(), 0)
        assertEquals(prefixMatch("c", "cat").toLong(), 0)
        assertEquals(prefixMatch("", "").toLong(), -1)
    }

    @Test
    fun treeTest1() {
        val result: String = RadixTree().apply { add("cat"); add("catastrophe"); add("catatonic") }.toString()
        assertEquals("[{'cat', t, [{'a', f, [{'tonic', t}, {'strophe', t}]}]}]", result)
    }

    @Test
    fun treeTest2() {
        val result: String = RadixTree().apply { add("cat"); add("can") }.toString()
        assertEquals("[{'ca', f, [{'t', t}, {'n', t}]}]", result)
    }

    @Test
    fun treeTest3() {
        val result: String = RadixTree().apply { add("ca"); add("cat"); add("can") }.toString()
        assertEquals("[{'ca', t, [{'t', t}, {'n', t}]}]", result)
    }

    @Test
    fun treeTest4() {
        val tree: RadixTree = RadixTree().apply { add("ca"); add("cat"); add("can") }
        val result = tree.search("cat")
        assertEquals(1, result.toLong())
    }

    @Test
    fun treeTest5() {
        val wrapper = TreeWrapper(RadixTree()).apply { add("ca"); add("cat"); add("can"); add("catatonic") }
        val tree = wrapper.tree
        val result: List<String> = tree.allStrings()
        assertEquals(wrapper.wordsAdded.size.toLong(), result.size.toLong())
        for (word in wrapper.wordsAdded) {
            assertTrue(result.contains(word))
            assertTrue(tree.contains(word))
        }
    }

    @Test
    fun treeTest6() {
        val wrapper =
            TreeWrapper(RadixTree()).apply { add("ca"); add("cat"); add("can"); add("catatonic"); remove("cat") }
        val tree = wrapper.tree
        val result: List<String> = tree.allStrings()
        assertEquals(result.size.toLong(), tree.size)
        assertEquals(wrapper.wordsAdded.size.toLong(), result.size.toLong())
        for (word in wrapper.wordsAdded) {
            assertTrue(result.contains(word))
            assertTrue(tree.contains(word))
        }
    }

    @Test
    fun treeTest7() {
        val tree: RadixTree = RadixTree().apply { add("ca"); add("cat"); add("can"); add("catatonic") }
        val tree2: RadixTree = RadixTree().apply { add("ca"); add("cat"); add("can"); add("catatonic") }
        assertTrue(matchTrees(tree, tree2))
    }
}
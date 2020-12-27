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
        val result: String = (RadixTree() + "cat" + "catatonic" + "catastrophe").toString()
        assertEquals("[{'cat', t, [{'a', f, [{'tonic', t}, {'strophe', t}]}]}]", result)
    }

    @Test
    fun treeTest2() {
        val result: String = (RadixTree() + "cat" + "can").toString()
        assertEquals("[{'ca', f, [{'t', t}, {'n', t}]}]", result)
    }

    @Test
    fun treeTest3() {
        val result: String = (RadixTree() + "ca" + "cat" + "can").toString()
        assertEquals("[{'ca', t, [{'t', t}, {'n', t}]}]", result)
    }

    @Test
    fun treeTest4() {
        val tree: RadixTree = RadixTree() + "ca" + "cat" + "can"
        val result = tree.search("cat")
        assertEquals(1, result)
    }

    @Test
    fun treeTest5() {
        val wrapper = TreeWrapper(RadixTree()) + "ca" + "cat" + "can" + "catatonic" - "cat"
        val tree = wrapper.tree
        val result: List<String> = tree.allStrings()
        assertEquals(wrapper.wordsAdded.size, result.size)
        for (word in wrapper.wordsAdded) {
            assertTrue(result.contains(word))
            assertTrue(tree.contains(word))
        }
    }

    @Test
    fun treeTest6() {
        val wrapper =
            TreeWrapper(RadixTree()) + "ca" + "cat" + "can" + "catatonic" - "cat"
        val tree = wrapper.tree
        val result: List<String> = tree.allStrings()
        assertEquals(result.size, tree.size)
        assertEquals(wrapper.wordsAdded.size, result.size)
        for (word in wrapper.wordsAdded) {
            assertTrue(result.contains(word))
            assertTrue(tree.contains(word))
        }
    }

    @Test
    fun treeTest7() {
        val tree: RadixTree = RadixTree() + "ca" + "cat" + "can" + "catatonic"
        val tree2: RadixTree = RadixTree() + "ca" + "cat" + "can" + "catatonic"
        println(tree.allStrings())
        println(tree2.allStrings())
        assertTrue(matchTrees(tree, tree2))
    }
}
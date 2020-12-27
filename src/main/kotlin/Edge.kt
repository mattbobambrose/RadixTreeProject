import java.lang.Boolean

internal class Edge(var prefix: String, var node: MashupNode) {
    @JvmField
    var isWord = true
    override fun toString(): String {
        return "{" + "'" + prefix + "'" +
                ", " + Boolean.valueOf(isWord).toString()[0] +
                (if (node.isEmpty) "" else ", $node") +
                '}'
    }
}
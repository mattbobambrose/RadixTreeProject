public class Edge {
    String prefix;
    boolean isWord = true;
    MashupNode node;

    public Edge(String prefix, MashupNode node) {
        this.prefix = prefix;
        this.node = node;
    }

    @Override
    public String toString() {
        return "{" + "'" + prefix + "'" +
                ", " + Boolean.valueOf(isWord).toString().charAt(0) +
                (node.isEmpty() ? "" : ", " + node) +
                '}';
    }
}
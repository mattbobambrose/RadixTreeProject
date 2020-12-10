import java.util.ArrayList;
import java.util.List;

public class MashupNode {
    List<Edge> children = new ArrayList<>();

    public static int match(String s1, String s2) {
        String result = "";
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) == s2.charAt(i))
                result += s1.charAt(i);
            else
                break;
        }
        return result.length() - 1;
    }

    public void add(String str) {
        for (Edge child : children) {
            int x = match(str, child.prefix);
            if (x != -1) {
                String sub1 = child.prefix.substring(x + 1);
                if (sub1.length() != 0)
                    child.node.add(sub1);

                String sub2 = str.substring(x + 1);
                if (sub2.length() != 0)
                    child.node.add(sub2);

                child.prefix = child.prefix.substring(0, x + 1);

                child.isWord = sub1.length() == 0 || sub2.length() == 0;
                return;
            }
        }
        children.add(new Edge(str, new MashupNode()));
    }

    public void split() {
//        String child = children
//        children.get()
    }

    public boolean isEmpty() {
        return children.size() == 0;
    }

    @Override
    public String toString() {
        return "" + children;
    }
}
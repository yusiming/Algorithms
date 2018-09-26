package chapter3.section2;

/**
 * 基于红黑树的符号表的实现
 *
 * @Auther: yusiming
 * @Date: 2018/9/26 19:21
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    // RED表示红链接
    private static final boolean RED = true;
    // BLACK表示普通黑链接
    private static final boolean BLACK = true;

    private class Node {
        Key key;
        Value value;
        Node right;
        Node left;
        // 由其父节点指向自己的连接的颜色,true表示红色，false表示黑色
        boolean color;
        // 这颗子树中节点的数量
        int N;

        public Node(Key key, Value value, boolean color, int n) {
            this.key = key;
            this.value = value;
            this.color = color;
            N = n;
        }
    }

    /**
     * 判断一个结点与其父节点之间的链接的颜色
     *
     * @param node 结点
     * @return boolean 是否为red链接
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    /**
     * 将一个红色右链接左旋转为一个红色左链接，
     * <p>
     * 并返回一个指向包含同一组键的子树，并且其左链接为红色
     *
     * @param node 被左旋转的结点
     * @return node 新的的子树
     */
    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        // 保持原来指向node节点的颜色，有可能为黑色或者红色，若为红色，需要继续旋转
        x.color = node.color;
        // 将node的颜色赋值为红色，
        node.color = RED;
        // 子树中的结点数量不变
        x.N = node.N;
        // 更新node子树中的结点数量
        node.N = size(node.left) + size(node.right) + 1;
        // 返回新的子树
        return x;
    }

    /**
     * 将一个红色左链接左旋转为一个红色右链接，
     * <p>
     * 并返回一个指向包含同一组键的子树，并且其右链接为红色
     *
     * @param node 被右旋转的结点
     * @return node 新的子树
     */
    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;
        return x;
    }

    /**
     * 该变结点以及子结点的颜色
     *
     * @param node 结点
     * @return void
     */
    private void flipColors(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    /**
     * @auther: yusiming
     * @date: 20:58 2018/9/26
     * @param: [key]
     * @return: void
     */
    private void delete(Key key) {

    }

    /**
     * 向表中插入键值对
     *
     * @param key   键
     * @param value 值
     * @return void
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("key不能为空");
        }
        if (value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            // 若没有查找到key，则创建之，并将其color赋值为红色
            return new Node(key, value, RED, 1);
        }
        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = put(node.left, key, value);
        } else if (comp > 0) {
            node.right = put(node.right, key, value);
        } else {
            // 找到了就覆盖原来的值
            node.value = value;
        }
        // 修正红黑树的结构
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    /**
     * 返回子树node中的节点的数量
     *
     * @param node 子树
     * @return int 结点数量
     */
    private int size(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("node不能为空");
        }
        return node.N;
    }

    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> bst = new RedBlackBST<>();
        bst.put(1, 10);
    }

}

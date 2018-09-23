package chapter3.section2;

/**
 * 使用链表来实现二叉树这种数据结构，
 * 每一个结点包含一个左子节点、右子节点、键值对、子树的结点数量(包含结点本身)
 * 每一个节点中的key的值都大于它的左子节点的中的key的值，小于它的右子节点中的key的值
 *
 * @Auther: yusiming
 * @Date: 2018/9/23 16:00
 */
public class BST<Key extends Comparable<Key>, Value> {
    // 整个符号表的根节点
    private Node root = null;

    public BST() {
    }

    private class Node {
        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }

        private Key key;
        private Value value;
        private Node left;
        private Node right;
        // 以该节点为根的子树中的结点总数，包含它自己
        private int N;
    }

    // 返回表中键值对的数量
    public int size() {
        return size(root);
    }

    // 返回指定结点的子树中的总结点数，包含结点本身
    private int size(Node node) {
        // 若结点为null，返回0
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    /**
     * 返回指定key的value值,若key不存在返回null,若表为空，返回null；
     *
     * @param: [key]
     * @return: Value
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 在以node根节点的数下查找key对应的value的值
     *
     * @param: [node]
     * @return: Value
     */
    private Value get(Node node, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("调用方法时，给参数传递了一个空值");
        }
        // 若node为null，则返回null
        if (node == null) {
            return null;
        }
        // 将key的值和node节点中的值作比较，
        int comp = key.compareTo(node.key);
        // 若key的值小于node结点中的key的值，那么应该到node 的左子节点中继续寻找
        if (comp < 0) {
            return get(node.left, key);
        }
        if (comp > 0) {
            // 若key大于node节点中的key，那么到右子节点中继续寻找
            return get(node.right, key);
        } else {
            // 若相等，返回node 的value
            return node.value;
        }
    }

    /**
     * 插入指定的键值对，若key已经存在，则更新value的值
     *
     * @param: [key, value]
     * @return: void
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * 此方法返回更新过的树结点，
     *
     * @param: [node, key, value]
     * @return:
     */
    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int camp = key.compareTo(node.key);
        if (camp < 0) {
            node.left = put(node.left, key, value);
        } else if (camp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 返回表中最小的键
     *
     * @param: []
     * @return: Key
     */
    public Key min() {
        if (isEmpty()) {
            throw new RuntimeException("表为空");
        }
        return min(root).key;
    }

    /**
     * 返回表中的最小的key，若标为空，返回null
     *
     * @param: [node]
     * @return: Key
     */
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    /**
     * 返回表中最大的key
     *
     * @param: []
     * @return: Key
     */
    public Key max() {
        if (isEmpty()) {
            throw new RuntimeException("表为空");
        }
        return max(root);
    }

    private Key max(Node node) {
        if (node.right == null) {
            return node.key;
        } else {
            return max(node.right);
        }
    }

    /**
     * 返回小于等于参数key的最大key
     *
     * @param key 要比较的key
     * @return Key 小于等于key的最大的key
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("参数key为null");
        }
        if (isEmpty()) {
            throw new RuntimeException("表为空");
        }
        Node node = floor(root, key);
        return node.key;
    }

    /**
     * 返回指定根节点的书中，小于等于key的最大结点
     *
     * @param: [node, key]
     * @return: node   小于等于key的最大结点
     */
    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int comp = key.compareTo(node.key);
        if (comp == 0) {
            return node;
        } else if (comp < 0) {
            return floor(node.left, key);
        }
        Node right = floor(node.right, key);
        if (right != null) {
            return right;
        }
        return node;
    }

    /**
     * 返回大于等于key的最小的key
     *
     * @param: []
     * @return: Key
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("参数为null");
        }
        if (isEmpty()) {
            throw new RuntimeException("表为空");
        }
        Node node = ceiling(root, key);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int comp = key.compareTo(node.key);
        if (comp == 0) {
            return node;
        } else if (comp > 0) {
            return ceiling(node.right, key);
        }
        Node left = ceiling(node.left, key);
        if (left != null) {
            return left;
        }
        return node;
    }

    /**
     * 返回排名为n的key,key从 0 开始，
     *
     * @param n 排名为n
     * @return: Key
     */
    public Key select(int n) {
        if (n < 0 || n > size()) {
            throw new IllegalArgumentException("参数n的范围超出了表的范围");
        }
        return select(root, n).key;
    }

    /**
     * 返回指定结点下面的第n 个结点，从0开始
     *
     * @param n 排名
     * @return node 排名为n的结点
     */
    private Node select(Node node, int n) {
        if (node == null) {
            return null;
        }
        int leftSize = size(node.left);
        // 若n等于leftSize，那么左子节点一共有n 个结点，那么node就是第 n个结点
        if (n == leftSize) {
            return node;
        } else if (n > leftSize) {
            // 若n 大于leftSize，证明要找的键在右子节点，
            // 继续在右子节点中查找第 n - leftSize - 1 结点，因为右子节点也是从 0 开始的
            return select(node.right, n - leftSize - 1);
        }
        return select(node.left, n);
    }

    /**
     * 删除最小的键对应的值
     *
     * @return: void
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new RuntimeException("表为空，不能删除元素");
        }
        // 更新root
        root = deleteMin(root);
    }

    /**
     * @param node 删除node结点中的最小结点
     * @return Node 删除指定节点后返回的结点
     */
    private Node deleteMin(Node node) {
        // 递归结束条件，当遇到一个结点没有左子节点时，那就是要被删除的结点，
        if (node.left == null) {
            return node.right;
        }
        // 更新node.left，和node的N的值
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        // 如果不是最后一个元素，返回自己
        return node;
    }

    /**
     * 删除最大的键对应的值
     *
     * @return: void
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new RuntimeException("表为空，不能删除元素");
        }
        root = deleteMax(root);
    }

    /**
     * @param node 删除指定结点的树中的key最大的结点
     * @return Node 删除指定节点后返回的结点
     */
    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 删除键为key的结点
     *
     * @param key 删除节点的key
     * @return void
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为null");
        }
        if (isEmpty()) {
            throw new RuntimeException("表为空，不能删除结点");
        }
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        // 找不到key返回null
        if (node == null) {
            System.out.println("没有这个key");
            return null;
        }
        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = delete(node.left, key);
        } else if (comp > 0) {
            node.right = delete(node.right, key);
        } else {
            // 若右子节点为null，证明要删除的结点没有右子节点，返回左子节点，
            if (node.right == null) {
                return node.left;
            }
            // 若左子节点为null，证明要删除的结点没有左子节点，返回又子节点，
            if (node.left == null) {
                return node.right;
            }
            // 执行到这里证明，左右字节点都有，变量t在方法结束后就释放内存了，
            Node t = node;
            // 找到右字节点中最小的key的结点替代要被删除的node结点，将node指向新的结点
            node = min(t.right);
            // 删除右子节点中的最小的结点，并将node指向右字节点
            node.right = deleteMin(t);
            // 将node指向原来的左子节点，
            node.left = t.left;
            // 更新N的值
            node.N = size(node.left) + size(node.right) + 1;
            // 更新链接
        }
        return node;
    }

    public void print() {
        showKeys(root);
    }

    /**
     * 按顺序打印指定node下面的所有key
     *
     * @param node 被打印的node
     * @return void
     */
    private void showKeys(Node node) {
        if (node == null) {
            return;
        }
        showKeys(node.left);
        System.out.println(node.key);
        showKeys(node.right);
    }

    /**
     * 判断表是否为空
     *
     * @param: []
     * @return: boolean
     */
    private boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {
    }
}

package chapter3.section2;

import chapter1.section3.Queue;

/**
 * 基于二叉树的符号表的实现
 * <p>
 * 每一个结点包含一个左子节点、右子节点、键值对、子树的结点数量(包含结点本身)
 * 每一个节点中的key的值都大于它的左子节点的中的key的值，小于它的右子节点中的key的值
 *
 * @Auther: yusiming
 * @Date: 2018/9/23 16:00
 */
public class BST<Key extends Comparable<Key>, Value> {
    /**
     * 整个二叉树的根节点
     */
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

    /**
     * @return 返回表中键值对的数量
     */
    public int size() {
        return size(root);
    }

    /**
     * 返回指定结点的子树中结点的数量，包含结点本身
     *
     * @param node 结点
     * @return 数量
     */
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
     * @param key 键
     * @return 键对应的值
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        return get(root, key);
    }

    /**
     * 在以node为根节点的树下查找key对应的value的值
     * <p>
     * 若不存在返回null
     *
     * @param node 指定结点
     * @return Value
     */
    private Value get(Node node, Key key) {
        if (key == null) {
            return null;
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
     * @param key   键
     * @param value 值
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("键不能为空");
        }
        root = put(root, key, value);
    }

    /**
     * 在以指定的结点为根的子树中，插入键值对
     * <p>
     * 此方法返回更新过的树结点，
     *
     * @param node  结点
     * @param key   键
     * @param value 值
     * @return 更新过后的结点
     */
    private Node put(Node node, Key key, Value value) {
        /*
         * put方法的思想：
         * 若key存在于树中，则更新value的值
         * 若key不存在于树中，则创建新的结点，
         * 并更新经过递归调用的结点的子结点，和N的值，
         * 可以将递归调用前的代码，想象成沿着树往下走
         * 将递归调用后的代码，想象成沿着树往上爬，边爬边更新结点
         */
        if (node == null) {
            return new Node(key, value, 1);
        }
        int camp = key.compareTo(node.key);
        if (camp < 0) {
            // 递归并跟新左子节点
            node.left = put(node.left, key, value);
        } else if (camp > 0) {
            // 递归并跟新右子节点
            node.right = put(node.right, key, value);
        } else {
            // 更新value的值
            node.value = value;
        }
        // 更新N的值
        node.N = size(node.left) + size(node.right) + 1;
        // 返回更新过的结点
        return node;
    }

    /**
     * 返回表中最小的键
     *
     * @return key
     */
    public Key min() {
        if (isEmpty()) {
            throw new RuntimeException("表为空");
        }
        return min(root).key;
    }

    /**
     * 返回以指定结点为根的子树中最小的key
     * <p>
     * 若结点的左子节点为空，证明该结点的key就是最小的
     *
     * @param node 结点
     * @return Key
     */
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            // 若node的左子结点不为空，那么到node.left结点找最小的结点，直到有一个结点的左子节点为空
            return min(node.left);
        }
    }

    /**
     * 返回表中最大的key
     *
     * @return Key
     */
    public Key max() {
        if (isEmpty()) {
            throw new RuntimeException("表为空");
        }
        return max(root);
    }

    /**
     * 返回以指定结点为根的子树中最大的key
     * <p>
     * 若结点的右子节点为空，证明该结点的key就是最大的
     *
     * @param node 结点
     * @return Key
     */
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
     * 返回指定根节点的树中，小于等于key的最大结点
     *
     * @param node 结点
     * @param key  键
     * @return node   小于等于key的最大结点
     */
    private Node floor(Node node, Key key) {
        /*
         * 若key就等于node.key，那么node.key就是小于等于key的最大结点
         * 若key小于node.key，那么小于等于key的最大节点在node.left节点中，
         * 若key大于node.key，那么只有当node.right中有小于等于key的结点时，node.right中才存在小于等于key的最大结点
         * 若node.right中的结点都大于key，那么node就是小于等于key的最大结点
         */
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
     * @param key 键
     * @return Key 键
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
        // 若leftSize大于n，则继续在左子节点中寻找第n个数
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
     * 删除指定结点中的最小的结点
     *
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
        // 返回更新过的node
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
     * 返回所有key的集合
     *
     * @param: []
     * @return: java.lang.Iterable<Key>
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * 返回表中start 到 end 范围中的key的集合
     *
     * @param start 开始位置
     * @param end   结束
     * @return: java.lang.Iterable<Key>
     */
    public Iterable<Key> keys(Key start, Key end) {
        if (start == null) {
            throw new IllegalArgumentException("start位置不能为null");
        }
        if (end == null) {
            throw new IllegalArgumentException("end位置不能为null");
        }
        Queue<Key> queue = new Queue<>();
        keys(root, queue, start, end);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key start, Key end) {
        if (node == null) {
            return;
        }
        int compareToStart = start.compareTo(node.key);
        int compareToEnd = end.compareTo(node.key);
        // 若start 小于node.key 证明node左子树有可能有key在start和end中间
        if (compareToStart < 0) {
            keys(node.left, queue, start, end);
        }
        // 若key在start和end中间，添加到队列中
        if (compareToStart <= 0 && compareToEnd >= 0) {
            queue.enqueue(node.key);
        }
        // 若end大于key证明，右子树有可能有key在 start和end中间
        if (compareToEnd >= 0) {
            keys(node.right, queue, start, end);
        }
    }

    /**
     * 判断表是否为空
     *
     * @return boolean
     */
    private boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {
    }
}

package chapter3.section1;

/**
 * 基于二分查找的有序数组实现符号表
 *
 * @Auther: yusiming
 * @Date: 2018/9/22 18:49
 * @Description:
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int size) {
        keys = (Key[]) new Comparable[size];
        values = (Value[]) new Comparable[size];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * @auther: yusiming
     * @date: 20:07 2018/9/22
     * @param: [key]
     * @return: Value
     */
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        // 返回小于key 的key的个数,
        int rank = rank(key, 0, N - 1);
        // 若 a[key] 存在的话,rank 的值也就是key在数组中的位置了，
        if (rank < N && key.compareTo(keys[rank]) == 0) {
            return values[rank];
        }
        return null;
    }

    /**
     * 向符号表中添加键值对
     *
     * @auther: yusiming
     * @date: 20:14 2018/9/22
     * @param: [key, value]
     * @return: void
     */
    public void put(Key key, Value value) {
        int rank = rank(key, 0, N - 1);
        if (rank < N && key.compareTo(keys[rank]) == 0) {
            values[rank] = value;
        }
        // 从最后一个元素开始，将所有keys数组values和的元素向后移动一格
        for (int i = N; i > rank; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[rank] = key;
        values[rank] = value;
        N++;
    }

    /**
     * 该方法返回小于指定key 的 key的个数,基于递归实现，
     *
     * @auther: yusiming
     * @date: 19:27 2018/9/22
     * @param: [key]
     * @return: int
     */
    public int rank(Key key, int start, int end) {
        if (start > end) {
            /*
             * 若keys数组中不存在该键，我们来分析最后两次比较
             * 当只剩两个元素需要比较时，这时候 middle = start + (end - start) / 2;
             * 若 key < keys[middle],end = middle - 1; start 就大于 end 了，这时候循环结束，返回 start 位置是合理的，
             * 若 key > keys[middle],start = middle + 1 ,此时 start = end ,循环还未结束，middle = start = end ;
             * 若再次比较时，key < keys[middle], end = middle - 1,循环结束，返回start 位置合理，
             * 若 key > keys[middle], start = middle + 1,start > end 循环结束，返回start 位置也是合理的，
             */
            return start;
        }
        int middle = start + (end - start) / 2;
        int comp = key.compareTo(keys[middle]);
        if (comp < 0) {
            return rank(key, start, middle - 1);
        } else if (comp > 0) {
            return rank(key, middle + 1, end);
        } else {
            return middle;
        }
    }

    // 返回最小的键
    public Key min() {
        return keys[0];
    }

    // 返回最大的键
    public Key max() {
        return keys[N - 1];
    }

    // 排名为k的键
    public Key select(int k) {
        return keys[k - 1];
    }

    // 大于等于key的最小键
    public Key ceiling(Key key) {
        int rank = rank(key, 0, N);
        return keys[rank];
    }

    // 小于等于key的最大键，
    public Key floor(Key key) {
        int rank = rank(key, 0, N);
        return keys[rank - 1];
    }

    // 删除键
    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }
        int rank = rank(key, 0, N);
        if (rank < N && key.compareTo(keys[rank]) == 0) {
            // rank之后的每一个元素向前移动一个位置
            for (int i = rank; i < N; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
        }
        N--;
    }

    public static void main(String[] args) {
        BinarySearchST<Integer, Integer> binarySearchST = new BinarySearchST<>(20);
        for (int i = 0; i < 10; i++) {
            binarySearchST.put(i, i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(binarySearchST.get(i));
        }

    }
}


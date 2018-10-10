package chapter1.section3;

/**
 * Dijkstra的双栈算术表达式求值算法
 * <p>
 * 使用两个栈来存放数据，一个存放操作数，一个存放操作符
 * 每读取一个字符，如果是操作数，存入栈中，如果是操作符，存入操作符栈中，
 * 如果遇到了一个“)”右括号，弹出一个操作符，和两个操作数，计算结果，
 * 将结果重新压入栈中，重复以上步骤
 *
 * @Auther yusiming
 * @Date 2018/10/10 16:11
 */
public class Evaluate {
    /**
     * 存放操作符
     */
    private Stack<String> ops;
    /**
     * 存放操作数
     */
    private Stack<Double> vals;

    public Evaluate() {
        this.ops = new Stack<>();
        this.vals = new Stack<>();
    }

    public double evaluate(String s) {
        String[] all = s.split(" ");
        for (int i = 0; i < all.length; i++) {
            String s1 = all[i];
            if (s1.equals("(")) {
            } else if (s1.equals("+") || s1.equals("-") || s1.equals("*") || s1.equals("/")) {
                ops.push(s1);
            } else if (s1.equals(")")) {
                String op = ops.pop();
                double va = vals.pop();
                if (op.equals("+")) {
                    vals.push(va + vals.pop());
                }
                if (op.equals("-")) {
                    vals.push(va - vals.pop());
                }
                if (op.equals("*")) {
                    vals.push(va * vals.pop());
                }
                if (op.equals("/")) {
                    vals.push(va / vals.pop());
                }
            } else {
                vals.push(Double.parseDouble(s1));
            }
        }
        return vals.pop();
    }

    public static void main(String[] args) {
        Evaluate evaluate = new Evaluate();
        // 101.0
        System.out.println(evaluate.evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
    }
}

package com.chen.calculator;

/**
 * 加减乘除计算器
 * 1. 支持 Integer(int) 或者 Long(long) 类型数据的加减乘除（目前不支持其他类型的 需要增加需要在getResult()方法里面加类型）
 * 2. 支持多次撤销，多次取消撤销
 *
 * @Author chenwenlin
 * @Date 2023-09-12 20:37
 */
public class Calculator<T> {
    /**
     * 备忘录对象
     */
    private final Memento memento;

    /**
     * 构造方法
     */
    public Calculator(Memento memento) {
        this.memento = memento;
    }

    /**
     * 获取计算的返回结果
     */
    public Number getResult() {
        return memento.getPre();
    }

    /**
     * 加
     *
     * @param num 请求参数
     */
    public void add(T num) {
        // 拿到上一个结果
        Number total = memento.getPre();
        Number result = getResult(total, num, "+");
        // 保存结果值
        memento.todo(result);
    }

    /**
     * 减
     *
     * @param num 请求参数
     */
    public void sub(T num) {
        // 拿到上一个结果
        Number total = memento.getPre();
        Number result = getResult(total, num, "-");
        // 保存结果值
        memento.todo(result);
    }

    /**
     * 乘
     *
     * @param num 请求参数
     */
    public void multiply(T num) {
        // 拿到上一个结果
        Number total = memento.getPre();
        Number result = getResult(total, num, "*");
        // 保存结果值
        memento.todo(result);
    }

    /**
     * 除
     *
     * @param num 请求参数
     */
    public void divide(T num) {
        // 拿到上一个结果
        Number total = memento.getPre();
        Number result = getResult(total, num, "/");
        // 保存结果值
        memento.todo(result);
    }

    /**
     * 撤销
     */
    public void undo() {
        // 撤销
        memento.undo();
    }

    /**
     * 取消撤销
     */
    public void redo() {
        // 取消撤销
        memento.redo();
    }

    /**
     * 根据结果类型获取返回结果
     *
     * @param total
     * @param num
     * @param operator 请求参数
     * @return {@link Number}
     */
    private Number getResult(Number total, T num, String operator) {
        switch (operator) {
            case "+":
                if (total instanceof Long || total instanceof Integer) {
                    return Long.parseLong(total.toString()) + Long.parseLong(num.toString());
                } else if (total instanceof Double || total instanceof Float) {
                    return Double.parseDouble(total.toString()) + Double.parseDouble(num.toString());
                }
                break;
            case "-":
                if (total instanceof Long || total instanceof Integer) {
                    return Long.parseLong(total.toString()) - Long.parseLong(num.toString());
                } else if (total instanceof Double || total instanceof Float) {
                    return Double.parseDouble(total.toString()) - Double.parseDouble(num.toString());
                }
                break;
            case "*":
                if (total instanceof Long || total instanceof Integer) {
                    return Long.parseLong(total.toString()) * Long.parseLong(num.toString());
                } else if (total instanceof Double || total instanceof Float) {
                    return Double.parseDouble(total.toString()) * Double.parseDouble(num.toString());
                }
                break;
            case "/":
                return Double.parseDouble(total.toString()) / Double.parseDouble(num.toString());
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println("--------------Long加减乘除 undo redo--------------");
        Calculator<Long> calculatorLong = new Calculator(new Memento(0));

        // 撤销 结果 0(默认值) (因为没有可以撤销的指令了)
        calculatorLong.undo();
        System.out.println(calculatorLong.getResult());

        // 结果1
        calculatorLong.add(1L);
        System.out.println(calculatorLong.getResult());

        // 结果11
        calculatorLong.add(10L);
        System.out.println(calculatorLong.getResult());

        // 结果 2.75
        calculatorLong.divide(4L);
        System.out.println(calculatorLong.getResult());

        // 结果 -1.25
        calculatorLong.sub(4L);
        System.out.println(calculatorLong.getResult());

        // 结果 -5.0
        calculatorLong.multiply(4L);
        System.out.println(calculatorLong.getResult());


        // 撤销 结果 -1.25
        calculatorLong.undo();
        System.out.println(calculatorLong.getResult());

        // 取消撤销 结果 -5.0
        calculatorLong.redo();
        System.out.println(calculatorLong.getResult());

        // 取消撤销 结果还是 -5.0 (因为没有撤销指令了)
        calculatorLong.redo();
        System.out.println(calculatorLong.getResult());

        // 结果 5.0
        calculatorLong.multiply(-1L);
        System.out.println(calculatorLong.getResult());

        /*System.out.println("--------------Integer加减乘除 undo redo--------------");
        Calculator<Integer> calculatorInteger = new Calculator(new Memento(0));
        // 撤销 结果 0(默认值) (因为没有可以撤销的指令了)
        calculatorInteger.undo();
        System.out.println(calculatorInteger.getResult());

        // 结果1
        calculatorInteger.add(1);
        System.out.println(calculatorInteger.getResult());

        // 结果11
        calculatorInteger.add(10);
        System.out.println(calculatorInteger.getResult());

        // 结果 2.75
        calculatorInteger.divide(4);
        System.out.println(calculatorInteger.getResult());

        // 结果 -1.25
        calculatorInteger.sub(4);
        System.out.println(calculatorInteger.getResult());

        // 结果 -5.0
        calculatorInteger.multiply(4);
        System.out.println(calculatorInteger.getResult());


        // 撤销 结果 -1.25
        calculatorInteger.undo();
        System.out.println(calculatorInteger.getResult());

        // 取消撤销 结果 -5.0
        calculatorInteger.redo();
        System.out.println(calculatorInteger.getResult());

        // 取消撤销 结果还是 -5.0 (因为没有撤销指令了)
        calculatorInteger.redo();
        System.out.println(calculatorInteger.getResult());

        // 结果 5.0
        calculatorInteger.multiply(-1);
        System.out.println(calculatorInteger.getResult());*/
    }
}
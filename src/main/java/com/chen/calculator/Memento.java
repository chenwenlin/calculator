package com.chen.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录 保存每次计算后的数据
 *
 * @Author chenwenlin
 * @Date 2023-09-12 20:33
 */
public class Memento {
    /**
     * 存放每次计算后的结果 （不包含已撤销的结果）
     */
    private final List<Number> historyTotal = new ArrayList<>();
    /**
     * 保存每次撤销的结果
     */
    private final List<Number> undoHistory = new ArrayList<>();
    /**
     * 计算器的超时值
     */
    private final Number initValue;

    public Memento(Number initValue) {
        this.initValue = initValue;
    }

    /**
     * 获取结果
     */
    public Number getPre() {
        if (historyTotal.isEmpty()) {
            return initValue;
        }
        return historyTotal.get(historyTotal.size() - 1);
    }

    /**
     * 保存备忘录的值
     *
     * @param num 请求参数
     */
    public void todo(Number num) {
        historyTotal.add(num);

        // 每次重新计算之后，之前的撤销没有用了
        undoHistory.clear();
    }

    /**
     * 撤销
     */
    public void undo() {
        if (historyTotal.isEmpty()) {
            // 表示不能再撤销 返回初始值
            return;
        }
        Number pop = historyTotal.remove(historyTotal.size() - 1);
        undoHistory.add(pop);
    }

    /**
     * 取消撤销
     */
    public void redo() {
        if (undoHistory.isEmpty()) {
            // 没有可以取消撤销的了
            return;
        }
        // 下一个结果
        Number undo = undoHistory.get(undoHistory.size() - 1);
        historyTotal.add(undo);
    }
}
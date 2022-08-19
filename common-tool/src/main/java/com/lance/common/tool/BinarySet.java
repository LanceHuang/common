package com.lance.common.tool;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * 二进制集，可处理局部的大量整数
 *
 * @author Lance
 * @since 2022/8/19
 */
public class BinarySet {

    /** 数据集 */
    private final BitSet dataSet;

    /** 低位 */
    private int low = Integer.MAX_VALUE;

    /** 高位 */
    private int high = Integer.MIN_VALUE;

    public BinarySet(int bits) {
        this.dataSet = new BitSet(bits);
    }

    /**
     * 判断数据是否存在
     *
     * @param bitIndex 数据
     * @return 判断结果
     */
    public boolean contains(int bitIndex) {
        return this.dataSet.get(bitIndex);
    }

    /**
     * 添加数据
     *
     * @param bitIndex 数据
     */
    public void add(int bitIndex) {
        if (this.low > bitIndex) {
            this.low = bitIndex;
        }
        if (this.high < bitIndex) {
            this.high = bitIndex;
        }
        this.dataSet.set(bitIndex);
    }

    /**
     * 删除数据
     *
     * @param bitIndex 数据
     */
    public void remove(int bitIndex) {
        this.dataSet.clear(bitIndex);
    }

    /**
     * 清空
     */
    public void clear() {
        this.dataSet.clear();
        this.low = Integer.MAX_VALUE;
        this.high = Integer.MIN_VALUE;
    }

    /**
     * 判断是否为空
     *
     * @return 判断结果
     */
    public boolean isEmpty() {
        return this.dataSet.isEmpty();
    }

    /**
     * 获取低位
     *
     * @return 低位
     */
    public int getLow() {
        return low;
    }

    /**
     * 获取高位
     *
     * @return 高位
     */
    public int getHigh() {
        return high;
    }

    /**
     * 转换为整型数据
     *
     * @return 整型数组
     */
    public Set<Integer> toIntSet() {
        if (isEmpty()) {
            return new HashSet<>(0);
        }

        Set<Integer> result = new HashSet<>();
        for (int i = low; i <= high; i++) {
            if (this.dataSet.get(i)) {
                result.add(i);
            }
        }
        return result;
    }
}

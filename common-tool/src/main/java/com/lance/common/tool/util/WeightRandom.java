package com.lance.common.tool.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Suppose that you have a program which can reward sth randomly, how can you do that?
 * Example:
 * <ul>
 * <li>5%, you get everything</li>
 * <li>10%, you get something</li>
 * <li>85%, you get nothing</li>
 * </ul>
 * <p>
 * Demo:
 * <pre>
 *     WeightRandom<RewardType> weightRandom = new WeightRandom<RewardType>();
 *     weightRandom.add(5, RewardType.ALL);
 *     weightRandom.add(10, RewardType.STH);
 *     weightRandom.add(85, RewardType.NOTHING);
 *     RewardType reward = weightRandom.next();
 * </pre>
 * <p>
 * You can use RewardType.NOTHING(Sth like that) to express 'Reward nothing'.
 *
 * @param <T> Type of output
 * @author Lance
 * @since 2018-8-18 15:30:42
 */
public class WeightRandom<T> {

    private List<Integer> weightList;
    private List<T> outputList;

    private Random random;
    private int totalWeight;

    public WeightRandom() {
        weightList = new LinkedList<>();
        outputList = new ArrayList<>();
        random = new Random();
    }

    /**
     * Set the weight of output
     *
     * @param weight N+
     * @param output Anything not null
     */
    public void add(int weight, T output) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }

        if (null == output) {
            throw new IllegalArgumentException("Output must not be null.");
        }
        weightList.add(weight);
        outputList.add(output);
        totalWeight += weight;
    }

    /**
     * Next output, but not remove duplicated data.
     */
    public T next() {
        if (weightList.isEmpty() || outputList.isEmpty()) {
            throw new IllegalStateException("You must invoke add(int, T) before invoke next().");
        }

        int randomVal = random.nextInt(totalWeight);
        int accumulator = 0;

        int i = 0;
        for (int weight : weightList) {
            accumulator += weight;
            if (accumulator > randomVal) {
                break;
            }
            i++;
        }
        return outputList.get(i);
    }
}

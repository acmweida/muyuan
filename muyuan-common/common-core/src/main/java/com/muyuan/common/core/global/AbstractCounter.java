package com.muyuan.common.core.global;

/**
 * @ClassName AbstractCounter
 * Description AbstractCounter
 * @Author 2456910384
 * @Date 2022/7/29 10:10
 * @Version 1.0
 */
public abstract class AbstractCounter implements Counter {

    protected static int DEFAULT_START = 0;

    protected static int DEFAULT_STEP = 1;

    protected String name;

    protected int start;

    protected int step;

    public AbstractCounter(String name, int start, int step) {
        this.name = name;
        this.start = start;
        this.step = step;
    }
}

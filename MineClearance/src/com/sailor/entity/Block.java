package com.sailor.entity;

/**
 * 格子类
 */
public class Block {
    private String name; //
    private int number; // 附近含雷个数
    private Boolean mark = false; // 标记是否为雷

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }
}

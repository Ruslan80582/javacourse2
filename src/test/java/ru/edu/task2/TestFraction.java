package ru.edu.task2;

import lombok.Getter;

public class TestFraction implements Fractionable{

    int num, denum;
    int count = 0;

    public TestFraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    @Override
    public double doubleValue() {
        count++;
        return num/denum;
    }

    @Override
    public void setNum(int num) {

    }

    @Override
    public void setDenum(int denum) {

    }
}

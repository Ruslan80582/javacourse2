package ru.edu.task2;

public class Fraction implements Fractionable{
    private int num;
    private int denum;

    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDenum(int denum) {
        this.denum = denum;
    }

    @Override
    public double doubleValue() {
        System.out.println("invoke double value");
        return (double) num/denum;
    }
}

;

;

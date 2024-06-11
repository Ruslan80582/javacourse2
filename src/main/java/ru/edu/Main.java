package ru.edu;

import ru.edu.task1.exceptions.UndoException;
import ru.edu.task2.CustomInvocationHandler;
import ru.edu.task2.Fraction;
import ru.edu.task2.Fractionable;

public class Main {
    public static void main(String[] args) throws UndoException {
        Fraction fr = new Fraction(2,3);
        Fractionable num = CustomInvocationHandler.createProxy(fr);
        num.doubleValue();// sout сработал
        num.doubleValue();// sout молчит
        num.doubleValue();// sout молчит
        num.setNum(5);
        num.doubleValue();// sout сработал
        num.doubleValue();// sout молчит

    }
}
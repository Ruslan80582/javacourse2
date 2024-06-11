package ru.edu.task2;

import ru.edu.task2.annotations.Cache;
import ru.edu.task2.annotations.Mutator;

public interface Fractionable{
    @Cache
    double doubleValue();
    @Mutator
    void setNum(int num) ;
    @Mutator
    void setDenum(int denum) ;
}

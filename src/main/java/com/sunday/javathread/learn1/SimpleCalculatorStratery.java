package com.sunday.javathread.learn1;

public class SimpleCalculatorStratery implements CalcatorStratery {
    private  final  double SALARY_RATE=0.1;
    private final  double BONUS_RATE=0.1;



    @Override
    public double caclate(double salary, double bonus) {
        return salary*SALARY_RATE+bonus*BONUS_RATE;
    }
}

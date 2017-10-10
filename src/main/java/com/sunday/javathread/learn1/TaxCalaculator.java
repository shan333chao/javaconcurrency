package com.sunday.javathread.learn1;

public class TaxCalaculator {
    private  final  double salary;
    private final  double bonus;
    private  CalcatorStratery calcatorStratery;


    protected double calcTax(){
        return calcatorStratery.caclate(salary,bonus);
    }

    public  double calculate(){
        return this.calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public TaxCalaculator(double salary, double bonus, CalcatorStratery calcatorStratery) {
        this.salary = salary;
        this.bonus = bonus;
        this.calcatorStratery = calcatorStratery;
    }
}

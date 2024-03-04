package org.application;
public class App 
{
    public static void main( String[] args ) {
        ValueCalculator calculator = new ValueCalculator();
        calculator.operationsWithMainThread();
        calculator.setNumOfThreads(10);
        calculator.operationsWithMultiplyThreads();
    }
}

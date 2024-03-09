package org.application;
public class App 
{
    public static void main( String[] args ) {
        ValueCalculator calculator = new ValueCalculator();
        calculator.operationsWithMultiplyThreads();
        calculator.setNumOfThreads(2);
        calculator.operationsWithMultiplyThreads();
        calculator.setNumOfThreads(5);
        calculator.operationsWithMultiplyThreads();
        calculator.setNumOfThreads(10);
        calculator.operationsWithMultiplyThreads();

    }
}

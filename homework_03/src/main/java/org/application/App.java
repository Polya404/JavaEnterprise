package org.application;
public class App 
{
    public static void main( String[] args ) {
        ValueCalculator calculator = new ValueCalculator();
        calculator.operationsWithMultipleThreads();
        calculator.setNumOfThreads(2);
        calculator.operationsWithMultipleThreads();
        calculator.setNumOfThreads(5);
        calculator.operationsWithMultipleThreads();
        calculator.setNumOfThreads(10);
        calculator.operationsWithMultipleThreads();

    }
}

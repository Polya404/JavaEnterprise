# Java Enterprise
## lesson_03 :
basic multithreading 
- ways to start a new thread
- exchange of data between threads
### homework_03 :
   1. 1. Create the ValueCalculator class
      2. In the ValueCalculator class, add:
      - property-an array of real numbers
      - a property that displays the size of the array (minimum 1,000,000)
      - a property that displays half the size of the array
      3. In the ValueCalculator class, add a method. The method performs:
      - Record the start time of method execution
      - Fill the array with ones or any other equal values
      - Split the array into two arrays of the same size
      - Create two streams, in each of which one of the previously obtained arrays will be passed.
        Assign new values to its value formed by the expression: (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2))
      - Perform reverse gluing of two arrays into one initial one
      - Calculate the time spent from the start to the end of the program and output it to the console using two threads and using one thread
      - Shuffle the data in the array (shuffle randomly so that the values are unsorted)
      - Add logic that searches for the largest element in a given array
      - Do it through several threads (choose the number of threads yourself, for example, do it through 2, 5 and 10 threads and compare how many threads the program found the maximum value the fastest)
package samples;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.testng.AssertJUnit.assertEquals;


public class Time {
    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter Date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter Time = DateTimeFormatter.ofPattern("hh:mm:ss a");


    @Test
    public void testExample() throws Exception{
        // Use the timestamp in your test
        System.out.println("Current time is: " + now.format(Time) );
        // perform your appium test actions using the timestamp as required

        Thread.sleep(4000);
    }
    @Test
    public void testExample2() {
        // Get the current time

        // Use the timestamp in your test
        System.out.println("Current time is: " + now.format(Time));
        // perform your appium test actions using the timestamp as required
    }




}


//    public static void main(String[] args) {
//        // Get the current time
//        LocalTime currentTime = LocalTime.now();
//
//        // Print the current time in the default format
//        System.out.println("Current time is: " + currentTime);
//
//        // Print the current time in a custom format
//        System.out.println("Current time is: " + currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());

//    private LocalTime currentTime;
//
//    @BeforeTest
//    public void setUp() {
//        currentTime = LocalTime.of(12, 0, 0); // set current time to 12:00:00
//    }
//
//    @Test
//    public void test1() {
//        // Use the current time in the test
//        assertEquals(currentTime, LocalTime.of(12, 0, 0));
//    }
//
//    @Test
//    public void test2() {
//        // Use the current time in the test
//        assertEquals(currentTime, LocalTime.of(12, 0, 0));
//    }

//    @Test
//    public void myTest() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//
//        for (int i = 0; i < 100; i++) {
//            LocalDateTime currentTime = LocalDateTime.now();
//            System.out.println("Current time is: " + formatter.format(currentTime));
//
//            try {
//                Thread.sleep(5000); // Sleep for 5 seconds
//            } catch (InterruptedException e) {
//                // Handle the exception if necessary
//            }
//        }

        // Rest of your test code goes here
//    }

//    @Test
//    public void myTest() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//
//        for (int i = 0; i < 100; i++) {
//            LocalDateTime currentTime = LocalDateTime.now();
//            System.out.println("Current time is: " + formatter.format(currentTime));
//        }
//
//        // Rest of your test code goes here
//    }
//
//    // Format the time using a DateTimeFormatter
//    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
//    // Get the current time
//    LocalTime currentTime = LocalTime.now();
//    String Time = currentTime.format(formatter);

//    @Test
//    public static void main(String[]args) throws Exception{
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.a");
//
//        LocalDateTime currentTime = LocalDateTime.now();
//        System.out.println("Current time is: " + formatter.format(currentTime));
//        Thread.sleep(3000);
//        System.out.println("Current time is: " + formatter.format(currentTime));
//        Thread.sleep(3000);
//        System.out.println("Current time is: " + formatter.format(currentTime));
//
//
//    @Test
//    // define your test method here
//    public void testMethod() {
//        // your test code goes here
//
//        System.out.println("hello");
//        // call the method that includes the timer code at the end of the test
//        printDatePeriodically();
//
//        System.out.println("hello");
//        printDatePeriodically();
//    }
//
//    // define the method that includes the timer code
//    public void printDatePeriodically() {
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            public void run() {
//                System.out.println(new Date());
//            }
//        };
//        timer.schedule(task, 0, 3000);
//    }
//}

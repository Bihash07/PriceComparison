package samples;

import tests.priceComparisonTuk;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class codeScheduler {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = new Runnable() {
            public void run() {
                // execute the batch file every 30 minutes
                try {
//                    priceComparisonTuk.test1();
//                    priceComparisonTuk.test12_copyCsvFile();
//                    priceComparisonTuk.test13_sendAutoMail();
//                    priceComparisonTuk.Test14_deleteCsvFile();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        };
        // schedule the task to run every 30 minutes
        scheduler.scheduleAtFixedRate(task, 0, 4, TimeUnit.HOURS);
    }
}

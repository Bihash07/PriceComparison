package samples;

import com.opencsv.CSVWriter;
import org.testng.annotations.Test;
import utils.Assets;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class createCSV {

    @Test(priority = 3)
    public void test11_createCSV() throws Exception{

    // Create a CSVWriter object
    CSVWriter csvWriter = new CSVWriter(new FileWriter(Assets.csvFile, StandardCharsets.UTF_8),
            ',', '"', CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

    // Define column headers
    String[] headers = {"Distance(KM)", "PickUp Location", "Drop Location", "Date", "Time", "Uber Tuk price", "Pickme Tuk price"};

    // Write headers to CSV file
        csvWriter.writeNext(headers);

//
//    String[] row1 = {"1", Assets.PICKUP_LOCATION_01 , Assets.DROP_LOCATION_01, Assets.date, Time1,uberTukPrice1, pickmeTukPrice1};
//    String[] row2 = {"2", Assets.PICKUP_LOCATION_02, Assets.DROP_LOCATION_02, Assets.date, Time2, uberTukPrice2, pickmeTukPrice2};
//    String[] row3 = {"3", Assets.PICKUP_LOCATION_03, Assets.DROP_LOCATION_03, Assets.date, Time3, uberTukPrice3, pickmeTukPrice3};
//    String[] row4 = {"4", Assets.PICKUP_LOCATION_04, Assets.DROP_LOCATION_04, Assets.date, Time4,  uberTukPrice4, pickmeTukPrice4};
//    String[] row5 = {"5", Assets.PICKUP_LOCATION_05, Assets.DROP_LOCATION_05, Assets.date, Time5, uberTukPrice5, pickmeTukPrice5};
//    String[] row6 = {"6", Assets.PICKUP_LOCATION_06, Assets.DROP_LOCATION_06, Assets.date, Time6, uberTukPrice6, pickmeTukPrice6};
//    String[] row7 = {"7", Assets.PICKUP_LOCATION_07, Assets.DROP_LOCATION_07, Assets.date, Time7, uberTukPrice7, pickmeTukPrice7};
//    String[] row8 = {"8", Assets.PICKUP_LOCATION_08, Assets.DROP_LOCATION_08, Assets.date, Time8, uberTukPrice8, pickmeTukPrice8};
//    String[] row9 = {"9", Assets.PICKUP_LOCATION_09, Assets.DROP_LOCATION_09, Assets.date, Time9, uberTukPrice9, pickmeTukPrice9};
//    String[] row10 = {"10", Assets.PICKUP_LOCATION_10, Assets.DROP_LOCATION_10, Assets.date , Time10, uberTukPrice10, pickmeTukPrice10};
//
//    // Write data rows to CSV file
//        csvWriter.writeNext(row1);
//        csvWriter.writeNext(row2);
//        csvWriter.writeNext(row3);
//        csvWriter.writeNext(row4);
//        csvWriter.writeNext(row5);
//        csvWriter.writeNext(row6);
//        csvWriter.writeNext(row7);
//        csvWriter.writeNext(row8);
//        csvWriter.writeNext(row9);
//        csvWriter.writeNext(row10);

    // Close CSV writer
        csvWriter.close();

        System.out.println(Assets.Magenta + "Data written to " + Assets.Yellow + Assets.csvFile);
    }
}

package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Assets {

    public static final LocalDateTime now = LocalDateTime.now();
    public static final String dateTime = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy__hh.mm.ss"));
    public static final String date = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    public static final String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));


    // PICKME
    public static final String PICKME_PLATFORM_NAME = "Android" ;
    public static final String PICKME_DEVICE_NAME = "A13" ;
    public static final String PICKME_UDID = "RF8TB0F3P0A" ;
    public static final String PICKME_PLATFORM_VERSION = "13" ;
    public static final String PICKME_APP_PACKAGE =  "com.pickme.passenger" ;
    public static final String PICKME_APP_ACTIVITY = "com.pickme.passenger.feature.account.presentation.LandingActivity" ;

    // UBER
    public static final String  UBER_PLATFORM_NAME = "Android" ;
    public static final String  UBER_DEVICE_NAME = "M02" ;
    public static final String  UBER_UDID = "R58R63FKHSB" ;
    public static final String  UBER_PLATFORM_VERSION = "11" ;
    public static final String  UBER_APP_PACKAGE = "com.ubercab" ;
    public static final String  UBER_APP_ACTIVITY = "com.ubercab.presidio.app.core.root.RootActivity" ;


    // Set the port numbers for the Appium servers
    public static final int appiumPort1 = 4723;
    public static final int appiumPort2 = 4724;


    // Destination folder path
    public static final String destinationFolder = "C:/Users/Device-farm/Desktop/Cost_Comparison";

    public static final String csvFile = "Cost_Comparison_" + dateTime + ".csv";








    // -- COLOR -- //
    public static final String Red = "\u001b[31m";
    public static final String Green = "\u001B[32m";
    public static final String Yellow = "\u001b[33m";
    public static final String Blue = "\u001b[34m";
    public static final String Magenta = "\u001b[35m";
    public static final String Cyan = "\u001b[36m";

}

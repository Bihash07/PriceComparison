package utils;

import org.openqa.selenium.By;

public class ElementLocator {

    // -- PICKME ELEMENTS -- //
    public static final By PICKME_RIDE_BUTTON = By.xpath("//android.view.ViewGroup[@index='0'][@resource-id='com.pickme.passenger:id/clOuterLayer']");
    public static final By PICKME_PICKUP_LOCATION_SEARCH = By.id("com.pickme.passenger:id/tvAddressBarPickup");
    public static final By PICKME_DROP_LOCATION_SEARCH = By.id("com.pickme.passenger:id/tvAddressBarDrop");
    public static final By PICKME_PICKUP_LOCATION_INPUT = By.id("com.pickme.passenger:id/etAddressBarPickup");
    public static final By PICKME_DROP_LOCATION_INPUT = By.id("com.pickme.passenger:id/etAddressBarDrop");
    public static final By PICKME_PICKUP_LOCATION_BAR = By.xpath("//android.widget.FrameLayout[@index=0][@resource-id='com.pickme.passenger:id/layout_cardview']");
    public static final By PICKME_DROP_LOCATION_BAR = By.xpath("//android.widget.FrameLayout[@index=0][@resource-id='com.pickme.passenger:id/layout_cardview']");
    public static final By PICKME_BACK_BUTTON = By.id("com.pickme.passenger:id/btnConfirmBookNowClose");
    public static final By PICKME_BACK_BUTTON2 = By.id("com.pickme.passenger:id/iv_back");


    // PICKME PRICE ELEMENTS
    public static final By PICKME_SURGE =  By.xpath("");
    public static final By PICKME_VEHICLE_LAYOUT = By.xpath("//android.widget.RelativeLayout[@resource-id='com.pickme.passenger:id/rlvdv']");
    public static final By PICKME_VEHICLE_NAME = By.xpath("//android.widget.RelativeLayout[@resource-id='com.pickme.passenger:id/rlvdv']//android.widget.TextView[@resource-id='com.pickme.passenger:id/tvDynamicVehicleName']");

    public static final By PICKME_VEHICLE_TUK_TEXT = By.xpath("//android.widget.TextView[@text='Tuk'][@resource-id='com.pickme.passenger:id/tvDynamicVehicleName']");
    public static final By PICKME_VEHICLE_FLEX_TEXT = By.xpath("//android.widget.TextView[@text='Flex'][@resource-id='com.pickme.passenger:id/tvDynamicVehicleName']");
    public static final By PICKME_VEHICLE_CAR_TEXT = By.xpath("//android.widget.TextView[@text='Car'][@resource-id='com.pickme.passenger:id/tvDynamicVehicleName']");

    public static final By PICKME_PRICE1 = By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.RelativeLayout//android.widget.TextView[@resource-id='com.pickme.passenger:id/tvRideEstimatedv']");

    public static final By PICKME_PRICE = By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.RelativeLayout[@index=0]//android.widget.TextView[@resource-id='com.pickme.passenger:id/tvRideEstimatedv']");
    public static final By PICKME_PRICE_WITHOUT_DISCOUNT = By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.RelativeLayout[@index=0]//android.widget.TextView[@resource-id='com.pickme.passenger:id/subscriptionDiscountTextview']");


    // -- UBER ELEMENTS -- //
    public static final By UBER_RIDE_BUTTON = By.xpath("//android.view.ViewGroup[@index=0]/android.view.ViewGroup[@resource-id='com.ubercab:id/small_nav_grid_tile_item_overlay'] ");
    public static final By UBER_PICKUP_LOCATION_SEARCH= By.id("com.ubercab:id/ub__location_edit_search_pickup_view");
    public static final By UBER_DROP_LOCATION_SEARCH = By.id("com.ubercab:id/ub__location_edit_search_destination_view");
    public static final By UBER_PICKUP_LOCATION_INPUT = By.id("com.ubercab:id/ub__location_edit_search_pickup_edit");
    public static final By UBER_DROP_LOCATION_INPUT = By.id("com.ubercab:id/ub__location_edit_search_destination_edit");
    public static final By UBER_PICKUP_LOCATION_BAR = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[@index=0]");
    public static final By UBER_DROP_LOCATION_BAR = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[@index=0]");

    public static final By UBER_BACK_BUTTON1 = By.xpath("//android.widget.FrameLayout[@resource-id='com.ubercab:id/ub__menu']//android.widget.FrameLayout");
    public static final By UBER_BACK_BUTTON2 = By.xpath("//android.widget.FrameLayout[@resource-id='com.ubercab:id/ub__menu']//android.widget.FrameLayout");
    public static final By UBER_BACK_BUTTON3 = By.xpath("//android.widget.ImageView[@content-desc=\"Menu\"]");




//    // UBER PRICE ELEMENTS
//    public static final  List<String>  = Arrays.asList( "//android.view.ViewGroup[@content-desc=\"No product description available.\"]/android.widget.FrameLayout[3]/android.widget.TextView",
//            "//android.view.ViewGroup[@content-desc=\"No product description available.\"]/android.widget.FrameLayout[2]/android.widget.TextView",
//            "//androidx.recyclerview.widget.RecyclerView[@index=3]//android.view.ViewGroup[@index=1]//android.widget.FrameLayout[@index=4]//android.widget.TextView");

//    public static final By UBER_PRICE1 = By.xpath("//android.view.ViewGroup[@content-desc=\"No product description available.\"]/android.widget.FrameLayout[3]/android.widget.TextView");
//    public static final By UBER_PRICE2 = By.xpath("//androidx.recyclerview.widget.RecyclerView[@index=3]//android.view.ViewGroup[@index=1]//android.widget.FrameLayout[@index=4]//android.widget.TextView");
//    public static final By UBER_PRICE3 = By.xpath("//android.view.ViewGroup[@content-desc=\"No product description available.\"]/android.widget.FrameLayout[2]/android.widget.TextView");

    public static final By UBER_VEHICLE_TUK_TEXT = By.xpath("//android.widget.TextView[@text='Tuk'][@resource-id='com.ubercab:id/ub__default_title_cell_element_view']");
    public static final By UBER_VEHICLE_ZIP_TEXT = By.xpath("//android.widget.TextView[@text='Zip'][@resource-id='com.ubercab:id/ub__default_title_cell_element_view']");
    public static final By UBER_VEHICLE_PREMIER_TEXT = By.xpath("//android.widget.TextView[@text='Uber Premier'][@resource-id='com.ubercab:id/ub__default_title_cell_element_view']");

    public static final By UBER_SURGE =  By.xpath("//android.widget.TextView[@content-desc=\"Fares are slightly higher due to increased demand\"]");
    public static final By UBER_PRICE = By.xpath("//android.widget.FrameLayout[@index=4][@resource-id='com.ubercab:id/ub__default_fare_cell_element_view']//android.widget.TextView[@index=0]");
    public static final By UBER_PRICE2 = By.xpath("");

    public static final By UBER_PRICE_WITHOUT_DISCOUNT = By.xpath("//android.widget.TextView[@resource-id='com.ubercab:id/ub__default_promo_cell_element_view']");






//    WebElement uberVehicle = driver.findElement(By.id("example-id1"));
//    WebElement uberVehiclePrice = driver.findElement(By.className("example-class"));
//
//    String text = uberVehicle.getText("Tuk") + uberVehicle.getText();


//    // Locate the first element using multiple attributes
//    WebElement uberVehicleName = driver.findElement(ElementLocator.UBER_VEHICLE_NAME);
//
//    // Retrieve the text of the first element
//    String uberVehicleNameText = uberVehicleName.getText();
//
//// Check if the text of the first element is equal to "Tuk"
//    uberVehicleNameText.equals("Tuk")) {
//        // Locate the second element using the text of the first element
//        WebElement secondElement = driver.findElement(By.xpath("//element[text()='" + firstElementText + "']/following-sibling::element"));
//
//        // Retrieve the text of the second element
//        String secondElementText = secondElement.getText();
//
//        // Do something with the text of the second element
//        System.out.println("The text of the second element is: " + secondElementText);
//    }



}

package utils;

import com.google.j2objc.annotations.Weak;
import org.openqa.selenium.WebElement;

public class GetPrice {

    public static void pickmeTukPrice(){
        WebElement knownElement = AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_VEHICLE_TUK_TEXT);

        WebElement parentElement = knownElement.findElement(ElementLocator.PICKME_VEHICLE_LAYOUT);

        WebElement desiredElement = parentElement.findElement(ElementLocator.PICKME_PRICE);
        String desiredText = desiredElement.getText();

    }
    public static void pickmeTukPriceWithoutDiscount(){

    }
    public static void pickmeFlexPrice(){

    }
    public static void pickmeFlexPriceWithoutDiscount(){

    }
    public static void pickmeMiniPrice(){

    }
    public static void pickmeMiniPriceWithoutDiscount(){

    }
    public static void uberTukPrice(){

    }
    public static void uberTukPriceWithoutDiscount(){

    }
    public static void uberZipPrice(){

    }
    public static void uberZipPriceWithoutDiscount(){

    }
    public static void pickmePrimePrice(){

    }
    public static void pickmePrimePriceWithoutDiscount(){

    }



    public static void main(String[] args){

        GetPrice.uberTukPrice();

    }
}

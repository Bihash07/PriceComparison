package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import jdk.dynalink.beans.StaticClass;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppiumServer1 {

    static AppiumDriverLocalService server1;

    public static void setInstance1() {

        // Set the path to the Node.js executable
        String nodePath = "C:\\Program Files\\nodejs\\node.exe";

        // Set the path to the Appium executable
        String appiumPath = "C:\\Users\\Device-farm\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

        // Create the first AppiumServiceBuilder instance for the first Appium server
        AppiumServiceBuilder builder1 = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodePath))
                .withAppiumJS(new File(appiumPath))
                .usingPort(Assets.appiumPort1)// Use any free port for the Appium server
                .withArgument(GeneralServerFlag.BASEPATH, "wd/hub")
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                .withArgument(GeneralServerFlag.RELAXED_SECURITY);

        // Create the first AppiumDriverLocalService instance using the first AppiumServiceBuilder
        server1 = builder1.build();
    }

    static AppiumDriverLocalService getInstance1(){

        if (server1 == null) {
            setInstance1();
        }
        return server1;
    }

    public static void startServer() {
        getInstance1().start();
        if (server1.isRunning()) {
            System.out.println(Assets.Green + "Appium server started successfully on port " + Assets.appiumPort1);
            System.out.println(server1.isRunning());
        } else {
            System.out.println("Appium server failed to start on port " + Assets.Red + Assets.appiumPort1);
        }
    }

    public static void stopServer(){
        if (server1 != null){
            getInstance1().stop();
            System.out.println("Appium servers stopped successfully on port " + Assets.appiumPort1 );
        } else {
            System.out.println("Appium servers failed to stop on port " + Assets.appiumPort1 );
        }
    }


}
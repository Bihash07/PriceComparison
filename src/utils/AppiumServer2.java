package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


import java.io.File;

public class AppiumServer2 {

    static AppiumDriverLocalService server2;

    public static void setInstance2() {

        // Set the path to the Node.js executable
        String nodePath = "C:\\Program Files\\nodejs\\node.exe";

        // Set the path to the Appium executable
        String appiumPath = "C:\\Users\\Device-farm\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

        // Create the first AppiumServiceBuilder instance for the first Appium server
        AppiumServiceBuilder builder1 = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodePath))
                .withAppiumJS(new File(appiumPath))
                .usingPort(Assets.appiumPort2)// Use any free port for the Appium server
                .withArgument(GeneralServerFlag.BASEPATH, "wd/hub")
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                .withArgument(GeneralServerFlag.RELAXED_SECURITY);

        // Create the first AppiumDriverLocalService instance using the first AppiumServiceBuilder
        server2 = builder1.build();
    }
    static AppiumDriverLocalService getInstance2(){

        if (server2 == null) {
            setInstance2();
        }
        return server2;
    }

    public static void startServer(){
        getInstance2().start();
        if (server2.isRunning()) {
            System.out.println( Assets.Green + "Appium server started successfully on port " + Assets.appiumPort2 );
            System.out.println(server2.isRunning());
        } else {
            System.out.println("Appium server failed to start on port " + Assets.Red + Assets.appiumPort2 );
        }
    }
    public static void stopServer(){
        if (server2 != null){
            getInstance2().stop();
            System.out.println("Appium servers stopped successfully on port " + Assets.appiumPort2 );
        } else {
            System.out.println("Appium servers failed to stop on port " + Assets.appiumPort2 );
        }
    }


    public static void main(String []args) throws Exception{
        AppiumServer1.startServer();
        AppiumServer2.startServer();
        Thread.sleep(5000);
        AppiumServer1.stopServer();
        AppiumServer2.stopServer();
    }
}





//    public static void setInstance() {
//
//        // Set the path to the Node.js executable
//        String nodePath = "C:\\Program Files\\nodejs\\node.exe";
//
//        // Set the path to the Appium executable
//        String appiumPath = "C:\\Users\\Device-farm\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
//
//        // Create the first AppiumServiceBuilder instance for the first Appium server
//        AppiumServiceBuilder builder1 = new AppiumServiceBuilder()
//                .usingDriverExecutable(new File(nodePath))
//                .withAppiumJS(new File(appiumPath))
//                .usingPort(Assets.appiumPort1)// Use any free port for the Appium server
//                .withArgument(GeneralServerFlag.BASEPATH, "wd/hub")
//                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
//                .withArgument(GeneralServerFlag.RELAXED_SECURITY);
//
//        // Create the first AppiumDriverLocalService instance using the first AppiumServiceBuilder
//        server1 = builder1.build();

//        // Start the first Appium server
//        service1.start();
//
//        // Print the server status
//        if (service1.isRunning()) {
//            System.out.println(service2.getUrl() );
//            System.out.println( Assets.Green + "Appium server started successfully on port " + Assets.appiumPort1 );
//        } else {
//            System.out.println("Appium server failed to start on port " + Assets.Red + Assets.appiumPort1 );
//        }

//        // Create the second AppiumServiceBuilder instance for the second Appium server
//        AppiumServiceBuilder builder2 = new AppiumServiceBuilder()
//                .usingDriverExecutable(new File(nodePath))
//                .withAppiumJS(new File(appiumPath))
//                .usingPort(Assets.appiumPort2)// Use any free port for the Appium server
//                .withArgument(GeneralServerFlag.BASEPATH, "wd/hub")
//                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
//                .withArgument(GeneralServerFlag.RELAXED_SECURITY);
//
//        // Create the second AppiumDriverLocalService instance using the second AppiumServiceBuilder
//        server2 = builder2.build();

//        // Start the second Appium server
//        service2.start();
//
//        // Print the server status
//        if (service2.isRunning()) {
//            System.out.println(service2.getUrl());
//            System.out.println( Assets.Green + "Appium server started successfully on port " + Assets.appiumPort2 );
//        } else {
//            System.out.println("Appium server failed to start on port " + Assets.Red + Assets.appiumPort2 );
//        }
//    }

//    @AfterTest
//    public static void stopServer() {
//        // Stop the Appium servers
//        server1.stop();
//        server2.stop();
//
//        // Print the server status
//        if (server1.isRunning() || server2.isRunning()) {
//            System.out.println("Appium servers failed to stop");
//        } else {
//            System.out.println("Appium servers stopped successfully");
//        }
//    }




















//    public static void main(String[] args) throws IOException {
//        // Start the first Appium server instance with port 4723
//        ProcessBuilder pb1 = new ProcessBuilder("appium", "--port", "4723");
//        try {
//            pb1.start();
//            System.out.println("Started Appium server on port 4723");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Start the second Appium server instance with port 4724
//        ProcessBuilder pb2 = new ProcessBuilder("appium", "--port", "4724");
//        try {
//            pb2.start();
//            System.out.println("Started Appium server on port 4724");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//


//    public static void startServer (int port1,int port2) {
//        // Set the path to the Node.js executable
//        String nodePath = "C:\\Program Files\\nodejs\\node.exe"; // Replace with the actual path to Node.js executable on your machine
//
//        // Set the path to the Appium server JavaScript file
//        String appiumPath = "C:\\Users\\Device-farm\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
//        // Replace with the actual path to the Appium server file on your machine
//
//        // Create an instance of AppiumServiceBuilder
//        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
//                .usingDriverExecutable(new File(nodePath))
//                .withAppiumJS(new File(appiumPath))
//                .withIPAddress("0.0.0.0") // Set the IP address of the machine where you want to start the Appium server
//                .usingPort(port1)// Use any free port for the Appium server
//                .withArgument(GeneralServerFlag.BASEPATH, "wd/hub")
//                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
//                .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, String.valueOf(port2))
//                .withArgument(GeneralServerFlag.RELAXED_SECURITY);
//
//
//        // Start the Appium server
//        server = AppiumDriverLocalService.buildService(serviceBuilder);
//        server.start();
//
//        System.out.println(server.getUrl());
//        System.out.println(server.isRunning());
//
//
//        if (!server.isRunning()) {
//            throw new AppiumServerHasNotBeenStartedLocallyException("An error occurred while starting the Appium server");
//        }
//        // Perform your test automation tasks here
//
////        // Stop the Appium server
////        service.stop();
//    }
//    public static void main (String[]args){
//
//        int pickmePort = 4723;
//        int uberPort = 4724;
//        AppiumServer.startServer(pickmePort,uberPort);
//    }
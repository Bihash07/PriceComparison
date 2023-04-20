package samples;


import com.opencsv.CSVWriter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import utils.ElementLocator;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class newcz {


    String PICKUP_LOCATION_01 = "Liberty Plaza";
    String DROP_LOCATION_01 = "446, R.D. Mel Mawatha Col 3";
    String PICKUP_LOCATION_02 = "Liberty Plaza";
    String DROP_LOCATION_02 = "44, Lauris Rd, Col 4";
    String PICKUP_LOCATION_03 = "Liberty Plaza";
    String DROP_LOCATION_03 = "21, Dickmans Rd, Col 4";
    String PICKUP_LOCATION_04 = "Liberty Plaza";
    String DROP_LOCATION_04 = "14, Dharmarama Rd, Col 3";
    String PICKUP_LOCATION_05 = "Liberty Plaza";
    String DROP_LOCATION_05 = "Viharampark";
    String PICKUP_LOCATION_06 = "Liberty Plaza";
    String DROP_LOCATION_06 = "21, Galle Rd, Kalubowila";
    String PICKUP_LOCATION_07 = "Liberty Plaza";
    String DROP_LOCATION_07 = "Viharamahadevi park";
    String PICKUP_LOCATION_08 = "Liberty Plaza";
    String DROP_LOCATION_08 = "Viharamahadevi park";
    String PICKUP_LOCATION_09 = "Liberty Plaza";
    String DROP_LOCATION_09 = "Viharamahadevi park";
    String PICKUP_LOCATION_10 = "Liberty Plaza";
    String DROP_LOCATION_10 = "Viharamahadevi park";


    // Destination folder path
    String destinationFolder = "C:/Users/Bihash Arulampalam/Desktop/Cost_Comparison";

    // Sender and recipient email addresses
    String from_mail = "bihash.arulampalam@pickme.lk"; //change accordingly

    ArrayList<String> to_mail = new ArrayList<>(); //change accordingly


    List<String> cc_recipient = new ArrayList<String>();

    public List<String> getCc_recipient() {
        return cc_recipient;
    }

    DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
    // Get the current timestamp
    LocalDateTime now = LocalDateTime.now();
    String dateTime = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy__hh.mm.ss"));


    String emailSubject = "Price comparison on "+ date.format(now) + "__" + time.format(now);
    String emailBody = """
            Dear all,

            Please find the attachment of Price comparison for the Uber & Pickme.""";


    // SMTP server settings
    String username = "bihash.arulampalam@pickme.lk"; //change accordingly
    String password = "qxblthbkdiiscjzg"; //change accordingly
    String host = "smtp.gmail.com"; //change accordingly
    int port = 587; //change accordingly

    String csvFile = "Cost_Comparison_" + dateTime + ".csv";

    String Red = "\u001b[31m";
    String Green = "\u001B[32m";
    String Yellow = "\u001b[33m";
    String Blue = "\u001b[34m";
    String Magenta = "\u001b[35m";
    String Cyan = "\u001b[36m";


    // Create two instances for each device
    public AndroidDriver pickmeDriver, uberDriver, drivers;
    public WebElement pickmePrice;

    private Timer timer;

    @BeforeClass
    public void setup() throws Exception {

        ArrayList<AndroidDriver> drivers = new ArrayList<>();


        // Set DesiredCapabilities for Pickme app to send to the appium Server
        DesiredCapabilities pickmeCap = new DesiredCapabilities();
        pickmeCap.setCapability("platformName","Android" );
        pickmeCap.setCapability("deviceName","A13");
        pickmeCap.setCapability("udid", "RF8TB0F3P0A");
        pickmeCap.setCapability("platformVersion", "13");
        pickmeCap.setCapability("appPackage", "com.pickme.passenger");
        pickmeCap.setCapability("appActivity", "com.pickme.passenger.feature.account.presentation.LandingActivity");
        pickmeCap.setCapability("noReset", true);
        pickmeCap.setCapability("ensureWebviewsHavePages", true);
        pickmeCap.setCapability("nativeWebScreenshot", true);
        pickmeCap.setCapability("connectHardwareKeyboard", true);

        // Set DesiredCapabilities for Uber app to send to the appium Server
        DesiredCapabilities uberCap = new DesiredCapabilities();
        uberCap.setCapability("platformName", "Android");
        uberCap.setCapability("deviceName", "M02");
        uberCap.setCapability("udid", "R58R63FKHSB");
        uberCap.setCapability("platformVersion", "11");
        uberCap.setCapability("appPackage", "com.ubercab");
        uberCap.setCapability("appActivity", "com.ubercab.presidio.app.core.root.RootActivity");
        uberCap.setCapability("noReset", true);
        uberCap.setCapability("ensureWebviewsHavePages", true);
        uberCap.setCapability("nativeWebScreenshot", true);
        uberCap.setCapability("connectHardwareKeyboard", true);


        // Create AppiumDriver instances for each device
        // Initialize driver 2 for Uber App
        uberDriver = new AndroidDriver(new URL ("http://localhost:4724/wd/hub"), uberCap);
        System.out.println( "Uber App was launched. \n");
        drivers.add(uberDriver);

        pickmeDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), pickmeCap);
        System.out.println("Pickme App was launched.");
        drivers.add(pickmeDriver);

        Thread.sleep(7000);
    }


    @BeforeMethod
    public void startTimer() {
        // Start the timer to run the test loop every 15 minutes
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    testLoop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 15 * 60 * 1000); // 15 minutes in milliseconds
    }


    // -- Automation Test -- //
    @Test(priority = 1)
    public void testLoop() throws Exception {

        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
        drivers.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);


        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
        WebElement el1 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
        el1.click();
        el1.click();

        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(PICKUP_LOCATION_01);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(PICKUP_LOCATION_01);
        drivers.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
        drivers.hideKeyboard();

        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();

        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(DROP_LOCATION_01);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(DROP_LOCATION_01);
        drivers.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
        drivers.hideKeyboard();

        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();



        WebElement uberTukPriceTxt = uberDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"No product description available.\"]/android.widget.FrameLayout[3]/android.widget.TextView"));
        String uberTukPrice1 = uberTukPriceTxt.getText();


        WebElement pickmeTukPriceTxt = pickmeDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/" +
                "android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/androidx.recyclerview.widget.RecyclerView/" +
                "android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
        String pickmeTukPrice1 = pickmeTukPriceTxt.getText();



        System.out.println(Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Yellow + uberTukPrice1 + Blue + ".\n " +
                "Location: " + Yellow + PICKUP_LOCATION_01 + Blue + "  to " + Yellow + DROP_LOCATION_01 + Blue + ".\n " +
                "Date: " + Yellow + date.format(now) + Blue + ".\n " +
                "Time: " + Yellow + time.format(now) + ". \n" + Blue + "\n" +
                " Pickme Tuk price: " + Yellow + pickmeTukPrice1 + Blue + ".\n " +
                "Location: " + Yellow + PICKUP_LOCATION_01 + Blue + "  to " + Yellow + DROP_LOCATION_01 + Blue + ".\n " +
                "Date: " + Yellow + date.format(now) + Blue + ".\n " +
                "Time: " + Yellow + time.format(now) + ".\n");


        File pickmePrice = ((TakesScreenshot) pickmeDriver).getScreenshotAs(OutputType.FILE);
        File uberPrice = ((TakesScreenshot) uberDriver).getScreenshotAs(OutputType.FILE);

        drivers.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

        // Example: Wait for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Example: Navigate back
        drivers.navigate().back();


        // Create a CSVWriter object
        CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile, StandardCharsets.UTF_8),
                ',', '"', CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

        // Define column headers
        String[] headers = {"Distance(KM)", "PickUp Location", "Drop Location", "Date", "Time", "Uber Tuk price", "Pickme Tuk price"};

        // Write headers to CSV file
        csvWriter.writeNext(headers);


//        // Define data rows
//       List <String[]> row = new ArrayList<>();
//
//
//        for (String row : row ){
//            // Write data rows to CSV file
//            csvWriter.writeNext(row);
//        }

//        String[] row1 = {"", PICKUP_LOCATION_01, DROP_LOCATION_01, date.format(now), time.format(now), uberTukPrice1, pickmeTukPrice1};
//        String[] row2 = {"", PICKUP_LOCATION_02, DROP_LOCATION_02, date.format(now), time.format(now), uberTukPrice2, pickmeTukPrice2};
//        String[] row3 = {"", PICKUP_LOCATION_03, DROP_LOCATION_03, date.format(now), time.format(now), uberTukPrice3, pickmeTukPrice3};
//        String[] row4 = {"", PICKUP_LOCATION_04, DROP_LOCATION_04, date.format(now), time.format(now), uberTukPrice4, pickmeTukPrice4};
//        String[] row5 = {"", PICKUP_LOCATION_05, DROP_LOCATION_05, date.format(now), time.format(now), uberTukPrice5, pickmeTukPrice5};
//        String[] row6 = {"", PICKUP_LOCATION_06, DROP_LOCATION_06, date.format(now), time.format(now), uberTukPrice6, pickmeTukPrice6};
//        String[] row7 = {"", PICKUP_LOCATION_07, DROP_LOCATION_07, date.format(now), time.format(now), uberTukPrice7, pickmeTukPrice7};
//        String[] row8 = {"", PICKUP_LOCATION_08, DROP_LOCATION_08, date.format(now), time.format(now), uberTukPrice8, pickmeTukPrice8};
//        String[] row9 = {"", PICKUP_LOCATION_09, DROP_LOCATION_09, date.format(now), time.format(now), uberTukPrice9, pickmeTukPrice9};
//        String[] row10 = {"", PICKUP_LOCATION_10, DROP_LOCATION_10, date.format(now), time.format(now), uberTukPrice10, pickmeTukPrice10};

//        // Write data rows to CSV file
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

        System.out.println( Magenta + "Data written to " + Yellow + csvFile);



    }


    @Test(priority = 9)
    public void Test8_copyCsvFile() throws Exception{
        try {
            // Create a File object for the source file
            File sourceFile = new File(csvFile);

            // Create a File object for the destination folder
            File destinationDir = new File(destinationFolder);

            // Create the destination folder if it does not exist
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            // Create a File object for the destination file
            File destinationFile = new File(destinationDir, csvFile);

            // Copy the source file to the destination file
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println( Cyan + "File copied to destination folder: " + Yellow + destinationFile.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Failed to copy file.");
            e.printStackTrace();
        }
    }

    @Test(priority = 10)
    public void Test9_sendAutoMail() {

        // Mail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create session with authentication
        // Get the Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {

            // Create a new message
            Message message = new MimeMessage(session); // Create a default MimeMessage object.
            message.setFrom(new InternetAddress(from_mail)); // Set From: header field of the header.


            for (String toMail : to_mail){
                // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            }
            for (String ccRecipient : cc_recipient){
                // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccRecipient));
            }

            // Adding CC recipients
            message.setSubject(emailSubject); // Set Subject: header field

            // Create a multipart message to hold the text and signature
            Multipart multipart = new MimeMultipart(); // Create a multipart message

            // Add the text part of the message
            BodyPart bodyPart = new MimeBodyPart(); // Create the message part
            bodyPart.setText(emailBody); // Now set the actual message
            multipart.addBodyPart(bodyPart); // Set text message part

            // -- File attachment -- //
            DataSource source = new FileDataSource(csvFile);
            bodyPart.setDataHandler(new DataHandler(source));
            bodyPart.setFileName(csvFile);
            multipart.addBodyPart(bodyPart);


            // Set the multipart as the content of the message
            message.setContent(multipart);  // Send the complete message parts
            Transport.send(message); // Send message

            System.out.println( Green + "Email sent successfully" + Blue +
                    " from " + Yellow + from_mail + Blue +
                    " to " + Yellow + to_mail + Blue +
                    "\n CC " + Yellow + cc_recipient);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 11)
    public void Test10_deleteCsvFile() throws Exception{
        // Convert the file path to Path object
        Path path = Paths.get(csvFile);

        // Delete the file
        Files.delete(path);
        System.out.println( Red + "File deleted: " + Yellow + csvFile);
    }


    @AfterMethod
    public void stopTimer() {
        // Stop the timer after each test run
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @AfterClass
    public void teardown() {
        // Close the driver
        drivers.quit();
    }

    public void set(){

    }
}

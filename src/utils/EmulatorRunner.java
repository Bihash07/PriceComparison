package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EmulatorRunner {

    public static void main(String[] args) {
        try {
            // Specify the AVD (Android Virtual Device) name
            String avdName = "emulator_name"; // Replace with the name of your AVD

            // Execute the emulator command
            Process process = Runtime.getRuntime().exec("emulator -avd " + avdName);

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the emulator to start (optional)
            Thread.sleep(5000); // Adjust the delay as needed

            // Perform additional actions with the running emulator
            // ...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


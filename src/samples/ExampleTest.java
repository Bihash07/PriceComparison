package samples;

public class ExampleTest {
    public static void main(String[] args) {
        Object[][] inputData = {
                {"input1", "Expected result for input 1"},
                {"input2", "Expected result for input 2"},
                {"input3", "Expected result for input 3"}
        };

        for (Object[] data : inputData) {
            String input = (String) data[0];
            String expectedOutput = (String) data[1];

            runTest(input, expectedOutput);
        }
    }

    private static void runTest(String input, String expectedOutput) {
        // Perform test with given input
        System.out.println("Running test with input: " + input);
        // ...
        // Assertions and other test logic go here
        // ...

        // Print result
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Test result: Passed\n");
    }
}

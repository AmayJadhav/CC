import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverterClient {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter amount in INR: ");
            String inrAmount = scanner.nextLine();

            // Validate input (optional)
            if (inrAmount.isEmpty()) {
                System.out.println("INR amount cannot be empty.");
                return;
            }

            // Create URL with query parameter
            URL url = new URL("http://127.0.0.1:5000/convert?inr=" + inrAmount);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Handle response
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Failed: HTTP error code: " + responseCode);
                return;
            }

            // Read and print response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

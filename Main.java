import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        process cpu = new process("top -n 5 -l 2 | grep -E \"CPU usage\"");
        cpu.startProcess();
        String cpuUsage = cpu.getMessage();
        System.out.println(cpuUsage);

        String apiUrl = "need_api_to_be_built";
        sendPostRequest(apiUrl, cpuUsage);
    }
    private static void sendPostRequest(String apiUrl, String cpuUsage) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Send the CPU usage info in the request body
            OutputStream os = connection.getOutputStream();
            os.write(cpuUsage.getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            System.out.println("POST Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
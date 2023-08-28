import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Process;
import java.util.Scanner;

public class process {
    private String Command;
    private String DiagnosticMessage;
    private ProcessBuilder processBuild;
    private java.lang.Process process;
    private InputStream inputStream;
    private static Scanner scnr;
    public process(String Command){
        this.Command = Command;
    }
    public void startProcess() throws IOException, InterruptedException {
        String[] shellCommand = { "bash", "-c", Command };

        Process process = new ProcessBuilder(shellCommand).start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        DiagnosticMessage = output.toString();

        int exitCode = process.waitFor();
    }
    public String getMessage(){
        return DiagnosticMessage;
    }
}

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        process cpu = new process("top -n 5 -l 2 | grep -E \"CPU usage\"");
        cpu.startProcess();
        System.out.println(cpu.getMessage());
    }
}
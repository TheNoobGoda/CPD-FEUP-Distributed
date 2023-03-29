import java.net.*;
import java.util.Scanner;
import java.io.*;

public class CalculatorClient {
    public static void main(String[] args) {
        if (args.length < 2) return;
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        Scanner scanner = new Scanner(System.in);
 
        try (Socket socket = new Socket(hostname, port)) {
            
            while(true){
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                System.out.println("Enter number:");
                String num = scanner.nextLine();
                writer.println(num);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                
                String sum = reader.readLine();
                if (Integer.parseInt(num) == 0){
                    System.out.println(sum);
                    break;
                } 
                
                System.out.println(sum);
            }
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
        scanner.close();
    }
    
}

import java.net.*;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class CalculatorServer extends Thread {
    Socket socket;
    public static int total = 0;
    public CalculatorServer(Socket socket){
        this.socket = socket;
    }

    public static void main(String[] args) {
        if (args.length < 1) return;
 
        int port = Integer.parseInt(args[0]);
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                CalculatorServer thread = new CalculatorServer(socket);
                
                thread.start();
 
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run(){
        System.out.println("new thread");
        int sum =0;
        //ReentrantLock reentrantLock = new ReentrantLock();
        try {
            while(true){
                InputStream input = socket.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String num = reader.readLine();
                if (Integer.parseInt(num) ==0){
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    //reentrantLock.lock();
                    total += sum;
                    //wait(10);
            
                    writer.println(String.valueOf(total));
                    //reentrantLock.unlock();
                    break;
                } 

                System.out.println("adding: "+ num);

                sum += Integer.parseInt(num);

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                writer.println(String.valueOf(sum));
            }    
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}

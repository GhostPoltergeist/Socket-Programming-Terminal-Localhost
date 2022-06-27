import java.io.*;
import java.net.Socket;

public class Client {
    static private String dataOne = "", dataTwo = "";
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 3454);
        DataInputStream dataInput = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));

     while(!dataOne.equals("stop")) {
         System.out.println("\n\n");
         System.out.println("[x]Client-Connected[x]");
         dataOne = reader.readLine();
         System.out.print("\nMessage: ");
         dataOutput.writeUTF(dataOne);
         dataOutput.flush();
         dataTwo = dataInput.readUTF();
         System.out.println("Server-Message: " + dataTwo);
     }
    }
}
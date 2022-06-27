import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;

public class Server {
     static String dataOne = "";
     static String dataTwo = "";
     static String serverFunctionality;
    private static String name = "";
    static final int portNumber = 3454;
    static Scanner scanner = new Scanner(System.in);

       Server(String serverFunctionality, String name, int portNumber) throws IOException {
        boolean settingThingsUp = true;
           while (settingThingsUp) {
               System.out.print("\nServerName: ");
               Server.name = scanner.next();
               settingThingsUp = false;
               settingServer();
               /* Enable The (this) in the constructor if your port number
               * is not (final) or there will be an error
               * this.portNumber = portNumber;*/
           }

       }
    public static void main(String[] args) throws InterruptedException, IOException, InvocationTargetException, IllegalAccessException, SocketException {
        System.out.print("\n\n[x]Set-LocalServer[x]");
        Thread.sleep(200);

        System.out.print("\nEnableServer[?][y/n]: ");
        serverFunctionality = scanner.next();

        if (serverFunctionality.equals("y") || serverFunctionality.equals("Y")) {
            new Server(serverFunctionality, name, portNumber);
        } else if (serverFunctionality.equals("n") || serverFunctionality.equals("N")) {
            System.out.println("\n[x]Disabled[x]\nExit..");
            System.exit(0);
        }

        Server check = new Server(serverFunctionality, name, portNumber);

          for (Method method : check.getClass().getDeclaredMethods()) {
              if (method.isAnnotationPresent(isAvailable.class)) {
                  method.invoke(check);
              }
          }

        }
    @isAvailable
    private static void settingServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);

        System.out.println("\nAddress : " + Arrays.toString(new int[]{serverSocket.getLocalPort()}));

        Socket socket = serverSocket.accept();
        DataInputStream dataInput = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOuput = new DataOutputStream(socket.getOutputStream());
        BufferedReader fileReading = new BufferedReader(new InputStreamReader(System.in));

     while(!dataOne.equals("stop")) {
         dataOne = dataInput.readUTF();
         System.out.print("\nClient: " + dataOne);
         System.out.print("\nMessage: ");
         dataTwo = fileReading.readLine();
         dataOuput.writeUTF(dataTwo);
         dataOuput.flush();
     }
     serverSocket.close();
     dataInput.close();
     socket.close();
    }
}
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;
//import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws IOException {

        ServerSocket server = null;

        try {
            server = new ServerSocket(5000);


            while(true){
                System.out.println("Waiting for a client.");
                Socket client = server.accept();
                System.out.println("Client accepted.");
                Thread thread = new Thread(new MyWorker(client));
                thread.start();
                System.out.println("Thread is started.");

            }

        }finally {
            server.close();
        }


    }
}

class MyWorker implements Runnable{

    private final Socket socket;
    private String name;
    private static List<OutputStreamWriter> outputs = new ArrayList<OutputStreamWriter>();
    private static StringBuffer history = new StringBuffer();

    MyWorker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("Socket recieved: " + socket.toString());
        OutputStreamWriter writer = null;
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            writer = new OutputStreamWriter(os);
            outputs.add(writer);

            writer.write("Please enter your name: ");
            writer.flush();
            name = in.readLine();

            writer.write("Welcome " + name + lineSeparator());
            writer.write(history.toString());
            writer.flush();

            String newMessage = in.readLine();

            for(OutputStreamWriter output : outputs){
                output.write(name + " > " + newMessage + lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }finally{
            outputs.remove(writer);
        }
    }
}
import com.example.myapplication.User;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServer {
    private String inputOrOut;
    private Dao userData = new Dao();
    public static void main(String args[]){
        new MyServer();
    }
    public  MyServer(){
        try{
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("local host:" + addr);

            //创建server socket
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("listen port 9999");

            while(true){
                System.out.println("waiting client connect");
                Socket socket = serverSocket.accept();
                System.out.println("accept client connect" + socket);
                BufferedReader in = null;
                try{
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                }catch (IOException ex){
                    ex.printStackTrace();
                }
                inputOrOut = in.readLine();
                if(inputOrOut.equals("login")){
                    System.out.println("login");
                    new Thread(new Login(socket)).start();
                }else{
                    new Thread(new Register(socket)).start();
                    System.out.println("register");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    class Login implements Runnable{
        private Socket socket;

        public Login(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("1");
                User user = (User)ois.readObject();
                PrintWriter pout = null;
                try{
                    pout = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),true);
                    User returnUser = userData.findUser(user);
                    System.out.println("2");
                    if(returnUser!=null) {
                        pout.println("success");
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(returnUser);
                    }else {
                        pout.println("fail");
                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("scuccess");
        }

    }
    class Register implements Runnable{
        private Socket socket;

        public Register(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("1");
                User user = (User)ois.readObject();
                userData.addUser(user);
                System.out.println("REGIS");
                PrintWriter pout = null;
                try{
                    pout = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),true);
                    pout.println("success");
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}

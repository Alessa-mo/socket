import com.example.myapplication.InternetUser;
import com.example.myapplication.User;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameServer implements Runnable{
    public static boolean firstConnect = false;
    public static boolean firstPlayerConnected = false;
    public static boolean secondPlayerConnected = false;

    private InternetUser user1 = new InternetUser(new User("1","1",0),1);
    private InternetUser user2 = new InternetUser(new User("2","2",0),2);

    @Override
    public void run(){
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            //创建server socket
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("listen port 8888");
            while (true){
                System.out.println("waiting client connect");
                Socket socket = serverSocket.accept();
                System.out.println("accept client connect" + socket);
                PrintWriter pout = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),true);
                if(!firstConnect){
                    firstConnect = true;
                    firstPlayerConnected = true;
                    pout.println("1");
                }else{
                    secondPlayerConnected = true;
                    pout.println("2");
                }
                while(!(firstPlayerConnected&&secondPlayerConnected)){

                }
                pout.println("connected");
                ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                if(ois!=null){
                    InternetUser user = (InternetUser) ois.readObject();
                    if(user.getID() == 1){
                        user1 = user;
                        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(user2);
                        firstConnect = false;
                        firstPlayerConnected = false;
                        secondPlayerConnected = false;
                        socket.close();
                    }else if(user.getID()==2){
                        user2 = user;
                        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(user1);
                        firstConnect = false;
                        firstPlayerConnected = false;
                        secondPlayerConnected = false;
                        socket.close();
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

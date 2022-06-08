import com.example.myapplication.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private List<User> records = new ArrayList<User>();
    private File file = new File("user.txt");

    /**
     * 获取文件中所有的用户
     */
    private void getAll(){
        records.clear();
        try (FileInputStream fis = new FileInputStream(file)){
            ObjectInputStream ois;
            while (fis.available()>0) {
                ois = new ObjectInputStream(fis);
                User t = (User) ois.readObject();
                records.add(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 添加用户
     */
    public void addUser(User user){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file,true))){
            oos.writeObject(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 查找用户
     */
    public User findUser(User user){
        getAll();
        for(User users: records){
            if((user.getUsername()).equals(users.getUsername()) && (users.getPassword()).equals(users.getPassword())){
                return users;
            }else{
                return null;
            }
        }
        return null;
    }

}

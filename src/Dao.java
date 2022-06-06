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
    /**
     * 返回用于model的String数组，标识名次、姓名、分数、时间
     */
//    @Override
//    public String[][] printRecord(){
//        getAll();
//        String[][] tableData = new String[records.size()][];
//        Collections.sort(records);
//        for (int i=0; i<records.size(); i++){
//            Record current = records.get(i);
//            int minute = current.getMinute();
//            String minuteString = String.valueOf(minute);
//            if(minute<10){
//                minuteString = '0'+minuteString;
//            }
//            String[] strings = {String.valueOf(i+1),current.getName(),String.valueOf(current.getScore()),current.getDate()+" "+current.getHour()+":"+minuteString};
//            tableData[i] = strings;
//        }
//        return tableData;
//    }

//    @Override
//    public void deleteRecord(int index){
//        records.remove(index);
//        //清空当前文件
//        try {
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.write("");
//            fileWriter.flush();
//            fileWriter.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        //重新写入
//        for(Record curRecord:records){
//            addRecord(curRecord);
//        }
//    }
}

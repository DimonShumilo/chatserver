package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Dimon on 04.01.2016.
 */
public class UserList {
    private static final UserList userList = new UserList();
    private static Properties prop = new Properties();

    private static final List<User> allUsers = new ArrayList<>();
    private static final List<User> onlineUsers = new ArrayList<>();

    static{
        try {

            prop.load(new InputStreamReader(new FileInputStream("D:\\users.properties"), "Cp1251"));
            Set<String> set = prop.stringPropertyNames();
            for (String s : set){
                User u = new User(s, prop.getProperty(s));
                allUsers.add(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UserList(){
    }

    public static UserList getInstance(){
        return userList;
    }

    public String getAllUsersString(){
        StringBuilder strB = new StringBuilder();
        for (User u : allUsers){
            strB.append(u.getNick()).append("\n");
        }
        return strB.toString();
    }

    public String getOnlineUsersString(){
        StringBuilder strB = new StringBuilder();
        for (User u : onlineUsers){
            strB.append(u.getNick()).append("\n");
        }
        return strB.toString();
    }

    public synchronized void add(User u){
        allUsers.add(u);
        onlineUsers.add(u);
        prop.setProperty(u.getNick(), u.getPassword());
        try {
            prop.store(new OutputStreamWriter(new FileOutputStream("D:\\users.properties"), "Cp1251"), "comment");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOnlineUser(User u) {
        onlineUsers.add(u);
    }

    public User getUser(String nick) {
        User user = null;
        for (User u : allUsers){
            if (u.getNick().equals(nick)){
                user = u;
            }
        }
        return user;
    }

    public boolean isUser(String nick) {
        boolean isUser = false;
        for (User u : allUsers){
            if (u.getNick().equals(nick)){
                isUser = true;
            }
        }
        return isUser;
    }
}

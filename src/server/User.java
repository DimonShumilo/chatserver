package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimon on 04.01.2016.
 */
public class User {
    private String nick;
    private String password;
    private String status = "offline";
    private List<Message> privateList = new ArrayList<>();



    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addPrivateMessage(Message msg) {
        privateList.add(msg);
    }

    public synchronized String privateToJSON(int from) {
        List<Message> res = new ArrayList<Message>();
        for (int i = from; i < privateList.size(); i++)
            res.add(privateList.get(i));
        if (res.size() > 0) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(res.toArray());
        } else
            return null;
    }
}

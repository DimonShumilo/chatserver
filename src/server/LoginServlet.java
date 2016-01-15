package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dimon on 04.01.2016.
 */
public class LoginServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nick = req.getParameter("nick");
        String password = req.getParameter("password");

        String response = null;
        boolean isUser = userList.isUser(nick);
        if (isUser) {
            User u = userList.getUser(nick);
            if (u.getPassword().equals(password)){
                response = "Hello " + nick + "!!!";
                userList.addOnlineUser(u);
            }
            else {
                response = "Bad password. Try again.";
            }

        } else {
            userList.add(new User(nick, password));
            response = "Hello " + nick + "!!!";
        }
        resp.getOutputStream().write(response.getBytes());


    }
}

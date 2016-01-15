package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dimon on 05.01.2016.
 */
public class UsersGetServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String which = req.getParameter("which");
        String response = null;

        if (which.equals("online")) {
            response = userList.getOnlineUsersString();

        } else if (which.equals("all")){
           response = userList.getAllUsersString();
        }
        resp.getOutputStream().write(response.getBytes());
    }
}

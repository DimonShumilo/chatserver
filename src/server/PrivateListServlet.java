package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Dimon on 05.01.2016.
 */
public class PrivateListServlet extends HttpServlet{
    private UserList userList = UserList.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        int from = Integer.parseInt(req.getParameter("from"));
        if (userList.isUser(nickname)){
            User user = userList.getUser(nickname);
            String json = user.privateToJSON(from);
            if (json != null) {
                OutputStream os = resp.getOutputStream();
                os.write(json.getBytes());
            }
        }



    }
}

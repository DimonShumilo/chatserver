package server;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();
	private UserList userList = UserList.getInstance();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		InputStream is = req.getInputStream();
		byte[] buf = new byte[req.getContentLength()];
		is.read(buf);
		
		Message msg = Message.fromJSON(new String(buf));

		if (msg != null){
			String to = msg.getTo();
			if (to == null){
				msgList.add(msg);
			} else if (userList.isUser(to)){
				User u = userList.getUser(to);
				u.addPrivateMessage(msg);
			}

		} else
			resp.setStatus(400); //Bad request
	}
}

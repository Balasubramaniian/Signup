package Sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String cpassword=request.getParameter("cpassword");
		PrintWriter out=response.getWriter();
		if(password.equals(cpassword)) {
			User user=new User(username,password);
			try {
				if(signup.insert(user)) {
					response.sendRedirect("Success.html");
				}
			}
			catch(Exception e) {
				response.sendRedirect("Fail.html");
			}
		}
		
		else {
          out.println("welcome "+username+ " your password and confirm password wrong");
		}
	}
	
	public static boolean insert(User u) throws Exception {
	        String url = "jdbc:mysql://localhost:3306/bala";
	        String username = "root";
	        String password = "bala";
	        Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
	        PreparedStatement pre = con.prepareStatement("INSERT INTO SignUp VALUES (?, ?)");
	    
	        pre.setString(1, u.getUsername());
	        pre.setString(2, u.getPassword());

	        int rowsAffected = pre.executeUpdate();
	        return rowsAffected > 0;
	    
	    }
	}



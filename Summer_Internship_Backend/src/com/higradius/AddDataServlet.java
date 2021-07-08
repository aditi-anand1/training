package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddDataServlet
 */
@WebServlet("/AddDataServlet")
public class AddDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER ="com.mys.jdbc.Driver";
	static final String DB_URL ="jdbc:mysql://localhost/my_database";
	
	static final String USER ="root";
	static final String PASSWORD ="aditianand";
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  
		String name_customer = request.getParameter("name_customer");
		 String cust_number = request.getParameter("cust_number");
		 String invoice_id = request.getParameter("invoice_id");
		 String total_open_amount = request.getParameter("total_open_amount");
		 String due_in_date = request.getParameter("due_in_date");
		 String predicted_payment_date = request.getParameter("predicted_payment_date");
		 
		 
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			Statement statement = connection.createStatement();
			int execute_Update=statement.executeUpdate("insert into mytable values('"+name_customer+"','" +cust_number+"', '"+invoice_id+"','"+ total_open_amount+"','"+due_in_date+"','"+ predicted_payment_date+"')");
			
			PrintWriter out =response.getWriter();
			out.println(name_customer);
			out.println(cust_number);
			out.println(invoice_id);
			out.println(total_open_amount);
			out.println(due_in_date);
			out.println(predicted_payment_date);
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		doGet(request, response);
	}

}

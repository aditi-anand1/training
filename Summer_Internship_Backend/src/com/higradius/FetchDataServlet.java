package com.higradius;

/*import java.awt.List;*/
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class FetchDataServlet
 */
@WebServlet("/FetchDataServlet")
public class FetchDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String JDBC_DRIVER ="com.mys.jdbc.Driver";
	static final String DB_URL ="jdbc:mysql://localhost/my_database";
	
	static final String USER ="root";
	static final String PASSWORD ="aditianand";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out =response.getWriter();
		String spageid =request.getParameter("page");
		int pageid =Integer.parseInt(spageid);
		int total=10;
		if(pageid==1) {}
		else {
			pageid =pageid-1;
			pageid=pageid*total+1;
		}
		List<Response> demoList = getRecords(pageid, total);
		String jsonString = getJSONStringFromObject(demoList);
		response.setContentType("application/json");
		try {
			response.getWriter().write(jsonString);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@SuppressWarnings("unused")
	private String getJSONStringFromObject(List<Response> filmList) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(filmList);
		System.out.println(json);
		return json;
	}
	
	private List<Response> getRecords(int start , int total){
		List<Response> list = new ArrayList<Response>();
		Connection conn =null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String sql;
			sql=("select name_customer, cust_number, invoice_id, total_open_amount ,due_in_date, predicted_payment_date,Notes from mytable limit "+(start-1)+","+total);
			
			ResultSet rs =stmt.executeQuery(sql);
			
			while(rs.next()) {
				Response demo = new Response();
				demo.setName_customer(rs.getString("name_customer"));
				demo.setCust_number(rs.getString("cust_number"));
				demo.setInvoice_id(rs.getString("invoice_id"));
				demo.setTotal_open_amount(rs.getString("total_open_amount"));
				demo.setDue_in_date(rs.getString("due_in_date"));
				demo.setPredicted_payment_date(rs.getString("predicted_payment_date"));
				demo.setNotes(rs.getString("Notes"));
				list.add(demo);
			}
			
//			String jsonString = getJSONStringFromObject(list);
//			response.setContentType("application/json");
//			try {
//				response.getWriter().write(jsonString);
//			}catch(IOException e) {
//				e.printStackTrace();
//			}
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		//	response.getWriter().append("Served at: ").append(request.getContextPath());
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}

}

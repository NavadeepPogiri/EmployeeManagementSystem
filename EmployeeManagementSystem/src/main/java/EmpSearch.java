
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpSearch
 */
@WebServlet("/EmpSearch")
public class EmpSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			String name = request.getParameter("name");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "navadeep", "welcome");

			PreparedStatement ps = con.prepareStatement("select * from employee where name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rss = rs.getMetaData();
			out.print("<table border='1'>");

			int n = rss.getColumnCount(); 

			for (int i = 1; i <= n; i++) 

				out.print("<td> <font color=blue size=3> " + "<br>" + rss.getColumnName(i));

			out.println("<tr>");

			while (rs.next())

			{

				for (int i = 1; i <= n; i++)

					out.println("<td><br> " + rs.getString(i));

				out.println("<tr>");

			}

			out.println("</table>");
			
		} catch (Exception ex) {
			out.println(ex);
		}
	}
}

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get parameters
        String deptName = request.getParameter("deptName");
        String countStr = request.getParameter("totalStudent");
        String action = request.getParameter("action");
        
        // 2. Parse the count safely
        int totalStudent = 0;
        if (countStr != null && !countStr.isEmpty()) {
            try {
                totalStudent = Integer.parseInt(countStr);
            } catch (NumberFormatException e) {
                totalStudent = 0; 
            }
        }

        ServiceClass service = new ServiceClass();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><style>");
        out.println("table { border-collapse: collapse; width: 50%; margin-top: 20px; }");
        out.println("th, td { border: 1px solid #333; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("body { font-family: Arial, sans-serif; padding: 20px; }");
        out.println("</style></head><body>");

        out.println("<h2>Action: " + action + "</h2>");

        if (action == null) {
            out.println("<p>No action selected.</p>");
        } else {
            switch (action) {
                case "Insert":
                    if (service.insertDB(deptName, totalStudent)) {
                        out.println("<p style='color:green;'>Inserted successfully.</p>");
                    } else {
                        out.println("<p style='color:red;'>Insertion failed.</p>");
                    }
                    break;

                case "View":
                    List<String> departments = service.viewDB();
                    if (departments.isEmpty()) {
                        out.println("<p>No departments found.</p>");
                    } else {
                        out.println("<table>");
                        out.println("<tr><th>Department Name</th><th>Total Students</th></tr>");
                        
                        for (String dept : departments) {
                            String[] parts = dept.split(", Total Students: ");
                            String dName = parts[0].replace("Department: ", "");
                            String dCount = parts.length > 1 ? parts[1] : "0";
                            
                            out.println("<tr><td>" + dName + "</td><td>" + dCount + "</td></tr>");
                        }
                        out.println("</table>");
                    }
                    break;

                case "Update":
                    if (service.updateDB(deptName, totalStudent)) {
                        out.println("<p style='color:green;'>Updated successfully.</p>");
                    } else {
                        out.println("<p style='color:red;'>Update failed.</p>");
                    }
                    break;

                case "Delete":
                    if (service.deleteDB(deptName)) {
                        out.println("<p style='color:green;'>Deleted successfully.</p>");
                    } else {
                        out.println("<p style='color:red;'>Delete failed.</p>");
                    }
                    break;

                default:
                    out.println("<p>Unknown action.</p>");
            }
        }
        
        out.println("<br><a href='index.jsp'>Go Back</a>");
        out.println("</body></html>");
    }
}
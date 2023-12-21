import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scala.collection.mutable.HashMap;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h1> Привет " + ctx.getAttribute("ContextParam") + "</h1>");

        HashMap<String, String> persons = ApiMySql.readUsers();
        System.out.println("test" + persons);
        // for (String i : persons.keySet()) {
        // System.out.println(i);
        // }
    }
}

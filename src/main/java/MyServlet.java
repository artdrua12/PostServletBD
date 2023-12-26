import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
    // @Override
    // protected void doGet(HttpServletRequest request, HttpServletResponse
    // response)
    // throws ServletException, IOException {
    // ServletContext ctx = getServletContext();
    // response.setContentType("text/html;charset=UTF-8");
    // response.getWriter().println("<h1> Привет " +
    // ctx.getAttribute("ContextParam") + "</h1>");

    // scala.collection.mutable.HashMap<String, String> persons =
    // ApiMySql.readUsers();
    // System.out.println("test" + persons);
    // // for (String i : persons.keySet()) {
    // // System.out.println(i);
    // // }
    // }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // перенаправляем на страничку
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/corect.jsp");
        // передаем параметры
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String personName = request.getParameter("personName");
        String personPassword = request.getParameter("personPassword");
        try {
            if (ApiMySql.isExistsPerson(personName, personPassword)) {
                // устанавливаем атрибуты
                request.setAttribute("name", personName);
                // вызываем метод GET
                doGet(request, response);
            } else {
                // перенаправляем на страничку
                response.sendRedirect("views/wrong.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

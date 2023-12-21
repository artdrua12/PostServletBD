import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    public MyServletContextListener() {
    }

    private ServletContext context;

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("_________________BD Initialized_____________________");
        try {
            if (!ApiMySql.isTableExists("users")) {
                ApiMySql.createDB(); // создаем базу данных если ее нет
                ApiMySql.insertUsers("Peter", "1234"); // вставляем данные
                ApiMySql.insertUsers("Alisa", "4321");
                ApiMySql.insertUsers("Otto", "2345");
                ApiMySql.insertUsers("Eva", "5432");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("_________________BD EROR_____________________");
        }

        context = servletContextEvent.getServletContext();
        // set attribute in context
        context.setAttribute("ContextParam", " Peter");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("BD Destroyed");
        ApiMySql.closeConnectDB();
    }
}

import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    public MyServletContextListener() {
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            if (!ApiMySql.isExistsPerson("Peter", ApiCrypt.createCrypt("1234"))) { // если нет такой записи
                ApiMySql.createDB(); // создаем базу данных если ее нет
                ApiMySql.insertUsers("Peter", ApiCrypt.createCrypt("1234")); // вставляем данные и шифруем их
                ApiMySql.insertUsers("Alisa", ApiCrypt.createCrypt("4321"));
                ApiMySql.insertUsers("Otto", ApiCrypt.createCrypt("2345"));
                ApiMySql.insertUsers("Eva", ApiCrypt.createCrypt("5432"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("_________________BD Initialized_____________________");
        // ServletContext context = servletContextEvent.getServletContext();
        // context.setAttribute("ContextParam", map);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("BD Destroyed");
        ApiMySql.closeConnectDB();
    }
}

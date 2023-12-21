import java.sql.SQLException;

import scala.collection.mutable.HashMap;

public class t {
    public static void main(String[] args) throws SQLException {
        if (!ApiMySql.isTableExists("users")) {
            ApiMySql.createDB(); // создаем базу данных если ее нет
            ApiMySql.insertUsers("Peter", "1234"); // вставляем данные
            ApiMySql.insertUsers("Alisa", "4321");
            ApiMySql.insertUsers("Otto", "2345");
            ApiMySql.insertUsers("Eva", "5432");
        }

        HashMap<String, String> test = ApiMySql.readUsers();
        System.out.println("test -> " + test);
    }
}

import java.sql.SQLException;

public class t {
    public static void main(String[] args) throws SQLException {
        if (!ApiMySql.isExistsPerson("Peter", "1234")) {
            ApiMySql.createDB(); // создаем базу данных если ее нет
            ApiMySql.insertUsers("Peter", "1234"); // вставляем данные
            ApiMySql.insertUsers("Alisa", "4321");
            ApiMySql.insertUsers("Otto", "2345");
            ApiMySql.insertUsers("Eva", "5432");
        }

        // System.out.println("test -> " + ApiMySql.isExistsPerson("Peter", "1234"));
        System.out.println(ApiMySql.readUsers());
    }
}

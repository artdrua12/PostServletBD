<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <!-- https://www.wideskills.com/servlets/servlet-listeners/p/0/3 -->
    <h2>
      Создать таблицу users в БД, в которой будут поля login и password. Сделать
      так чтобы при авторизации проверялось, что введённые данные есть в таблице
      и если есть то перенапровлять на страницу где будет выведена надпись
      "Привет " + login. Иначе на страницу с ошибкой авторизации. Пароли хранить в базе зашифровать с помощью MD5
    </h2>
    <ul>
      <li>Peter => 1234</li>
      <li>Alisa => 4321</li>
      <li>Otto => 2345</li>
      <li>Eva => 5432</li>
    </ul>
    <form method="post" action="/app/check">
      <label>personName: <input type="text" name="personName" /> </label>
      <label
        >personPassword: <input type="text" name="personPassword" />
      </label>
      <button type="submit">Check</button>
    </form>
  </body>
</html>

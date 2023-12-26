<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Right user</title>
  </head>

  <body>
    <h2>Hello <%= request.getAttribute("name") %></h2>
    <div>
      <button onclick="location.href='/app'">Back to app</button>
    </div>
  </body>
</html>
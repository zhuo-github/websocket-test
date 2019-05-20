<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<meta charset="UTF-8" />
<head>
    <title>登陆页面</title>
</head>
<body>
<div th:if="${param.error}">
    无效的账号和密码
</div>
<div th:if="${param.logout}">
    你已注销
</div>
<form th:action="@{/login}" method="post">
    <div><label> 账号 : <input type="text" name="username"/> </label></div>
    <div><label> 密码: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="登陆"/></div>
</form>
</body>
</html>
<%@page import="com.mercateo.HelloWorld"%>
<html>
<body>
<h2>Hello World!</h2>
<%
String s = HelloWorld.helloWorldTomcatJava8();
out.println(s);
%>
</body>
</html>

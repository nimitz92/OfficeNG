<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="officeng.db.ConnectionManager"%>
<%
    Connection conn = null;
    try {
        conn = ConnectionManager.getConnection();
    }
    catch(Exception e){
        out.print(e.getMessage());
    }
    finally {
        if(conn != null) conn.close();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OfficeNG Document Management &amp; Collaboration System</title>
    </head>
    <body>
        <h1>Welcome to OfficeNG</h1>
        <h2>Document Management &amp; Collaboration System</h2>
    </body>
</html>

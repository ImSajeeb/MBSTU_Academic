<%@ page import="java.util.*, com.student.model.Student" %>

<table border="1">
<tr>
    <th>ID</th><th>Name</th><th>Email</th><th>Actions</th>
</tr>

<%
List<Student> list =
    (List<Student>)request.getAttribute("studentList");

for(Student s : list){
%>
<tr>
<form action="StudentServlet" method="post">
    <td><%= s.getId() %></td>
    <td><input type="text" name="name" value="<%= s.getName() %>"></td>
    <td><input type="text" name="email" value="<%= s.getEmail() %>"></td>
    <td>
        <input type="hidden" name="id" value="<%= s.getId() %>">
        <input type="submit" name="action" value="Update">
        <input type="submit" name="action" value="Delete">
    </td>
</form>
</tr>
<% } %>
</table>

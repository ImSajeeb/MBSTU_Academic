<!DOCTYPE html>
<html>
<head>
    <title>Department Management</title>
</head>
<body>
    <h2>Department Details</h2>
    
    <form method="get" action="TestServlet">
        
        <label for="deptName">Department Name:</label>
        <input type="text" id="deptName" name="deptName" required>
        <br><br>
        
        <label for="totalStudent">Total Students:</label>
        <input type="number" id="totalStudent" name="totalStudent">
        <br><br>

        <input type="submit" name="action" value="Insert">
        
        <input type="submit" name="action" value="View" formnovalidate>
        
        <input type="submit" name="action" value="Update">
        
        <input type="submit" name="action" value="Delete" formnovalidate>
    </form>
</body>
</html>
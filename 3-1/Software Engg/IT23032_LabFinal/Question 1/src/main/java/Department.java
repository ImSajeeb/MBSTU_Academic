public class Department {
    // specific fields matching the 'ds' table
    private String deptName;
    private int totalStudent;

    // Default Constructor
    public Department() {
    }

    // Parameterized Constructor (optional, but useful)
    public Department(String deptName, int totalStudent) {
        this.deptName = deptName;
        this.totalStudent = totalStudent;
    }

    // Getters and Setters
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    // toString method for easy printing (optional)
    @Override
    public String toString() {
        return "Department [Name=" + deptName + ", Students=" + totalStudent + "]";
    }
}
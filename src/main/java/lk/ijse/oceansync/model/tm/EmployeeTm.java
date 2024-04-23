package lk.ijse.oceansync.model.tm;


public class EmployeeTm {

    private String id;
    private String employeeId;
    private String name;
    private String activity;
    private String salary;
    private String date;
    private String userId;

    public EmployeeTm(String id, String employeeId, String name, String activity, String salary, String date, String userId) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.activity = activity;
        this.salary = salary;
        this.date = date;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

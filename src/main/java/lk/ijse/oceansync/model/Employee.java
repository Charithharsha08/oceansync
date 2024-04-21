package lk.ijse.oceansync.model;
import lombok.*;



public class Employee {

    private String id;
    private String employeeId;
    private String name;
    private String activity;
    private String month;
    private String salary;
    private String  date;
    private String userId;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public Employee(String id, String employeeId, String name, String activity, String month, String salary, String date, String userId) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.activity = activity;
        this.month = month;
        this.salary = salary;
        this.date = date;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", activity='" + activity + '\'' +
                ", month='" + month + '\'' +
                ", salary='" + salary + '\'' +
                ", date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}

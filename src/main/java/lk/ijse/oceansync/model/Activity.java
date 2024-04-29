package lk.ijse.oceansync.model;


public class Activity {

    private String activityId;
    private String name;
    private String type;
    private String location;
    private double cost;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Activity(String activityId, String name, String type, String location, double cost) {
        this.activityId = activityId;
        this.name = name;
        this.type = type;
        this.location = location;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId='" + activityId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", cost=" + cost +
                '}';
    }
}

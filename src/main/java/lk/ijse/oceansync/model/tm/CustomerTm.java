package lk.ijse.oceansync.model.tm;


public class CustomerTm {

    private String customerId;
    private String name;
    private String address;
    private String tel;

    public CustomerTm(String customerId, String name, String address, String tel) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public String getId() {
        return customerId;
    }

    public void setId(String id) {
        this.customerId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return tel;
    }

    public void setContact(String contact) {
        this.tel = contact;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}

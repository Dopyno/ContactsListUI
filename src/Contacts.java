import java.io.Serializable;

public class Contacts implements Serializable {
    private String name;
    private String phoneNo;

    public Contacts(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return name + " - " + phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}

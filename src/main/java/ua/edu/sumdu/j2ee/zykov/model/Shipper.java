package ua.edu.sumdu.j2ee.zykov.model;

public class Shipper {
    private User user;
    private String companyName;

    public Shipper() {
    }

    public Shipper(User user, String companyName) {
        this.user = user;
        this.companyName = companyName;
    }

    public Shipper(String companyName) {
        this.companyName = companyName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

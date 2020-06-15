package ua.edu.sumdu.j2ee.zykov.model;

public class Shipper {
    private int id;
    private String companyName;

    public Shipper() {
    }

    public Shipper(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

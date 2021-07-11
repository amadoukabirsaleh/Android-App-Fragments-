package hr.tvz.android.salehfragment;

public class Product {

    private String make;
    private String model;
    private String OwnerName;
    private String OwnerTel;


    public Product(String make, String model, String ownerName, String ownerTel) {
        this.make = make;
        this.model = model;
        this.OwnerName = ownerName;
        this.OwnerTel = ownerTel;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getOwnerTel() {
        return OwnerTel;
    }

    public void setOwnerTel(String ownerTel) {
        OwnerTel = ownerTel;
    }
}

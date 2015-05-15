package mobile.listaacessivel.fafica.listaacessvel.entidades;

/**
 * Created by ivan on 15/05/15.
 */
public class Cep {

    private int status;
    private String code;
    private String state;
    private String city;
    private String district;
    private String address;

    public Cep(){

    }

    public Cep(int status, String code, String state, String city, String district, String address) {
        this.status = status;
        this.code = code;
        this.state = state;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

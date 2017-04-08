package fundroid.ixicode.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 */

public class Hotel implements Serializable {

    private String address;// D-193, Saket, Near PVR Saket, saket new delhi, ,
    private ArrayList<String> categoryNames;// [Accommodation
    private String cityName;// New Delhi,
    private String countryName;// India,
    private String description;// Tree of Life Bed &amp; Breakfast is 1 km from Select City Walk Mall and Garden of 5 Senses. Free Wi-Fi access is available.,
    private String cityId;// 503b2a70e4b032e338f0ee67,
    private long xid;// 544252,
    private String keyImageUrl;// https;////images.ixigo.com/node_image/t_thumb,f_auto/id/qnaciezhsswjmfn7meia.jpg,
    private double latitude;// 28.52458,
    private double longitude;// 77.2044,
    private String minimumPrice;// 3799,
    private String name;// Tree Of Life Bed & Breakfast,
    private String stateName;// Delhi,
    private String shortDescription;// description not defined,
    private String id;// 5331cf78e4b087361af0a427

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(ArrayList<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public long getXid() {
        return xid;
    }

    public void setXid(long xid) {
        this.xid = xid;
    }

    public String getKeyImageUrl() {
        return keyImageUrl;
    }

    public void setKeyImageUrl(String keyImageUrl) {
        this.keyImageUrl = keyImageUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(String minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

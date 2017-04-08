package fundroid.ixicode.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 */

public class Place implements Serializable{

    private String image;// "https;////images.ixigo.com/node_image/t_thumb,f_auto/id/503b2a60e4b032e338f0c66f.jpg",
    private String name;// "Tirupati",
    private String countryName;// "india",
    private String url;// "/search/result/flight/DEL/TIR/30042017//1/0/0/e",
    private String data;// "&#8377; 4771",
    private String text;// "flights starting from ",
    private String type;// "flight",
    private String cityName;// "Tirupati",
    private String stateName;// "andhra pradesh",
    private long price;// 4771,
    private String currency;// "INR",
    private String cityId;// "503b2a60e4b032e338f0c66f",
    private ArrayList<String> destinationCategories;

    private double lat;
    private double lng;

    private boolean dummy;

    public Place() {}

    public Place(boolean dummy) {
        this.dummy = dummy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public ArrayList<String> getDestinationCategories() {
        return destinationCategories;
    }

    public void setDestinationCategories(ArrayList<String> destinationCategories) {
        this.destinationCategories = destinationCategories;
    }

    public boolean isDummy() {
        return dummy;
    }

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
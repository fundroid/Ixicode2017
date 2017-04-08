package fundroid.ixicode.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 */

public class Point implements Serializable {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("categoryNames")
    @Expose
    private List<String> categoryNames = null;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cityId")
    @Expose
    private String cityId;
    @SerializedName("xid")
    @Expose
    private Long xid;
    @SerializedName("keyImageUrl")
    @Expose
    private String keyImageUrl;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("minimumPrice")
    @Expose
    private Long minimumPrice;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("id")
    @Expose
    private String id;

    private boolean dummy;
    private boolean loading;

    public Point() {}

    public Point(boolean dummy) {
        this.dummy = dummy;
    }

    public boolean isDummy() {
        return dummy;
    }

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }

    private final static long serialVersionUID = -7363306854986740157L;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
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

    public Long getXid() {
        return xid;
    }

    public void setXid(Long xid) {
        this.xid = xid;
    }

    public String getKeyImageUrl() {
        return keyImageUrl;
    }

    public void setKeyImageUrl(String keyImageUrl) {
        this.keyImageUrl = keyImageUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Long minimumPrice) {
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

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
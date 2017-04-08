package fundroid.ixicode.model;

import java.io.Serializable;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 */

public class City implements Serializable{
    private String text;// "delhi",
    private String url;// "/travel-guide/new-delhi",
    private String ct;// "explore-destination",
    private String address;// "",
    private String _id;// "503b2a70e4b032e338f0ee67",
    private String cn;// null,
    private boolean en;// true,
    private String rt;// "",
    private String st;// "delhi",
    private String co;// "india",
    private long _oid;// 1140454,
    private String eid;// "503b2a70e4b032e338f0ee67",
    private String cid;// null,
    private boolean useNLP;// false,
    private double lat;// 28.61282,
    private double lon;// 77.23114,
    private long xid;// 1065223

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public boolean isEn() {
        return en;
    }

    public void setEn(boolean en) {
        this.en = en;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public long get_oid() {
        return _oid;
    }

    public void set_oid(long _oid) {
        this._oid = _oid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public boolean isUseNLP() {
        return useNLP;
    }

    public void setUseNLP(boolean useNLP) {
        this.useNLP = useNLP;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getXid() {
        return xid;
    }

    public void setXid(long xid) {
        this.xid = xid;
    }
}

package br.com.savingfoodscanner.model;

import java.io.Serializable;

/**
 * Created by brunolemgruber on 31/08/17.
 */

public class Store implements Serializable {

    private String address;

    private String close;

    private String img;

    private Double lat;

    private Double lng;

    private String name;

    private String open;

    private String phone;

    private String network;

    public Store(){}

    public Store(String address, String close, String img, Double lat,
                 Double lng, String name, String open, String phone,String network) {
        this.address = address;
        this.close = close;
        this.img = img;
        this.lat = lat;
        this.network = network;
        this.lng = lng;
        this.name = name;
        this.open = open;
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }


}

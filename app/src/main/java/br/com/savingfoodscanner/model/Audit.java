package br.com.savingfoodscanner.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static br.com.savingfoodscanner.R.id.img;

/**
 * Created by brunolemgruber on 01/09/17.
 */

public class Audit implements Serializable {

    private String uid;

    private String bar_code;

    private String name;

    private String register_date;

    private Double price;

    private Double old_price;

    private String network;

    private String store;

    public Audit() {
    }

    public Audit(String uid, String bar_code, String name, String register_date,
                 String network, String store, Double old_price, Double price) {
        this.uid = uid;
        this.bar_code = bar_code;
        this.name = name;
        this.register_date = register_date;
        this.network = network;
        this.store = store;
        this.old_price = old_price;
        this.price = price;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("bar_code", bar_code);
        result.put("name", name);
        result.put("register_date", register_date);
        result.put("network", network);
        result.put("store", store);
        result.put("old_price", old_price);
        result.put("price", price);

        return result;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOld_price() {
        return old_price;
    }

    public void setOld_price(Double old_price) {
        this.old_price = old_price;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}

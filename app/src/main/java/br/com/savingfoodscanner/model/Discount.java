package br.com.savingfoodscanner.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brunolemgruber on 18/07/16.
 */

public class Discount implements Serializable {

    private String uid;

    private String name;

    private String bar_code;

    private String description;

    private String due_date;

    private String img;

    private Double price;

    private Double old_price;

    private int views;

    private int quantity;

    private int percent;

    private List<String> mStores;

    public Discount() {
    }

    public Discount(String uid, String name, String bar_code, String img,
                    String description,Double price,Double old_price,String due_date, int views,
                    int quantity,int percent) {
        this.uid = uid;
        this.name = name;
        this.bar_code = bar_code;
        this.description = description;
        this.img = img;
        this.price = price;
        this.old_price = old_price;
        this.views = views;
        this.quantity = quantity;
        this.percent = percent;
        this.due_date = due_date;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("bar_code", bar_code);
        result.put("description", description);
        result.put("img", img);
        result.put("price", price);
        result.put("old_price", old_price);
        result.put("views", views);
        result.put("quantity", quantity);
        result.put("percent", percent);
        result.put("due_date", due_date);

        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Double getOld_price() {
        return old_price;
    }

    public void setOld_price(Double old_price) {
        this.old_price = old_price;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public List<String> getmStores() {
        return mStores;
    }

    public void setmStores(List<String> mStores) {
        this.mStores = mStores;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}

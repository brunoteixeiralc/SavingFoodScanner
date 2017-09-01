package br.com.savingfoodscanner.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brunolemgruber on 31/08/17.
 */

public class Product implements Serializable {

    private String bar_code;

    private String description;

    private String img;

    private String name;

    private String register_date;

    public Product() {
    }

    public Product(String bar_code, String description, String img, String name, String register_date) {
        this.bar_code = bar_code;
        this.description = description;
        this.img = img;
        this.name = name;
        this.register_date = register_date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("bar_code", bar_code);
        result.put("description", description);
        result.put("img", img);
        result.put("name", name);
        result.put("register_date", register_date);

        return result;
    }


    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
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

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }
}

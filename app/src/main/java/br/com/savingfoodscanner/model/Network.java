package br.com.savingfoodscanner.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by brunolemgruber on 31/08/17.
 */

public class Network implements Serializable{

    private String name;

    private List<Store> stores;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}

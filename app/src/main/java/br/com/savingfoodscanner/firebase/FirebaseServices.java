package br.com.savingfoodscanner.firebase;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import br.com.savingfoodscanner.model.Discount;
import br.com.savingfoodscanner.model.Product;

/**
 * Created by brunolemgruber on 31/08/17.
 */

public final class FirebaseServices {

    public static void saveProduct(DatabaseReference databaseReference, Product product){
        String key = product.getBar_code();
        Map<String, Object> productValues = product.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/products/" + key, productValues);
        databaseReference.updateChildren(childUpdates);
    }

    public static void saveDiscount(DatabaseReference databaseReference, Discount discount, String key){
        Map<String, Object> discountValues = discount.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/discounts/" + key, discountValues);
        databaseReference.updateChildren(childUpdates);
    }
}

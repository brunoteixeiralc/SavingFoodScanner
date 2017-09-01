package br.com.savingfoodscanner.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Date;

import br.com.savingfoodscanner.R;
import br.com.savingfoodscanner.firebase.FirebaseServices;
import br.com.savingfoodscanner.model.Product;
import br.com.savingfoodscanner.utils.DateTime;
import br.com.savingfoodscanner.utils.Dialogs;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private Button btnBarCode;
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBarCode = (Button) findViewById(R.id.btn_enter);
        btnBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(MainActivity.this).setCaptureActivity(BarCodeActivity.class).initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
           if(result.getContents() == null)
              Log.d("SavingFoodScanner", "Cancelled scan");
           else {
               Dialogs.openDialog(this,"Verificando o produto.");
               String barCode = result.getContents();
               findProduct(barCode);
           }

         }else
            super.onActivityResult(requestCode, resultCode, data);
        }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void findProduct(String barCode){
        findProductByBarCode(barCode);
    }

    private void findProductByBarCode(final String barCode){

      databaseReference.child("products").child(barCode).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product = dataSnapshot.getValue(Product.class);

                if(product == null){
                    product = new Product(barCode, "Descrição do produto", "https://firebasestorage.googleapis.com/v0/b/savingfood-debug-2f54f.appspot.com/o/product%2Fnissin_lamen_picanha%2Fimagem.png?alt=media&token=629aada4-7be2-4161-9345-fa55030e4f2e",
                            "Produto", DateTime.formatToString("dd/MM/yy HH:mm:ss",new Date()));
                    FirebaseServices.saveProduct(databaseReference,product);
                }

                Intent i = new Intent(MainActivity.this,ProductDetailActivity.class);
                i.putExtra("product",product);
                startActivity(i);
                Dialogs.closeDialog(MainActivity.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}


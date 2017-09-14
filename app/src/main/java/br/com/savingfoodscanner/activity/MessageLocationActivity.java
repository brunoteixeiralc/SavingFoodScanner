package br.com.savingfoodscanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import br.com.savingfoodscanner.R;
import br.com.savingfoodscanner.firebase.FirebaseServices;
import br.com.savingfoodscanner.model.Audit;
import br.com.savingfoodscanner.model.Discount;
import br.com.savingfoodscanner.utils.DateTime;

/**
 * This Activity is exactly the same as CaptureActivity, but has a different orientation
 * setting in AndroidManifest.xml.
 */
public class MessageLocationActivity extends Activity {

    private Button btnChangeLocation;
    private Button btnSave;
    private Discount discount;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private TextView message;
    private String network,store,address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_message);

        discount = (Discount) getIntent().getSerializableExtra("discount");
        network = getIntent().getStringExtra("network");
        store = getIntent().getStringExtra("store");
        address = getIntent().getStringExtra("address");

        message = (TextView) findViewById(R.id.message);
        if(network != null && store != null){
            message.setText("Você está no supermercado da rede  " + network
            + " na loja " + store + " do endereço " + address + " , correto ?");
        }

        btnChangeLocation = (Button) findViewById(R.id.btn_change_location);
        btnChangeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MessageLocationActivity.this,ChooseLocationActivity.class);
                i.putExtra("discount",discount);
                startActivity(i);
                finish();

            }
        });

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discount.setNetwork(network);
                discount.setStore(store);
                discount.setSearch(network+"_"+store);
                FirebaseServices.saveDiscount(mDatabase,discount,discount.getUid());

                Audit audit = new Audit(mDatabase.push().getKey(),discount.getBar_code(),discount.getName(), DateTime.formatToString("dd/MM/yyyy HH:mm:ss",new Date()),network,
                        store,discount.getOld_price(),discount.getPrice());
                FirebaseServices.saveAudit(mDatabase,audit,audit.getUid());

                startActivity(new Intent(MessageLocationActivity.this,SuccessActivity.class));
            }
        });

    }
}

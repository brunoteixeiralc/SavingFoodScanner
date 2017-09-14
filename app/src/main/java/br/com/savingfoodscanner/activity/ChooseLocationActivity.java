package br.com.savingfoodscanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.savingfoodscanner.R;
import br.com.savingfoodscanner.firebase.FirebaseServices;
import br.com.savingfoodscanner.model.Audit;
import br.com.savingfoodscanner.model.Discount;
import br.com.savingfoodscanner.model.Network;
import br.com.savingfoodscanner.model.Store;
import br.com.savingfoodscanner.utils.DateTime;
import br.com.savingfoodscanner.utils.Dialogs;

/**
 * Created by brunolemgruber on 01/09/17.
 */

public class ChooseLocationActivity extends Activity {

    private Button btnSaveDiscount;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private Discount discount;
    private NiceSpinner networkSpinner,storeSpinner;
    private List<Network> networks = new ArrayList<>();
    private String networkSelected,storeSelected,addressSelected;
    private List<String> netWorkDataSet,storeDataSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_choose_location);

        Dialogs.openDialog(this,"Carregando redes.");
        netWorkDataSet = new ArrayList<>();
        netWorkDataSet.add("Selecione uma rede");
        storeDataSet = new ArrayList<>();
        storeDataSet.add("Selecione uma loja");
        getNetwork();

        discount = (Discount) getIntent().getSerializableExtra("discount");

        btnSaveDiscount = (Button) findViewById(R.id.btn_save);
        btnSaveDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discount.setNetwork(networkSelected);
                discount.setStore(storeSelected);
                FirebaseServices.saveDiscount(mDatabase,discount,discount.getUid());

                Audit audit = new Audit(mDatabase.push().getKey(),discount.getBar_code(),discount.getName(), DateTime.formatToString("dd/MM/yyyy HH:mm:ss",new Date()),networkSelected,
                        storeSelected,discount.getOld_price(),discount.getPrice());
                FirebaseServices.saveAudit(mDatabase,audit,audit.getUid());

                startActivity(new Intent(ChooseLocationActivity.this,SuccessActivity.class));

                SharedPreferences.Editor editor = getSharedPreferences("sfScanner", MODE_PRIVATE).edit();
                editor.putString("network", networkSelected);
                editor.putString("store", storeSelected);
                editor.putString("address", addressSelected);
                editor.apply();
            }
        });

        networkSpinner= (NiceSpinner) findViewById(R.id.networkSpinner);
        networkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                storeDataSet.clear();
                if(i != 0){
                    networkSelected = netWorkDataSet.get(i);
                    for (Store s : networks.get(i - 1).getStores()){
                        storeDataSet.add(s.getName() + "-" +  s.getAddress());
                    }
                }else{
                    storeDataSet.add("Selecione uma loja");
                }
                storeSpinner.attachDataSource(storeDataSet);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        storeSpinner = (NiceSpinner) findViewById(R.id.storeSpinner);
        storeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                storeSelected = storeDataSet.get(i).substring(0,storeDataSet.get(i).indexOf("-"));
                addressSelected = storeDataSet.get(i).substring(storeDataSet.get(i).indexOf("-"));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getNetwork(){
        mDatabase.child("network").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    for (DataSnapshot nt: dataSnapshot.getChildren()) {
                        Network network = new Network();
                        network.setStores(new ArrayList<Store>());
                        network.setName(nt.getKey());
                        netWorkDataSet.add(nt.getKey());

                        for (DataSnapshot st: nt.getChildren()){
                            Store store = st.getValue(Store.class);
                            network.getStores().add(store);

                        }
                        networks.add(network);
                    }
                    networkSpinner.attachDataSource(netWorkDataSet);
                    storeSpinner.attachDataSource(storeDataSet);
                    Dialogs.closeDialog(ChooseLocationActivity.this);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

package br.com.savingfoodscanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.savingfoodscanner.R;
import br.com.savingfoodscanner.firebase.FirebaseServices;
import br.com.savingfoodscanner.model.Audit;
import br.com.savingfoodscanner.model.Discount;
import br.com.savingfoodscanner.model.Network;
import br.com.savingfoodscanner.model.Store;
import br.com.savingfoodscanner.utils.DateTime;
import br.com.savingfoodscanner.utils.Dialogs;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by brunolemgruber on 01/09/17.
 */

public class ChooseLocationActivity extends Activity {

    @BindView(R.id.btn_save)
    Button btnSaveDiscount;
    @BindView(R.id.networkSpinner)
    NiceSpinner networkSpinner;
    @BindView(R.id.storeSpinner)
    NiceSpinner storeSpinner;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private Discount discount;
    private List<Network> networks = new ArrayList<>();
    private String networkSelected,storeSelected;
    private List<String> netWorkDataSet = new ArrayList<>();
    private List<String> storeDataSet = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_choose_location);
        ButterKnife.bind(this);

        Dialogs.openDialog(this,"Carregando redes.");
        netWorkDataSet.add("Selecione uma rede");
        storeDataSet.add("Selecione uma loja");
        getNetwork();

        discount = (Discount) getIntent().getSerializableExtra("discount");

//        btnSaveDiscount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseServices.saveDiscount(mDatabase,discount,discount.getUid());
//
//                Audit audit = new Audit(mDatabase.push().getKey(),discount.getBar_code(),discount.getName(), DateTime.formatToString("dd/MM/yyyy HH:mm:ss",new Date()),networkSelected,
//                        storeSelected,discount.getOld_price(),discount.getPrice());
//                FirebaseServices.saveAudit(mDatabase,audit,audit.getUid());
//
//                startActivity(new Intent(ChooseLocationActivity.this,SuccessActivity.class));
//            }
//        });

//        networkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                storeDataSet.clear();
//                if(i != 0){
//                    networkSelected = netWorkDataSet.get(i);
//                    for (Store s : networks.get(i - 1).getStores()){
//                        storeDataSet.add(s.getName() + " " +  s.getAddress());
//                    }
//                }else{
//                    storeDataSet.add("Selecione uma loja");
//                }
//                storeSpinner.attachDataSource(storeDataSet);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        storeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                storeSelected = storeDataSet.get(i);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    @OnItemSelected(R.id.networkSpinner)
    public void networkSelected(int position){

        storeDataSet.clear();
        if(position != 0){
            networkSelected = netWorkDataSet.get(position);
            for (Store s : networks.get(position - 1).getStores()){
                storeDataSet.add(s.getName() + " " +  s.getAddress());
            }
        }else{
            storeDataSet.add("Selecione uma loja");
        }
        storeSpinner.attachDataSource(storeDataSet);

    }

    @OnItemSelected(R.id.storeSpinner)
    public void storeSelected(int position){
        storeSelected = storeDataSet.get(position);
    }

    @OnClick(R.id.btn_save)
    public void saveDiscount(View view){

        FirebaseServices.saveDiscount(mDatabase,discount,discount.getUid());

        Audit audit = new Audit(mDatabase.push().getKey(),discount.getBar_code(),discount.getName(), DateTime.formatToString("dd/MM/yyyy HH:mm:ss",new Date()),networkSelected,
                storeSelected,discount.getOld_price(),discount.getPrice());
        FirebaseServices.saveAudit(mDatabase,audit,audit.getUid());

        startActivity(new Intent(ChooseLocationActivity.this,SuccessActivity.class));
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

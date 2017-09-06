package br.com.savingfoodscanner.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.savingfoodscanner.R;
import br.com.savingfoodscanner.firebase.FirebaseServices;
import br.com.savingfoodscanner.model.Discount;
import br.com.savingfoodscanner.model.Product;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by brunolemgruber on 21/08/17.
 */

public class ProductDetailActivity extends AppCompatActivity {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private Discount newDiscount;
    private Toolbar toolbar;
    private ImageView img;
    private TextView name,description,barcode,quantity;
    private EditText due_date;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        setDiscount();

        toolbar =(Toolbar)findViewById(R.id.toolbar);
        name =(TextView) findViewById(R.id.name);
        quantity =(TextView) findViewById(R.id.product_quantity);
        name.setText(newDiscount.getName());
        description =(TextView) findViewById(R.id.description);
        description.setText(newDiscount.getDescription());
        barcode =(TextView) findViewById(R.id.barcode);
        barcode.setText(newDiscount.getBar_code());
        due_date = (EditText) findViewById(R.id.due_date);
        due_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    startActivityForResult(new Intent(ProductDetailActivity.this,DueDateCalendarActivity.class),1000);
                }
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newDiscount.setDue_date(due_date.getText().toString());
                newDiscount.setQuantity(Integer.parseInt(quantity.getText().toString()));
                newDiscount.setUid(mDatabase.getRef().push().getKey());

                Intent i = new Intent(ProductDetailActivity.this,ChooseLocationActivity.class);
                i.putExtra("discount",newDiscount);
                startActivity(i);
            }
        });

        toolbar.setTitle(newDiscount.getName());
        img = (ImageView)findViewById(R.id.img);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(ProductDetailActivity.this).load(newDiscount.getImg()).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable drawable, Object o, Target<Drawable> target, DataSource dataSource, boolean b) {
                return false;
            }
        }).into(img);
    }

    private void setDiscount(){

        Product p = new Product();
        p = (Product) getIntent().getSerializableExtra("product");

        newDiscount = new Discount();
        newDiscount.setName(p.getName());
        newDiscount.setDescription(p.getDescription());
        newDiscount.setImg(p.getImg());
        newDiscount.setBar_code(p.getBar_code());
        newDiscount.setPercent(70);
        newDiscount.setOld_price((double) 10);
        newDiscount.setPrice((double) 3);
        newDiscount.setViews(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1000){
            if (resultCode == RESULT_OK) {
                due_date.setText(data.getStringExtra("select_date"));
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}

package br.com.savingfoodscanner.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import br.com.savingfoodscanner.R;

/**
 * Created by brunolemgruber on 01/09/17.
 */

public class ChooseLocationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_choose_location);

        NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.test_nice_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
        niceSpinner.attachDataSource(dataset);

        NiceSpinner niceSpinner2 = (NiceSpinner) findViewById(R.id.test_nice_spinner2);
        List<String> dataset2 = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
        niceSpinner2.attachDataSource(dataset2);
    }
}

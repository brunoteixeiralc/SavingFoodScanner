package br.com.savingfoodscanner.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import br.com.savingfoodscanner.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by brunolemgruber on 07/01/16.
 */
public class BarCodeActivity extends Activity implements
        CompoundBarcodeView.TorchListener  {

    private CaptureManager capture;

    @BindView(R.id.zxing_barcode_scanner)
    CompoundBarcodeView barcodeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_code);
        ButterKnife.bind(this);

        barcodeScannerView.setTorchListener(this);
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public void onTorchOn() {

    }

    @Override
    public void onTorchOff() {

    }
}

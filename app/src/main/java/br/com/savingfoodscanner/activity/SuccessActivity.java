package br.com.savingfoodscanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import br.com.savingfoodscanner.R;

/**
 * This Activity is exactly the same as CaptureActivity, but has a different orientation
 * setting in AndroidManifest.xml.
 */
public class SuccessActivity extends Activity {

    private LottieAnimationView animationView;
    private Button btnBarCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_register);

        btnBarCode = (Button) findViewById(R.id.btn_enter);
        btnBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SuccessActivity.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        animationView = (LottieAnimationView)findViewById(R.id.animation_view);
        animationView.playAnimation();
    }
}

package br.com.savingfoodscanner.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;

import br.com.savingfoodscanner.R;

/**
 * This Activity is exactly the same as CaptureActivity, but has a different orientation
 * setting in AndroidManifest.xml.
 */
public class SuccessActivity extends Activity {

    private LottieAnimationView animationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_register);

        animationView = (LottieAnimationView)findViewById(R.id.animation_view);
        animationView.playAnimation();
    }
}

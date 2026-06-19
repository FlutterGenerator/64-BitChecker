package com.danielpolish.a64bitchecker;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckerActivity extends AppCompatActivity {

    private Button CheckBtn;
    private TextView ReasonText;
    private TextView ResultText;

    private void Check() {
        int i;
        CharSequence stringBuilder;

        if (VERSION.SDK_INT >= 21) {
            String str = "";
            i = 0;

            for (int i2 = 0; i2 < Build.SUPPORTED_ABIS.length; i2++) {
                StringBuilder stringBuilder2;

                i |= Build.SUPPORTED_ABIS[i2].contains("64");

                if (i2 > 0) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str);
                    stringBuilder2.append(", ");
                    str = stringBuilder2.toString();
                }

                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append(Build.SUPPORTED_ABIS[i2]);
                str = stringBuilder2.toString();
            }

            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("(Supported ABIs: ");
            stringBuilder3.append(str);
            stringBuilder3.append(".)");
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = "(Android versions before 5.0 do not have 64-bit support.)";
            i = 0;
        }

        CharSequence charSequence = i != 0
                ? "This device has a 64-bit OS."
                : "This device has a 32-bit OS.";

        this.CheckBtn.setVisibility(View.INVISIBLE);
        this.ResultText.setVisibility(View.VISIBLE);
        this.ResultText.setText(charSequence);
        this.ReasonText.setVisibility(View.VISIBLE);
        this.ReasonText.setText(stringBuilder);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_checker);

        this.CheckBtn = (Button) findViewById(R.id.CheckButton);
        this.ResultText = (TextView) findViewById(R.id.ResultText);
        this.ReasonText = (TextView) findViewById(R.id.ReasonText);

        this.CheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
            }
        });
    }
}
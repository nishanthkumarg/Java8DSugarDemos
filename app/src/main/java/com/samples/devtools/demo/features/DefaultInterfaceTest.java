package com.samples.devtools.demo.features;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.samples.devtools.demo.R;

public class DefaultInterfaceTest extends AppCompatActivity implements InterfaceA,InterfaceB{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_interface_test);

        Button dIButton = (Button) findViewById(R.id.default_interface_action);
        dIButton.setOnClickListener((View v) ->{
            Toast.makeText(this, "Test Completed. Result: " + dialNumber(), Toast.LENGTH_LONG).show();
        });

        Button sIButton = (Button) findViewById(R.id.static_interface_action);
        sIButton.setOnClickListener((View v)-> {
            Toast.makeText(this, "TestCompleted: Result: String null-" + InterfaceA.isNull(""), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void textMe() {

    }

    @Override
    public int dialNumber() {
        return InterfaceA.super.dialNumber();
    }
}

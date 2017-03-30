package com.samples.devtools.demo.features;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.samples.devtools.demo.R;

public class LambdaTest extends AppCompatActivity {

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda_test);
        Button btnStepOver = (Button)findViewById(R.id.btn_step_over);
        Button btnStepIn = (Button)findViewById(R.id.btn_step_in);
        btnStepOver.setOnClickListener((View v) ->
        {
            testFunctionalInterfaceLambda( (p) ->
            {
                Log.d(TAG,p.id+"");
                return p.id;
            });
        }
        );
        btnStepIn.setOnClickListener((View v)->
        {
            testLambda1();
            testLambda2();
        });
    }

    public void testLambda1(){

        Log.d(TAG, "testLambda1: ");
        Log.e(TAG, "test Lambda error: " );
        Log.i(TAG, "test Lambda info: ");
        Log.v(TAG, "verbose");
        Log.w(TAG, "test Lambda: ");
        Log.wtf(TAG, "WTF");
        Log.wtf(TAG, "WTF");
    }

    public void testLambda2(){
        Log.d("TAG", "testLambda2: ");
    }


    public void testFunctionalInterfaceLambda(Person.testInterface ti){

        Person p = new Person("Dev-tools", 31, 1);
        int i = ti.getId(p);
        Log.d("", "testFunctionalInterfaceLambda: " + ti.getId(p));
    }
}

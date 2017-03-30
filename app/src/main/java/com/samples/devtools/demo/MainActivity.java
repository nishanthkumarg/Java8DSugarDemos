package com.samples.devtools.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.samples.devtools.demo.features.LambdaTest;
import com.samples.devtools.demo.features.MethodReferencesTest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<DemoItem> demos = new ArrayList<DemoItem>();
        demos.add(new DemoItem(DemoItem.LAMBDAS));
        demos.add(new DemoItem(DemoItem.METHOD_REFERENCES));
        demos.add(new DemoItem(DemoItem.TYPE_ANNOTATIONS));
        demos.add(new DemoItem(DemoItem.REPEATING_ANNOTATIONS));
        demos.add(new DemoItem(DemoItem.STATIC_INTERFACE_METHODS));
        demos.add(new DemoItem(DemoItem.STREAM_API));
        demos.add(new DemoItem(DemoItem.REQUIRE_NON_NULL));
        demos.add(new DemoItem(DemoItem.TRY_WITH_RESOURCES));
        demos.add(new DemoItem(DemoItem.JAVA_TIME_API));
        demos.add(new DemoItem(DemoItem.INSTANT_RUN));
        adapter = new RecyclerViewAdapter(this,demos);
        recyclerView.setAdapter(adapter);

    }

    public void onItemClick(DemoItem demoItem) {
        switch (demoItem.getTitle()){
            case DemoItem.LAMBDAS:
                startActivity(new Intent(this, LambdaTest.class));
                break;
            case DemoItem.METHOD_REFERENCES:
                startActivity(new Intent(this, MethodReferencesTest.class));
                break;
            case DemoItem.TYPE_ANNOTATIONS:
                break;
            case DemoItem.REPEATING_ANNOTATIONS:
                break;
            case DemoItem.STATIC_INTERFACE_METHODS:
                break;
            case DemoItem.STREAM_API:
                break;
            case DemoItem.REQUIRE_NON_NULL:
                break;
            case DemoItem.TRY_WITH_RESOURCES:
                break;
            case DemoItem.JAVA_TIME_API:
                break;
            case DemoItem.INSTANT_RUN:
                break;
        }
    }
}

package com.samples.devtools.demo.features;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.samples.devtools.demo.R;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RepeatedAnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeated_annotation);

        Manufacturer[] a = Car.class.getAnnotationsByType(Manufacturer.class);
        System.out.println("Number of car manufacturers is "+ a.length );
        System.out.println("\n-------Printing out Car Manufacturers--------");

        Cars cars = Cars.class.getAnnotation(Cars.class);


        for(Manufacturer car: cars.value()){

        }


    }

    @Retention( RetentionPolicy.RUNTIME )
    public @interface Cars {
        Manufacturer[] value() default{};
    }

    @Manufacturer( "Mercedes Benz")
    @Manufacturer( "Toyota")
    @Manufacturer( "BMW")
    @Manufacturer( "Range Rover")
    public interface Car {

    }


    @Repeatable(value = Cars.class )
    public @interface Manufacturer {
        String value();

    };
}

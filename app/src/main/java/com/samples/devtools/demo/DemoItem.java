package com.samples.devtools.demo;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by nishanthkumarg on 3/29/17.
 */

public class DemoItem {

    static final String LAMBDAS = "Lambdas";
    static final String METHOD_REFERENCES = "Method references";
    static final String TYPE_ANNOTATIONS = "Type annotations";
    static final String REPEATING_ANNOTATIONS = "Repeating Annotations";
    static final String STATIC_INTERFACE_METHODS = "Default and static interface methods";
    static final String STREAM_API = "Stream API";
    static final String REQUIRE_NON_NULL = "objects.requireNonNull";
    static final String TRY_WITH_RESOURCES = "Try-with-resources";
    static final String JAVA_TIME_API = "Java.time API";
    static final String INSTANT_RUN = "Instant Run";
    static final String DATA_BINDING = "Data Binding";
    /*@Retention(RetentionPolicy.RUNTIME)
    public @interface Demos{
        Samples[] value() default {};
    }

    @Samples("Lambdas")
    @Samples("Method references")
    @Samples("Type annotations")
    @Samples("Repeating Annotations")
    @Samples("Default and static interface methods")
    public interface Demo{

    }

    @Repeatable(value = Demos.class)
    public @interface Samples{
      String value();
    };*/



    private String title;

    public DemoItem(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


}

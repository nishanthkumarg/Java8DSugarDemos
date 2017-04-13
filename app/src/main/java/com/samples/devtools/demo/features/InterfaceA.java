package com.samples.devtools.demo.features;

/**
 * Created by pprabhakaran on 3/29/17.
 */

public interface InterfaceA {

    public void textMe();

    default public int dialNumber(){
        return 987654321;
    }

    static boolean isNull(String str) {
        System.out.println("Interface Null Check");

        return str == null ? true : "".equals(str) ? true : false;
    }

}

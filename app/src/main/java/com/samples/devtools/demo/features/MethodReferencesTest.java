package com.samples.devtools.demo.features;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.samples.devtools.demo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReferencesTest extends AppCompatActivity {

    private TextView static_integer_list;
    private TextView static_lambda_form;
    private TextView static_mf_form;
    private TextView cons_integer_list;
    private TextView cons_lambda_form;
    private TextView cons_reference_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_references_test);
        static_integer_list = (TextView)findViewById(R.id.integer_list);
        static_lambda_form = (TextView)findViewById(R.id.static_mf_lambda_form);
        static_mf_form = (TextView)findViewById(R.id.static_mf_form);

        cons_integer_list = (TextView)findViewById(R.id.cons_integer_list);
        cons_lambda_form = (TextView)findViewById(R.id.constructor_mf_lambda_form);
        cons_reference_form = (TextView)findViewById(R.id.constructor_mf_form);

        referenceToStaticMethodExample();
        referenceToConstructor();
        referenceToInstanceMethodAOPT();
        referenceToInstanceMethodOAPO();
    }

    //Function to describe reference to static method
    private void referenceToStaticMethodExample() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16);
        static_integer_list.setText(static_integer_list.getText()+": \n"+ numbers.toString());

        //Lambda Expression From
        List<Integer> primeNumbersLambda = findPrimeNumbers(numbers, (number) -> isPrime(number));
        static_lambda_form.setText(static_lambda_form.getText()+": \n"+primeNumbersLambda.toString());

        //Method reference
        List<Integer> primeNumbersMethodReference = findPrimeNumbers(numbers, MethodReferencesTest::isPrime);
        static_mf_form.setText(static_mf_form.getText()+": \n"+primeNumbersMethodReference.toString());
    }

    //Function to describe reference to constructor method
    public void referenceToConstructor() {

        List  numbers = Arrays.asList(4,9,16,25,36);
        cons_integer_list.setText(cons_integer_list.getText()+": \n"+numbers.toString());

        //Lambda Form
        List squaredLambdaNumbers = MethodReferencesTest.findSquareRoot(numbers,x->new Integer(x));
        cons_lambda_form.setText(cons_lambda_form.getText()+": \n"+squaredLambdaNumbers.toString());

        //Reference to constructor
        List squaredNumbers = MethodReferencesTest.findSquareRoot(numbers,Integer::new);
        cons_reference_form.setText(cons_reference_form.getText()+": \n"+squaredNumbers.toString());

    }

    //Reference to an instance Method of an arbitrary object of a particular type
    public void referenceToInstanceMethodAOPT(){
        List persons = new ArrayList();
        persons.add(new Person("Albert", 80));
        persons.add(new Person("Ben", 15));
        persons.add(new Person("Charlote", 20));
        persons.add(new Person("Dean", 6));
        persons.add(new Person("Elaine", 17));


        List allAges = MethodReferencesTest.listAllAges(persons, Person::getAge);
        Log.e("",allAges.toString());
        System.out.println("Printing out all ages \n"+allAges);
    }


    //Reference To An Instance Method Of A Particular Object
    public void referenceToInstanceMethodOAPO(){
        List names = new ArrayList();
        names.add("David");
        names.add("Richard");
        names.add("Samuel");
        names.add("Rose");
        names.add("John");

        MethodReferencesTest.printNames(names,System.out::println);
    }









    private static void printNames(List list, Consumer c ){
        list.forEach(x -> c.accept(x));
    }

    private static List listAllAges(List person, Function<Person, Integer> f){
        List result = new ArrayList();
        person.forEach(x -> result.add(f.apply((Person)x)));
        return result;
    }


    private static List findSquareRoot(List list, Function<Integer,Integer> f){
        List result = new ArrayList();
        list.forEach(x -> result.add(Math.sqrt(f.apply((Integer) x))));
        return result;
    }

    public static boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i < number; i++)
        {
            if (number % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public static List findPrimeNumbers(List<Integer> list, Predicate<Integer> predicate)
    {
        List<Integer> sortedNumbers = new ArrayList<>();
        list.stream().filter((i) -> (predicate.test(i))).forEach((i) ->
        {
            sortedNumbers.add(i);
        });
        return sortedNumbers;
    }



    private static class Person {

        private final String name;
        private final int age;


        public Person(String name, int age) {
            this.name = name;
            this.age = age;

        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

    }
}

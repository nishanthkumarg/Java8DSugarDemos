package com.samples.devtools.demo.features;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.samples.devtools.demo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streams_test);


        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Albert", 80, Employee.Gender.MALE));
        employees.add(new Employee("Ben", 15, Employee.Gender.MALE));
        employees.add(new Employee("Amy", 20, Employee.Gender.FEMALE));
        employees.add(new Employee("Dean", 6, Employee.Gender.MALE));
        employees.add(new Employee("Arnold", 17, Employee.Gender.FEMALE));

        Button filterTest = (Button) findViewById(R.id.test_filter_action);
        filterTest.setOnClickListener((View v) -> {

            List<Employee> filtered = (List<Employee>) employees
                    .stream()
                    .filter(p -> ((Employee) p).getName().startsWith("A"))
                    .collect(Collectors.toList());
            StringBuilder newString = new StringBuilder();
            filtered.stream().forEach( u -> newString.append(u.getName()));

            Toast.makeText(this, "Test Completed.Result: " + newString,Toast.LENGTH_LONG).show();

                }
        );

    //Maps in streams

        Map<Integer, List<Employee>> personsByAge = (Map<Integer, List<Employee>>) employees
            .stream()
            .collect(Collectors.groupingBy(p -> ((Employee) p).getAge()));

        personsByAge
                .forEach((age, p) -> Log.i("Streams: Mapping age", age + "," + p.toString()));

    //Collector and String Joiner

        Button collectorTest = (Button) findViewById(R.id.test_collector_action);
        collectorTest.setOnClickListener((View v) -> {
                    Collector<Employee, StringJoiner, String> personNameCollector =
                            Collector.of(
                                    () -> new StringJoiner(" | "),          // supplier
                                    (j, p) -> j.add(p.getName().toUpperCase()),  // accumulator
                                    (j1, j2) -> j1.merge(j2),               // combiner
                                    StringJoiner::toString);                // finisher

                    String names = (String) employees
                            .stream()
                            .collect(personNameCollector);
                    Toast.makeText(this,"Test Completed.Result: " +names,Toast.LENGTH_LONG).show();
                });


        //FlatMap
        Button testflatMap = (Button) findViewById(R.id.test_map_action);
        testflatMap.setOnClickListener((View v) ->{
            String testString  = testFlatMap();
            Toast.makeText(this, "Test Completed.Result: " + testString,Toast.LENGTH_LONG).show();
        });

        //Parallel Stream
        Button testParallelStram = (Button) findViewById(R.id.test_parallel_stream_action);
        testParallelStram.setOnClickListener((View v) -> {
             testparallelStreams();
        });


}

    public static String testFlatMap(){
        List<Developer> team = new ArrayList<>();
        Developer polyglot = new Developer("esoteric");
        polyglot.add("clojure");
        polyglot.add("scala");
        polyglot.add("groovy");
        polyglot.add("go");

        Developer busy = new Developer("pragmatic");
        busy.add("java");
        busy.add("javascript");

        team.add(polyglot);
        team.add(busy);

        List<String> teamLanguages = team.stream().
                map(d -> d.getLanguages()).
                flatMap(l -> l.stream()).
                collect(Collectors.toList());

        String listString = teamLanguages.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
        return listString;
    }


    public void testparallelStreams(){
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    Log.i("Parallel Stream:filter:", s + Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    Log.i("Parallel Stream:map:", s + Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    Log.i("Parallel Stream:Sort:", s1 + s2 + Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    Log.i("filter: " , s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    Log.i("sort: " , s1 + "," + s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    Log.i("map: " , s);
                    return s.toUpperCase();
                })
                .forEach(s -> Log.i("forEach: " , s));

        Toast.makeText(StreamsTest.this, "Test completed Successfully.", Toast.LENGTH_LONG).show();
    }
}

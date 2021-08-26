package com.github.dmiller2117.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsWithObjects {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Adam", true));
        students.add(new Student("Sue", false));
        students.add(new Student("Kevin", false));
        students.add(new Student("Joe", true));
        students.add(new Student("Daniel", true));

        System.out.println("print students name:");
        students.stream().map(Student::getName).forEach(System.out::println);

        System.out.println("print students name starting with \"D\":");
        students.stream().map(Student::getName).filter(x -> x.startsWith("D")).forEach(System.out::println);

        System.out.println("print the local students:");
        students.stream().filter(s -> s.isLocal() == true).forEach(s -> System.out.println(s.getName()));

        System.out.println("count the local students:");
        // using a method reference for isLocal
        long count = students.stream().filter(Student::isLocal).count();
        System.out.printf("There are %d local students", count);
        System.out.println();

        System.out.println("we can construct a single string of the local students:");
        String names = students.stream().filter(s -> s.isLocal()).map(Student::getName).collect(Collectors.joining(" "));
        System.out.println(names);

        System.out.println("we can construct a single string of non-local students:");
        names = students.stream().filter(s -> s.isLocal() == false).map(Student::getName).collect(Collectors.joining(" "));
        System.out.print(names);
    }
}
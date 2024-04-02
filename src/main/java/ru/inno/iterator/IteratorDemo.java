package ru.inno.iterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class IteratorDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("input = " + input);

        MyIterator iterator = new MyIterator(input);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }



    }
}

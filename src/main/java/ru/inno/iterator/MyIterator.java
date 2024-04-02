package ru.inno.iterator;

public class MyIterator {

    private final String data;
    private int index;
    public MyIterator(String data) {
        this.data = data;
        this.index = -1;

    }

    public boolean hasNext() {
        return index != data.length()-1;
    }

    public char next() {
        index++;
        return data.charAt(index);
    }
}

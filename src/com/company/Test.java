package com.company;

public class Test {

    public static void main(String[] args) {
        String a = "Dear\n" +
                "\n" +
                "Kindly please find attached quotation.";

        System.out.println(a);
        a = a.replace("\n","123");
        System.out.println(a);
    }
}

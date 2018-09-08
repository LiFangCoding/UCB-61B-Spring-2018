package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] someInts = new int[]{1, 2, 3};
        for (int x : someInts) {
            for (int y: someInts) {
                System.out.println("x: " + x +  ", y:" + y);
            }
        }
    }
}

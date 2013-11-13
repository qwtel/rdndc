package com.tumblr.cell303.rdndc.example;

import com.tumblr.cell303.rdndc.creator.RandomCreator;

public class CreatorExample {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            ZipCodeFormatter formatter = (ZipCodeFormatter) RandomCreator.create(ZipCodeFormatter.class);
            System.out.println(formatter.getClass().getSimpleName() + ": " + formatter.format("123"));
        }
    }
}

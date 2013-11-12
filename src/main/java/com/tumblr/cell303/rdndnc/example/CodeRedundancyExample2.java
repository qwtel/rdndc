package com.tumblr.cell303.rdndnc.example;

import com.tumblr.cell303.rdndnc.type.Creator;

public class CodeRedundancyExample2 {

    private static Creator creator = new Creator(IZipCodeFormatter.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            IZipCodeFormatter formatter = (IZipCodeFormatter) creator.apply();
        }
    }
}

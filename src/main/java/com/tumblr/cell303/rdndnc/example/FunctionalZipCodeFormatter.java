package com.tumblr.cell303.rdndnc.example;

import com.tumblr.cell303.rdndnc.type.Redundancy;

@Redundancy(IZipCodeFormatter.class)
public class FunctionalZipCodeFormatter implements IZipCodeFormatter {

    @Override
    public String format(String digits) {
        if (digits.length() >= 5) return digits;
        else return format('0' + digits);
    }
}

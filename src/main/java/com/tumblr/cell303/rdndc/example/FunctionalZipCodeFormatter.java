package com.tumblr.cell303.rdndc.example;

import com.tumblr.cell303.rdndc.creator.Redundancy;

@SuppressWarnings("unused")
@Redundancy(ZipCodeFormatter.class)
public class FunctionalZipCodeFormatter extends ZipCodeFormatter {

    @Override
    public String format(String digits) {
        if (digits.length() >= 5) return digits;
        else return format('0' + digits);
    }
}

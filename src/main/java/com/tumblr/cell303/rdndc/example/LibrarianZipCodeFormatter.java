package com.tumblr.cell303.rdndc.example;

import com.tumblr.cell303.rdndc.creator.Redundancy;

@SuppressWarnings("unused")
@Redundancy(ZipCodeFormatter.class)
public class LibrarianZipCodeFormatter extends ZipCodeFormatter {

    @Override
    public String format(String digits) {
        return String.format("%05d", Integer.parseInt(digits));
    }
}

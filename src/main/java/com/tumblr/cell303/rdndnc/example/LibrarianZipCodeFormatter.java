package com.tumblr.cell303.rdndnc.example;

import com.tumblr.cell303.rdndnc.type.Redundancy;

@Redundancy(IZipCodeFormatter.class)
public class LibrarianZipCodeFormatter implements IZipCodeFormatter {
    @Override
    public String format(String digits) {
        return String.format("%05d", Integer.parseInt(digits));
    }
}

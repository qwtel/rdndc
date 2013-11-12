package com.tumblr.cell303.rdndnc.example;

import com.tumblr.cell303.rdndnc.type.Redundancy;

@Redundancy(IZipCodeFormatter.class)
public class PragmaticZipCodeFormatter implements IZipCodeFormatter {
    @Override
    public String format(String digits) {
        switch (digits.length()) {
            case 4: return "0" + digits;
            case 3: return "00" + digits;
        }
        return null;
    }
}

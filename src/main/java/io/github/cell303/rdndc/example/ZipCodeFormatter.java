package io.github.cell303.rdndc.example;

public class ZipCodeFormatter {

    public String format(String digits) {
        switch (digits.length()) {
            case 4: return "0" + digits;
            case 3: return "00" + digits;
        }
        return null;
    }
}

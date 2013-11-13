package com.tumblr.cell303.rdndc.invoker.helper;

import com.tumblr.cell303.rdndc.invoker.rdndc;

public class Formatter2 extends Formatter {

    @Override
    @rdndc("format")
    public String librarianFormat(String digits) {
        return super.librarianFormat(digits);
    }

    @Override
    @rdndc("format")
    public String functionalFormat(String digits) {
        return super.functionalFormat(digits);
    }

    @Override
    public String faultyFormat(String digits) {
        throw new RuntimeException("This should never happen since it's not annotated!");
    }
}

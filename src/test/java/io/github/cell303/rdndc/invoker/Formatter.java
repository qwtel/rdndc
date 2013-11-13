package io.github.cell303.rdndc.invoker;

import io.github.cell303.rdndc.invoker.Invoker;
import io.github.cell303.rdndc.invoker.SequentialInvoker;
import io.github.cell303.rdndc.invoker.rdndc;

public class Formatter {

    private Invoker invoker = new SequentialInvoker(this);

    public String format(String digits) {
        return (String) invoker.invoke("format", digits);
    }

    @rdndc("format")
    public String librarianFormat(String digits) {
        return String.format("%05d", Integer.parseInt(digits));
    }

    @rdndc("format")
    public String functionalFormat(String digits) {
        if (digits.length() >= 5) return digits;
        else return functionalFormat('0' + digits);
    }

    @rdndc("format")
    public String faultyFormat(String digits) {
        throw new RuntimeException("Faulty implementation");
    }

    public String format(int i, String digits) {
        return (String) invoker.invoke("format", i, digits);
    }

    @rdndc("format")
    public String format2(int i, String digits) {
        if (digits.length() >= i) return digits;
        else return format2(i, '0' + digits);
    }

    @rdndc("format")
    public String format3(int i, String digits) {
        return String.format("%0"+i+"d", Integer.parseInt(digits));
    }
}

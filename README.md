rdndc
=====

When writing software we face a lot of uncertainty. Even when properly tested there is no guarantee that code
is really bug-free. The best way to combat uncertainty is redundancy. Nature figured this out for us, this is why we have 
two eyes, two lungs, two kidneys, etc. We also use a lot of redundancy in software, for example in file storage. 
However, so far there's not much redundancy in the code itself. Usually there is exactly one piece of code for 
any given task.

rdndc for Java is an experiment in code redundancy, which allows you to write multiple implementations of methods 
(and to some extent classes) that will be used based on some strategy. Redundant implementations of methods can be 
invoked probabilistically or as a fall back if an exception gets thrown.

Annotations are used to mark methods as similar or "redundant":

    Invoker invoker = new RandomInvoker(this); // choose strategy

    public String format(String digits) { // public stub
        return (String) invoker.invoke("format", digits); // invoker chooses the actual implementation
    }

    @rdndc("format") // mark the method as redundant
    private String librarianFormat(String digits) { // private implementation
        return String.format("%05d", Integer.parseInt(digits));
    }

    @rdndc("format") // mark the method as redundant
    private String functionalFormat(String digits) { // another implementation
        if (digits.length() >= 5) return digits;
        else return functionalFormat('0' + digits);
    }


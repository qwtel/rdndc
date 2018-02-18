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

### Example

~~~java
Invoker invoker = new RandomInvoker(this); // different invokers use different strategies

public String format(String digits) { // public stub
    return (String) invoker.invoke("format", digits); // the invoker chooses the implementation
}

@rdndc("format") // mark method as redundant
private String librarianFormat(String digits) { // private implementation
    return String.format("%05d", Integer.parseInt(digits));
}

@rdndc("format") // mark method as redundant
private String functionalFormat(String digits) { // another implementation
    if (digits.length() >= 5) return digits;
    else return functionalFormat('0' + digits);
}
~~~

### Acknowledgements

Inspired by and examples taken from [The Narcissism of Small Code Differences](http://raganwald.com/2008/05/narcissism-of-small-code-differences.html).


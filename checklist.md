## Common mistakes (jv-salary-info)

#### Don't begin class or method implementation with an empty line.  
Remove all redundant empty lines, be careful :)
#### If you create a [formatter](https://docs.oracle.com/javase/tutorial/datetime/iso/format.html), make it a constant field.
If you create a formatter, make it a [constant](https://mate-academy.github.io/style-guides/java/java.html#s5.2.4-constant-names).
That will make your code easier to understand. Plus if you use it in several places in your code 
you will be able to change its value easily with one action(change variable declaration). 
In other cases, you need to update the value in each place it's being used.
#### Use LocalDate instead of Date.
Here is some [background](https://www.baeldung.com/migrating-to-java-8-date-time-api) why you should use it primarily.
#### Don’t create redundant loops. Two is enough.
The more loops you have, the slower your code may get, so try to solve task with minimum amount.
#### Don’t use two-dimension arrays and HashMap(or any other Map).
They may simplify the work for you, but for now you will get more value from solving this task if you complete 
it without using these data structures. Let's do it manually.
#### Use StringBuilder.
You have learned in previous topics about different internal structures of `String` and `StringBuilder` don't forget 
to use them in practice. Keep in mind that String concatenation creates many new objects that take up a lot 
of memory if you use it inside of a loop. Though it's safe to use it outside of a loop because the compiler 
will replace it with StringBuilder anyway  [java doc](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
#### Use System.lineSeparator
Different OS handle line separators differently. 
Instead of using `\n` or `\r\n` use universal solution - `System.lineSeparator()`.
#### Remember about informative names of the variables and methods.
Do not use abstract words like `string` or `array` as variable names. Do not use one-letter names. The name of 
the method should make it clear what it does.
- Bad example:
    ```java
    String[] arr = new String[]{"Alex", "Bob", "Alice"};
    for (String s : arr) {
        System.out.println(s);
    }
    ```
- Refactored code:
    ```java
    String[] usernames = new String[]{"Alex", "Bob", "Alice"};
    for (String username : usernames) {
        System.out.println(username);
    }
    ```

#### Any magic numbers should be constants
Your code should be easy to read. Move all hardcoded values
to [constant fields](https://mate-academy.github.io/style-guides/java/java.html#s5.2.4-constant-names) and give them informative names.
#### Write informative messages when you commit code or open a PR.
Bad examples of commit/PR messages: `done`/`fixed`/`commit`/`solution`/`added homework`/`my solution` and other one-word, abstract or random messages.

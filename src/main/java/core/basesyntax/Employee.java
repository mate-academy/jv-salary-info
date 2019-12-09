package core.basesyntax;

public class Employee {
    private String name;
    private int value = 0;

    public Employee(String nameLocal) {
        name = nameLocal;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value += value;
    }
}

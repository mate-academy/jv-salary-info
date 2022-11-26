package core.basesyntax;

public class User {
    private String name;
    private int earned;

    public User(String name) {
        this.name = name;
    }

    public void addMoney(int days, int price) {
        this.earned = this.earned + days * price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEarned() {
        return earned;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }
}

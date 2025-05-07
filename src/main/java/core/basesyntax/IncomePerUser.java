package core.basesyntax;

public class IncomePerUser {
    private String[] userNames;
    private int[] incomes;

    public IncomePerUser(String[] userNames) {
        if (userNames == null) {
            throw new IllegalArgumentException("Provide an Array");
        }

        this.userNames = userNames;
        this.incomes = new int[userNames.length];
    }

    public String[] getNames() {
        return userNames;
    }

    public int getIncome(String name) {
        int index = getIncomeIndex(name);

        if (index == -1) {
            throw new IllegalArgumentException("No such key: " + name);
        }

        return incomes[index];
    }

    public void addIncome(String name, int income) {
        int index = getIncomeIndex(name);

        if (index == -1) {
            throw new IllegalArgumentException("No such key: " + name);
        }

        incomes[index] += income;
    }

    private int getIncomeIndex(String name) {
        for (int i = 0; i < userNames.length; i++) {
            if (userNames[i].equals(name)) {
                return i;
            }
        }

        return -1;
    }
}

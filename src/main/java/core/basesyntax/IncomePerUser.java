package core.basesyntax;

public class IncomePerUser {
    private String[] userNames;
    private int[] incomes;

    public IncomePerUser(String[] userNames) {
        this.userNames = userNames;
        this.incomes = new int[userNames.length];
    }

    public String[] getNames() {
        return userNames;
    }

    public int getSum(String name) {
        int index = getSumIndex(name);

        if (index == -1) {
            throw new IllegalArgumentException("No such key: " + name);
        }

        return incomes[index];
    }

    public void increment(String name, int sum) {
        int index = getSumIndex(name);

        if (index == -1) {
            throw new IllegalArgumentException("No such key: " + name);
        }

        incomes[index] += sum;
    }

    private int getSumIndex(String name) {
        for (int i = 0; i < userNames.length; i++) {
            if (userNames[i].equals(name)) {
                return i;
            }
        }

        return -1;
    }
}

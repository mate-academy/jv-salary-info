package core.basesyntax;

public class SalaryCalculator {
    private static final int INITIAL_AMOUNT = 0;

    public String toCalculateSalary(String[] names, String[] infoData) {
        StringBuilder result = new StringBuilder();
        for (String name: names) {
            int salarySum = INITIAL_AMOUNT;
            for (int i = 0; i < infoData.length; i++) {
                if (infoData[i].contains(name)) {
                    String[] earnedMoney = infoData[i].substring(name.length() + 1).split(" ");
                    salarySum += Integer.parseInt(earnedMoney[0])
                            * Integer.parseInt(earnedMoney[1]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salarySum);
        }
        return result.toString().trim();
    }
}

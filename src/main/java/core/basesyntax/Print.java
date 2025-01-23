package core.basesyntax;

public class Print {
    public String getPrint(String[] money, String dateFrom, String dateTo) {

        StringBuilder results = new StringBuilder();
        results.append("Report for period " + dateFrom + " - " + dateTo + "\n");

        for (String moneyPerPerson : money) {
            results.append(moneyPerPerson + "\n");
        }

        return results.toString();
    }
}

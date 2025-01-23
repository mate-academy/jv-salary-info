package core.basesyntax;

public class Print {
    public String getPrint(String[] money, String dateFrom, String dateTo) {

        StringBuilder results = new StringBuilder();
        results.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String moneyPerPerson : money) {
            if (moneyPerPerson != null && !moneyPerPerson.trim().isEmpty()) {
                results.append(moneyPerPerson).append(System.lineSeparator());
            } else {
                System.out.println("Invalid data detected: " + moneyPerPerson);
            }
        }
        return results.toString();
    }
}



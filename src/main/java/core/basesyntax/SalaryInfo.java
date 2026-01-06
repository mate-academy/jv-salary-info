package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int day = 0;
    private static final int name = 1;
    private static final int hours = 2;
    private static final int amount = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dtFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dtTo = LocalDate.parse(dateTo, formatter);
        LocalDate actualDate;
        int[] totals = new int[names.length];
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String person : data) {
            String[] devidedData = person.split(" ");
            actualDate = LocalDate.parse(devidedData[day], formatter);

            if (!actualDate.isBefore(dtFrom) && !actualDate.isAfter(dtTo)) {
                String employee = devidedData[name];
                int earned = Integer.parseInt(devidedData[hours])
                        * Integer.parseInt(devidedData[amount]);
                for (int i = 0; i < names.length; i++) {
                    if (employee.equals(names[i])) {
                        totals[i] += earned;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ");
            result.append(totals[i]);
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}

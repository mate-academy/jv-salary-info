package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        LocalDate localDateFrom = convert(dateFrom);
        LocalDate localDateTo = convert(dateTo);
        for (String name : names) {
            int moneyEarned = 0;
            for (String datum : data) {
                String[] dataInArray = datum.split(" ");
                LocalDate dateOnWork = convert(dataInArray[0]);
                if (dateOnWork.compareTo(localDateFrom) >= 0
                        && dateOnWork.compareTo(localDateTo) <= 0
                        && dataInArray[1].equals(name)) {
                    moneyEarned += Integer.parseInt(dataInArray[2])
                            * Integer.parseInt(dataInArray[3]);
                }
            }
            result.append(name).append(" - ")
                    .append(moneyEarned).append("\n");
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }

    private LocalDate convert(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}

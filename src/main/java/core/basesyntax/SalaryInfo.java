package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATE);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATE);
        StringBuilder resultStrBuilder = new StringBuilder();
        resultStrBuilder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int salary = 0;

            for (String entryData : data) {
                String[] splitData = entryData.split(" ");

                if (isDateInRange(LocalDate.parse(splitData[0], DATE_FORMATE),
                        from,
                        to)
                        && name.equals(splitData[1])) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }

            resultStrBuilder.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }

        return resultStrBuilder.toString().trim();
    }

    private boolean isDateInRange(LocalDate entryDate, LocalDate dateFrom, LocalDate dateTo) {
        return (!entryDate.isBefore(dateFrom) && !entryDate.isAfter(dateTo));
    }
}

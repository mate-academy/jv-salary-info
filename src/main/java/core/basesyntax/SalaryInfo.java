package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATA_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATA_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo,DATA_FORMATTER);
        String[] splitData;
        LocalDate date;
        String dataName;
        int hour;
        int wage;
        int salary;

        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String line: data) {
                splitData = line.split(" ");
                date = LocalDate.parse(splitData[DATE_INDEX], DATA_FORMATTER);
                dataName = splitData[1];
                hour = Integer.parseInt(splitData[2]);
                wage = Integer.parseInt(splitData[3]);

                if (dataName.equals(names[i])) {
                    if (date.equals(startDate) || date.equals(endDate)
                            || (date.isAfter(startDate) && date.isBefore(endDate))) {
                        salary += wage * hour;
                    }
                }
            }
            sb.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return sb.toString();
    }
}

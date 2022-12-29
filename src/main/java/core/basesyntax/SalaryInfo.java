package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = parseToLocalDate(dateFrom);
        LocalDate localDateTo = parseToLocalDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] splitData = record.split(" ");
                LocalDate localDateFromData = parseToLocalDate(splitData[0]);
                if (splitData[1].equals(name)
                        & (localDateFromData.isAfter(localDateFrom)
                        || localDateFromData.isEqual(localDateFrom))
                        & (localDateFromData.isBefore(localDateTo)
                        || localDateFromData.isEqual(localDateTo))) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    public static LocalDate parseToLocalDate(String dateIn) {
        return LocalDate.parse(dateIn, FORMATTER);
    }
}

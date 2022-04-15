package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

    public boolean inPeriod(String date, String dateFrom, String dateTo) {
        try {
            LocalDate checkDate = LocalDate.parse(date,
                    DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
            LocalDate dateStart = LocalDate.parse(dateFrom,
                    DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
            LocalDate dateEnd = LocalDate.parse(dateTo,
                    DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
            return (checkDate.isAfter(dateStart)
                    && checkDate.isBefore(dateEnd))
                    || checkDate.isEqual(dateStart)
                    || checkDate.isEqual(dateEnd);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Error of parsing string to date!");
        }
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        int salarySum;
        for (String name : names) {
            salarySum = 0;
            for (String dataRow : data) {
                if (inPeriod(dataRow.split(" ")[0], dateFrom, dateTo)
                        && name.equals(dataRow.split(" ")[1])) {
                    salarySum += Integer.parseInt(dataRow.split(" ")[2])
                        * Integer.parseInt(dataRow.split(" ")[3]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salarySum);
        }
        return result.toString();
    }
}

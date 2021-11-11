package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ")
                               .append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateFromInLocalDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToInLocalDate = LocalDate. parse(dateTo, DATE_FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String stringOfData : data) {
                LocalDate salaryDate = LocalDate.parse(stringOfData
                        .substring(0, stringOfData.indexOf(" ")), DATE_FORMATTER);
                if (stringOfData.contains(name)
                        && salaryDate.compareTo(dateFromInLocalDate) >= 0
                        && salaryDate.compareTo(dateToInLocalDate) <= 0) {
                    Integer hoursPerDay = Integer.parseInt(stringOfData.substring(
                            stringOfData.lastIndexOf(name) + name.length() + 1,
                            stringOfData.lastIndexOf(" ")));
                    Integer incomePerHour =
                            Integer.parseInt(stringOfData.substring(stringOfData
                                    .lastIndexOf(" ") + 1));
                    totalSalary += hoursPerDay * incomePerHour;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();
    }
}

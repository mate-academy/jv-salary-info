package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int INDEX_SALARY_DATE = 0;
    private static final int INDEX_HOURS_PER_DAY = 1;
    private static final int INDEX_INCOME_PER_HOUR = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromInLocalDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToInLocalDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String stringOfData : data) {
                LocalDate salaryDate = LocalDate
                        .parse(parseData(stringOfData)[INDEX_SALARY_DATE], DATE_FORMATTER);
                if (stringOfData.contains(name)
                        && salaryDate.compareTo(dateFromInLocalDate) >= 0
                        && salaryDate.compareTo(dateToInLocalDate) <= 0) {
                    Integer hoursPerDay = Integer
                            .parseInt(parseData(stringOfData)[INDEX_HOURS_PER_DAY]);
                    Integer incomePerHour = Integer
                            .parseInt(parseData(stringOfData)[INDEX_INCOME_PER_HOUR]);
                    totalSalary += hoursPerDay * incomePerHour;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return result.toString();
    }

    private String[] parseData(String stringOfData) {
        String salaryDate = stringOfData.substring(0, stringOfData.indexOf(" "));
        String hoursPerDay = stringOfData.replaceAll(".*\\w+ (\\d+) .*", "$1");
        String incomePerHour = stringOfData.substring(stringOfData.lastIndexOf(" ") + 1);
        return new String[]{salaryDate, hoursPerDay, incomePerHour};
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_HOURS_PER_DAY = 2;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_SALARY_PER_DAY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String userData : data) {
                String[] userDataArray = userData.split(" ");
                LocalDate userWorkingDay = LocalDate
                        .parse(userData.substring(0, userData.indexOf(' ')), DATE_FORMATTER);
                if (name.equals(userDataArray[INDEX_OF_NAME])
                        && userWorkingDay
                        .isAfter(LocalDate.parse(dateFrom, DATE_FORMATTER).minusDays(1))
                        && userWorkingDay
                        .isBefore(LocalDate.parse(dateTo, DATE_FORMATTER).plusDays(1))) {
                    salary += Integer.parseInt(userDataArray[INDEX_HOURS_PER_DAY])
                            * Integer.parseInt(userDataArray[INDEX_SALARY_PER_DAY]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splittedData;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                splittedData = datum.split(" ");
                LocalDate workDate = LocalDate.parse(splittedData[INDEX_OF_DATE], formatter);
                if ((workDate.isAfter(fromDate) && workDate.isBefore(toDate)
                        || workDate.equals(fromDate)
                        || workDate.equals(toDate))
                        && name.equals(splittedData[INDEX_OF_NAME])) {
                    salary += Integer.parseInt(splittedData[INDEX_OF_HOURS])
                              * Integer.parseInt(splittedData[INDEX_OF_SALARY_PER_HOUR]);
                }
            }
            builder.append(System.lineSeparator())
                   .append(name).append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}

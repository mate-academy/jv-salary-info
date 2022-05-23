package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_FOR_DATE = 0;
    private static final int INDEX_HOURS_PER_DAY = 2;
    private static final int INDEX_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int employeesSalary = 0;
            for (String dataInfo: data) {
                String[] dataSplitted = dataInfo.split(" ");
                LocalDate localDateNow = LocalDate.parse(dataSplitted[INDEX_FOR_DATE], FORMATTER);
                if (name.equals(dataSplitted[1])
                        && (localDateNow.isEqual(localDateFrom)
                        || localDateNow.isAfter(localDateFrom)
                        && localDateNow.isBefore(localDateTo)
                        || localDateNow.isEqual(localDateTo))) {
                    employeesSalary += Integer.parseInt(dataSplitted[INDEX_HOURS_PER_DAY])
                            * Integer.parseInt(dataSplitted[INDEX_SALARY_PER_HOUR]);
                }
            }
            builder.append(System.lineSeparator()).append(name)
                   .append(" - ").append(employeesSalary);
        }
        return builder.toString();
    }
}

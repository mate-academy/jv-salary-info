package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DAY = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private LocalDate dateOfEmployeeLD = null;

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        StringBuilder reportBuffer = new StringBuilder();
        LocalDate dateFromLD = LocalDate.parse(dateFrom, FORMATTER).minusDays(1);
        LocalDate dateToLD = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        int [] salaryNumbers = new int [names.length];
        reportBuffer.append("Report for period ")
                .append(dateFrom)
                .append(" ")
                .append("-")
                .append(" ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                dateOfEmployeeLD = LocalDate.parse(data[j].split(" ")[INDEX_OF_DAY], FORMATTER);
                if ((names[i].equals(data[j].split(" ")[INDEX_OF_NAME]))
                        && ((dateOfEmployeeLD.isAfter(dateFromLD))
                                && dateOfEmployeeLD.isBefore(dateToLD))) {
                    salaryNumbers[i] += Integer.parseInt(data[j].split(" ")[INDEX_OF_HOURS])
                            * Integer.parseInt(data[j].split(" ")[INDEX_OF_SALARY]);
                }
            }
            reportBuffer.append(System.lineSeparator())
                    .append(names[i])
                    .append(" ")
                    .append("-")
                    .append(" ")
                    .append(salaryNumbers[i]);
        }
        return reportBuffer.toString();
    }
}

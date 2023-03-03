package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder finalReport = new StringBuilder();
        String[] checkLine;
        boolean isDateInRange;
        LocalDate dateRangeBegin;
        LocalDate dateRangeEnd;
        LocalDate dataSalary;

        finalReport.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            finalReport.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;

            for (String dateString : data) {
                checkLine = dateString.split(SEPARATOR);
                dateRangeBegin = LocalDate.parse(dateFrom, formatter);
                dateRangeEnd = LocalDate.parse(dateTo, formatter);
                dataSalary = LocalDate.parse(checkLine[0], formatter);
                isDateInRange = dateRangeBegin.isBefore(dataSalary)
                        && dateRangeEnd.isAfter(dataSalary)
                        || dateRangeBegin.equals(dataSalary)
                        || dateRangeEnd.equals(dataSalary);

                if (name.equals(checkLine[1]) && isDateInRange) {
                    salary += (Integer.parseInt(checkLine[2]) * Integer.parseInt(checkLine[3]));
                }
            }
            finalReport.append(salary);
        }
        return finalReport.toString();
    }
}

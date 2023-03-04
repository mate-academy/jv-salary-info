package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder finalReport = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateRangeBegin = LocalDate.parse(dateFrom, formatter);
        LocalDate dateRangeEnd = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int salary = 0;

            for (String dateString : data) {
                String[] checkLine = dateString.split(SEPARATOR);
                LocalDate dataSalary = LocalDate.parse(checkLine[0], formatter);
                boolean isDateInRange = dateRangeBegin.isBefore(dataSalary)
                        && dateRangeEnd.isAfter(dataSalary)
                        || dateRangeBegin.equals(dataSalary)
                        || dateRangeEnd.equals(dataSalary);

                if (name.equals(checkLine[1]) && isDateInRange) {
                    salary += (Integer.parseInt(checkLine[2]) * Integer.parseInt(checkLine[3]));
                }
            }
            finalReport.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return finalReport.toString();
    }
}

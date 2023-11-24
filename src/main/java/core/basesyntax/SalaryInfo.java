package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
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
                LocalDate dataSalary = LocalDate.parse(checkLine[DATE_INDEX], formatter);
                boolean isDateInRange = dateRangeBegin.isBefore(dataSalary)
                        && dateRangeEnd.isAfter(dataSalary)
                        || dateRangeBegin.equals(dataSalary)
                        || dateRangeEnd.equals(dataSalary);

                if (name.equals(checkLine[NAME_INDEX]) && isDateInRange) {
                    salary += (Integer.parseInt(checkLine[HOURS_INDEX])
                            * Integer.parseInt(checkLine[SALARY_INDEX]));
                }
            }
            finalReport.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return finalReport.toString();
    }
}

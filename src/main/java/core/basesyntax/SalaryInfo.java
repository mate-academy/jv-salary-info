package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = parseDate(dateFrom);
        LocalDate toDate = parseDate(dateTo);
        StringBuilder salaryInfoBuilder = new StringBuilder();
        salaryInfoBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] recordSplitted = record.split(" ");
                LocalDate dataDate = parseDate(recordSplitted[DATE_INDEX]);
                if (name.equals(recordSplitted[NAME_INDEX])
                        && dataDate.compareTo(fromDate) >= 0
                        && dataDate.compareTo(toDate) <= 0) {
                    int currentSalary = Integer.parseInt(recordSplitted[HOURS_INDEX])
                            * Integer.parseInt(recordSplitted[PER_HOUR_INDEX]);
                    salary += currentSalary;
                }
            }
            salaryInfoBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }

        return salaryInfoBuilder.toString();
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}

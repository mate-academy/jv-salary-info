package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (int i = 0; i < data.length; i++) {
                String[] splittedData = data[i].split(SEPARATOR);
                if (name.equals(splittedData[NAME_INDEX]) && isDateInRange(splittedData[DATE_INDEX],
                        dateFrom, dateTo)) {
                    int hours = Integer.parseInt(splittedData[WORKING_HOURS_INDEX]);
                    int salaryPerHour = Integer.parseInt(splittedData[INCOME_PER_HOUR_INDEX]);
                    int salary = hours * salaryPerHour;
                    totalSalary += salary;
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }

        return report.toString();
    }

    private static boolean isDateInRange(String dateString, String dateFrom, String dateTo) {
        LocalDate date = LocalDate.parse(dateString, FORMATTER);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        return !(date.isBefore(fromDate) || date.isAfter(toDate));
    }
}

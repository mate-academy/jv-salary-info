package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder message = new StringBuilder();

        message.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\r\n");

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] splitArray = record.split(" ");
                String employeeName = splitArray[NAME_INDEX];
                LocalDate workDate = LocalDate.parse(splitArray[DATE_INDEX], formatter);
                int hoursWorked = Integer.parseInt(splitArray[HOURS_INDEX]);
                int salaryPerHour = Integer.parseInt(splitArray[INCOME_INDEX]);

                if (employeeName.equals(name)
                        && (workDate.isEqual(startDate) || workDate.isEqual(endDate)
                        || (workDate.isAfter(startDate) && workDate.isBefore(endDate)))) {
                    totalSalary += hoursWorked * salaryPerHour;
                }
            }
            message.append(name).append(" - ").append(totalSalary).append("\r\n");
        }
        message.deleteCharAt(message.length() - 1);
        message.deleteCharAt(message.length() - 1);
        return message.toString();
    }
}

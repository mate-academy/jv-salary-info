package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final String DELIMITER = " ";
    static final int DATE = 0;
    static final int NAME = 1;
    static final int HOURS_WORKED = 2;
    static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordData = record.split(DELIMITER);
                LocalDate recordDate = LocalDate.parse(recordData[DATE], formatter);
                if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                    if (recordData[NAME].equals(name)) {
                        int hoursWorked = Integer.parseInt(recordData[HOURS_WORKED]);
                        int salaryPerHour = Integer.parseInt(recordData[SALARY_PER_HOUR]);
                        int salaryPerDay = hoursWorked * salaryPerHour;
                        totalSalary += salaryPerDay;

                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return result.toString();
    }
}

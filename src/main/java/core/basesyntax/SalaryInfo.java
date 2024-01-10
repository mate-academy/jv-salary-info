package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom,DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        for (String name : names) {
            int totalSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] date = data[i].split(" ");
                LocalDate currentDate = LocalDate.parse(date[DATE_INDEX], DATE_FORMAT);
                String employeeName = date[NAME_INDEX];
                int hoursWorked = Integer.parseInt(date[HOURS_WORKED_INDEX]);
                int hourlyRate = Integer.parseInt(date[HOURLY_RATE_INDEX]);
                int salary = hoursWorked * hourlyRate;
                if ((currentDate.isAfter(fromDate) || currentDate.equals(fromDate))
                            && (currentDate.isBefore(toDate) || currentDate.equals(toDate))
                            && employeeName.equals(name)) {
                    totalSalary += salary;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ")
                        .append(totalSalary);
        }
        return result.toString();
    }
}

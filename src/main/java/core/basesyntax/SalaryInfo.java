package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int datePosition = 0;
    private static final int namePosition = 1;
    private static final int hoursWorkedPosition = 2;
    private static final int salaryPerHourPosition = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name: names) {
            int salary = 0;
            for (String record: data) {
                if (name.equals(getName(record)) && validateDate(record, dateFrom, dateTo)) {
                    salary += getDailySalary(record);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return stringBuilder.toString();
    }

    private String getName(String data) {
        return data.split(" ")[namePosition];
    }

    private boolean validateDate(String data, String dateFrom, String dateTo) {
        String dateString = data.split(" ")[datePosition];
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);

        return !date.isBefore(dateStart) & !date.isAfter(dateEnd);
    }

    private int getDailySalary(String data) {
        int numberOfHoursWorked = Integer.parseInt(data.split(" ")[hoursWorkedPosition]);
        int salaryPerHour = Integer.parseInt(data.split(" ")[salaryPerHourPosition]);
        return numberOfHoursWorked * salaryPerHour;
    }
}

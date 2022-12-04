package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
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
        final int namePosition = 1;
        return data.split(" ")[namePosition];
    }

    private boolean validateDate(String data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final int datePosition = 0;
        String dateString = data.split(" ")[datePosition];
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);

        return !date.isBefore(dateStart) & !date.isAfter(dateEnd);
    }

    private int getDailySalary(String data) {
        final int hoursWorkedPosition = 2;
        final int salaryPerHourPosition = 3;
        int numberOfHoursWorked = Integer.parseInt(data.split(" ")[hoursWorkedPosition]);
        int salaryPerHour = Integer.parseInt(data.split(" ")[salaryPerHourPosition]);
        return numberOfHoursWorked * salaryPerHour;
    }
}

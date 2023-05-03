package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_WORKED_POSITION = 2;
    private static final int SALARY_PER_HOUR_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);
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
        return data.split(" ")[NAME_POSITION];
    }

    private boolean validateDate(String data, String dateFrom, String dateTo) {
        String dateString = data.split(" ")[DATE_POSITION];
        LocalDate date = LocalDate.parse(dateString, FORMATTER);
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);

        return !date.isBefore(dateStart) & !date.isAfter(dateEnd);
    }

    private int getDailySalary(String data) {
        int numberOfHoursWorked = Integer.parseInt(data.split(" ")[HOURS_WORKED_POSITION]);
        int salaryPerHour = Integer.parseInt(data.split(" ")[SALARY_PER_HOUR_POSITION]);
        return numberOfHoursWorked * salaryPerHour;
    }
}

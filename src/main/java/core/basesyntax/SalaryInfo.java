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
                if (name.equals(getName(record)) && compareDates(record, dateFrom, dateTo)) {
                    salary += getDailySalary(record);
                }
            }
            stringBuilder.append("\n").append(name).append(" - ").append(salary);
        }

        return stringBuilder.toString();
    }

    private String getName(String data) {
        return data.split(" ")[1];
    }

    private boolean compareDates(String data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateString = data.split(" ")[0];
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);

        return !date.isBefore(dateStart) & !date.isAfter(dateEnd);
    }

    private int getDailySalary(String data) {
        int numberOfHoursWorked = Integer.parseInt(data.split(" ")[2]);
        int salaryPerHour = Integer.parseInt(data.split(" ")[3]);
        return numberOfHoursWorked * salaryPerHour;
    }
}

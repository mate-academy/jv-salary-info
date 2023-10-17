package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        for (int i = 0; i < names.length; i++) {
            int salary = calculateSalaryForPerson(names[i], data, startDate, endDate);
            stringBuilder.append(names[i]).append(" - ").append(salary);
            if (i != names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    private int calculateSalaryForPerson(String name, String[] data,
                                         LocalDate startDate, LocalDate endDate) {
        int salary = 0;
        for (String entry : data) {
            String[] allData = entry.split(" ");
            LocalDate dateData = LocalDate.parse(allData[0], FORMATTER);
            if (name.equals(allData[1]) && isDateInRange(dateData, startDate, endDate)) {
                salary += Integer.parseInt(allData[2]) * Integer.parseInt(allData[3]);
            }
        }
        return salary;
    }

    private boolean isDateInRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return (date.isAfter(startDate) || date.isEqual(startDate))
                && (date.isBefore(endDate) || date.isEqual(endDate));
    }
}

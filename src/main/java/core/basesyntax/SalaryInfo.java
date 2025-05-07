package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE = 0;
    public static final int NAME = 1;
    public static final int HOURS = 2;
    public static final int MONEY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToLocal = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;
            for (String date : data) {
                String[] newData = date.split(" ");
                LocalDate localDate = LocalDate.parse(newData[DATE], FORMATTER);
                String nameData = newData[NAME];
                if (name.equals(nameData)
                        && (localDate.isEqual(dateFromLocal) || localDate.isAfter(dateFromLocal))
                        && (localDate.isEqual(dateToLocal) || localDate.isBefore(dateToLocal))) {
                    salary = salary + Integer.parseInt((newData[HOURS].trim()))
                            * Integer.parseInt(newData[MONEY].trim());
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}

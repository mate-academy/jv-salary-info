package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int salary = 0;
            for (String date : data) {
                String[] newData = date.split(" ");
                LocalDate localDate = LocalDate.parse(newData[0], formatter);
                String nameData = newData[1];
                if (name.equals(nameData)
                        && (localDate.isEqual(dateFromLocal) || localDate.isAfter(dateFromLocal))
                        && (localDate.isEqual(dateToLocal) || localDate.isBefore(dateToLocal))) {
                    salary = salary + Integer.parseInt((newData[2].trim()))
                            * Integer.parseInt(newData[3].trim());
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}

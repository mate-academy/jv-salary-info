package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        int counter = 0;
        for (String name : names) {
            counter++;
            int salary = 0;
            for (String d : data) {
                String[] arrayFromData = d.split(" ");
                LocalDate dateFromArray = LocalDate.parse(arrayFromData[0], dateTimeFormatter);
                String nameOfEmployee = arrayFromData[1];
                int workingHours = Integer.parseInt(arrayFromData[2]);
                int incomePerHour = Integer.parseInt(arrayFromData[3]);
                if (name.equals(nameOfEmployee) && ((dateFromArray.isAfter(dateFromLocalDate)
                        || dateFromArray.equals(dateToLocalDate))
                        && (dateFromArray.isBefore(dateToLocalDate)
                        || dateFromArray.equals(dateToLocalDate)))) {
                    salary += workingHours * incomePerHour;
                }
            }
            builder.append(name).append(" - ").append(salary);
            if (counter < names.length) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}

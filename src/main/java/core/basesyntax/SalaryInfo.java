package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate calendarFrom = convertDataToLocalDate(dateFrom, DATE_TIME_FORMATTER);
        LocalDate calendarTo = convertDataToLocalDate(dateTo, DATE_TIME_FORMATTER);
        StringBuilder employeeSalary = new StringBuilder();
        employeeSalary.append("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;
            for (String eachLine : data) {
                String[] splitedData = eachLine.split(" ");
                if (splitedData[1].equals(name)) {
                    LocalDate currentDate =
                            convertDataToLocalDate(splitedData[0], DATE_TIME_FORMATTER);
                    if ((currentDate.isAfter(calendarFrom)
                            || currentDate.isEqual(calendarFrom))
                            && (currentDate.isBefore(calendarTo)
                            || currentDate.isEqual(calendarTo))) {
                        salary += Integer.valueOf(splitedData[2])
                                * Integer.valueOf(splitedData[3]);
                    }
                }
            }
            employeeSalary.append("\n" + name + " - " + salary);
        }
        return employeeSalary.toString();
    }

    private LocalDate convertDataToLocalDate(String date, DateTimeFormatter dateTimeFormatter) {
        return LocalDate.parse(date, dateTimeFormatter);
    }
}


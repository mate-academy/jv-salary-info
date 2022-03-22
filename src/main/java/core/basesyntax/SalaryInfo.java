package core.basesyntax;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
//God help me
public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String[] array;
        int salary = 0;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (String name : names) {

            for (String datum : data) {
                array = datum.split(" ");
                if (getDateFromString(dateFrom, format).isAfter(getDateFromString(array[0], format)) ||
                        getDateFromString(dateTo, format).isBefore(getDateFromString(array[0], format))) {
                    continue;
                }
                if (array[1].equals(name)) {
                    salary = salary + Integer.parseInt(array[2]) * Integer.parseInt(array[3]);
                }
            }

            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }
    public static LocalDate getDateFromString(String string, DateTimeFormatter format)
    {
        return LocalDate.parse(string, format);
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        String result = new String();
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int salary = 0;
            for (String line: data) {
                String[] array = line.split(" ");
                LocalDate localDate = LocalDate.parse(array[0], formatter);
                if ((localDate.isAfter(localDateFrom) || localDate.equals(localDateFrom))
                        && (localDate.isBefore(localDateTo)
                        || localDate.equals(localDateTo)) && array[1].equals(name)) {
                    salary += Integer.parseInt(array[2])
                            * Integer.parseInt(array[3]);

                }
            }
            result = report.append("\r\n").append(name).append(" - ").append(salary).toString();
        }
        return result;
    }
}

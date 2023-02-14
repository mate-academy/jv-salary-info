package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder();
        result.append("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] recordArray = record.split(" ");
                if (name.equals(recordArray[1])
                        && isBetweenDate(recordArray[0], startDate, endDate)) {
                    salary += Integer.parseInt(recordArray[2]) * Integer.parseInt(recordArray[3]);
                }
            }
            result.append(System.lineSeparator() + name + " - " + salary);
        }
        return result.toString();
    }

    private boolean isBetweenDate(String date, LocalDate startDate, LocalDate endDate) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        return startDate.compareTo(localDate) <= 0 && endDate.compareTo(localDate) >= 0;
    }
}

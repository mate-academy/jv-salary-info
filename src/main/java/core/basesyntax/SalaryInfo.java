package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = parseDate(dateFrom);
        LocalDate toDate = parseDate(dateTo);
        var result = new StringBuilder();
        int[] salariesArr = new int[names.length];

        for (String person : data) {
            String[] personData = person.split(" ");
            LocalDate dataDate = parseDate(personData[0]);
            int salary = Integer.parseInt(personData[2]) * Integer.parseInt(personData[3]);
            if (dataDate.compareTo(fromDate) >= 0
                    && dataDate.compareTo(toDate) <= 0) {
                salariesArr[Arrays.asList(names).indexOf(personData[1])] += salary;
            }
        }

        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salariesArr[i]);
        }

        return result.toString();
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}

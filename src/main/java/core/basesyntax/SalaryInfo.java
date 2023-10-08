package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] personData;
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        int[] salary = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            personData = data[i].split(" ");
            LocalDate date = LocalDate.parse(personData[0], formatter);

            if (localDateFrom.compareTo(date) <= 0 && localDateTo.compareTo(date) >= 0) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(personData[1])) {
                        salary[j] += Integer.parseInt(personData[2])
                                * Integer.parseInt(personData[3]);
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append("\r\n").append(names[i]).append(" - ").append(salary[i]);
        }
        return result.toString();
    }
}

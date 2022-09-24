package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        int[] salary = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] datas = data[i].split(" ");
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(datas[1])
                        && (LocalDate.parse(datas[0], FORMATTER).isAfter(startDate)
                        && LocalDate.parse(datas[0], FORMATTER).isBefore(endDate))) {
                    salary[j] = (Integer.parseInt(datas[2]) * Integer.parseInt(datas[3]))
                            + salary[j];
                }
            }
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator()
                + names[0] + " - " + salary[0]
                + System.lineSeparator()
                + names[1] + " - " + salary[1]
                + System.lineSeparator()
                + names[2] + " - " + salary[2];
    }
}

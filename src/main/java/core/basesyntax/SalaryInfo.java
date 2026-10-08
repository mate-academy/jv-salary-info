package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder res = getInfo(names, data, fromDate, toDate);
        return res.toString();
    }

    private StringBuilder getInfo(String[] names, String[] data, LocalDate
            fromDate, LocalDate toDate) {
        StringBuilder res = new StringBuilder();
        res.append("Report for period ")
                .append(fromDate.format(formatter))
                .append(" - ")
                .append(toDate.format(formatter));

        int[] salaries = new int[names.length];

        for (String dataStr : data) {
            String[] dataSplit = dataStr.split(" ");
            LocalDate current = LocalDate.parse(dataSplit[ZERO_INDEX], formatter);
            String nameSplit = dataSplit[FIRST_INDEX];
            if (!current.isBefore(fromDate) && !current.isAfter(toDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(nameSplit)) {
                        int hours = Integer.parseInt(dataSplit[SECOND_INDEX]);
                        int income = Integer.parseInt(dataSplit[THIRD_INDEX]);
                        salaries[i] += hours * income;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            res.append(System.lineSeparator()).append(names[i]).append(" - ").append(salaries[i]);
        }
        return res;
    }
}

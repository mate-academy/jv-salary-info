package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalaryInfo {
    private static final String RETURN_ROW = "Report for period ";
    private static final String HYPHEN = " - ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        int sum;
        String[] info;
        ArrayList<String[]> sortNames = new ArrayList<>();

        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                info = data[i].split(" ");
                LocalDate actualDate = LocalDate.parse(info[0], formatter);
                LocalDate from = LocalDate.parse(dateFrom, formatter);
                LocalDate endDate = LocalDate.parse(dateTo, formatter);
                if (name.equals(info[1])) {
                    if ((actualDate.isAfter(from.minusDays(1)))
                            && (actualDate.isBefore(endDate.plusDays(1)))) {
                        sum = Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                        String[] str = {name, Integer.toString(sum)};
                        sortNames.add(str);
                    }
                }
            }
            if (sortNames.isEmpty()) {
                StringBuilder emptyResult = new StringBuilder(RETURN_ROW
                        + dateFrom + HYPHEN + dateTo);
                for (String name1 : names) {
                    emptyResult.append(System.lineSeparator())
                            .append(name1).append(HYPHEN).append("0");
                }
                return emptyResult.toString();
            }
        }
        ArrayList<String[]> finalArray = new ArrayList<>();
        for (String[] row : sortNames) {
            boolean isEqualName = false;
            for (String[] rowUnique : finalArray) {
                if (row[0].equals(rowUnique[0])) {
                    isEqualName = true;
                    rowUnique[1] = Integer.toString(Integer.parseInt(rowUnique[1])
                            + Integer.parseInt(row[1]));
                }
            }
            if (!isEqualName) {
                finalArray.add(row);
            }
        }
        StringBuilder resultOfMethod = new StringBuilder(RETURN_ROW + dateFrom + HYPHEN + dateTo);
        for (String[] rows : finalArray) {
            resultOfMethod.append(System.lineSeparator())
                    .append(rows[0]).append(HYPHEN).append(rows[1]);
        }
        return resultOfMethod.toString();
    }
}

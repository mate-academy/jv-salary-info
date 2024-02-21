package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static int endSum = 0;
    private StringBuilder result = new StringBuilder();
    private String endResult = "";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (!endResult.equals("")) {
            endResult = "";
        }
        if (!result.isEmpty()) {
            result.setLength(0);
        }
        String first = "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator();
        result.append(first);
        LocalDate beginningDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endingDate = LocalDate.parse(dateTo, FORMATTER);
        int[] numbers = new int[names.length];
        for (int y = 0; y < names.length; y++) {
            for (int i = 0; i < data.length; i++) {
                LocalDate date = LocalDate.parse(data[i].substring(0,
                        data[i].indexOf(" ")), FORMATTER);
                if (date.compareTo(beginningDate) >= 0 && date.compareTo(endingDate) <= 0
                        && data[i].contains(names[y])) {
                    int index = data[i].indexOf(names[y])
                            + names[y].length() + 1;
                    int hour = Integer.valueOf(data[i].substring(index, data[i].lastIndexOf(" ")));
                    int income = Integer.valueOf(data[i].substring(data[i]
                            .lastIndexOf(" ") + 1, data[i].length()));
                    int sum = hour * income;
                    endSum = endSum + sum;
                }
                if (i == data.length - 1) {
                    numbers[y] = endSum;
                    endSum = 0;
                }
            }
            result.append(names[y]).append(" - ").append(numbers[y])
                    .append(System.lineSeparator());
        }
        String resultToString = result.toString();
        int index = resultToString.lastIndexOf(System.lineSeparator());
        endResult = resultToString.substring(0, index);
        return endResult;
    }
}

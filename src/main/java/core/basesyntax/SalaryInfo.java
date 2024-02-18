package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-MM-yyyy");
    private static int endSum = 0;
    private int hour = 0;
    private int income = 0;
    private int sum = 0;
    private int[] numbers;
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
        dateFrom = dateFrom.replace(".", "-");
        dateTo = dateTo.replace(".", "-");
        LocalDate beginningDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endingDate = LocalDate.parse(dateTo, FORMATTER);
        numbers = new int[names.length];

        for (int i = 0, y = 0; i < data.length && y < names.length; i++) {
            data[i] = data[i].replace(".", "-");
            LocalDate date = LocalDate.parse(data[i].substring(0,
                    data[i].indexOf(" ")), FORMATTER);
            if (date.compareTo(beginningDate) >= 0 && date.compareTo(endingDate) <= 0
                    && data[i].contains(names[y])) {
                int index = data[i].indexOf(names[y])
                        + names[y].length() + 1;
                hour = Integer.valueOf(data[i].substring(index, data[i].lastIndexOf(" ")));
                income = Integer.valueOf(data[i].substring(data[i]
                        .lastIndexOf(" ") + 1, data[i].length()));
                sum = hour * income;
                endSum = endSum + sum;
            }
            if (i == data.length - 1 && y < names.length) {
                numbers[y] = endSum;
                endSum = 0;
                i = 0;
                y++;
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(numbers[i]).append(System.lineSeparator());
        }
        String resultToString = result.toString();
        int index = resultToString.lastIndexOf(System.lineSeparator());
        endResult = resultToString.substring(0, index);
        return endResult;
    }
}

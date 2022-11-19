package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class SalaryInfo {
    private static final int NAME_COUNTER = 1;
    private static final int HOURS_COUNTER = 2;
    private static final int PAY_PER_HOUR_COUNTER = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> resultingData = new HashMap<>();
        for (String info : data) {
            String[] eachRecord = info.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(eachRecord[NAME_COUNTER])) {
                    if (isDateInBetweenIncludingEndPoints(dateFrom, dateTo, eachRecord[0])) {
                        int time = Integer.parseInt(eachRecord[HOURS_COUNTER]);
                        int pay = Integer.parseInt(eachRecord[PAY_PER_HOUR_COUNTER]);
                        if (resultingData.containsKey(names[i])) {
                            resultingData.put(names[i], resultingData.get(names[i]) + (time * pay));
                            break;
                        }
                        resultingData.put(eachRecord[NAME_COUNTER], time * pay);
                    }
                }
            }
        }
        return "Report for period " + dateFrom + " " + " - " + dateTo + System.lineSeparator() +
                createReport(resultingData);
    }

    public boolean isDateInBetweenIncludingEndPoints(String min, String max, String date) {
        LocalDate inputDate = LocalDate.parse(date, formatter);
        LocalDate minDate = LocalDate.parse(min, formatter);
        LocalDate maxDate = LocalDate.parse(max, formatter);
        return !(inputDate.isBefore(minDate) || inputDate.isAfter(maxDate));
    }

    public String createReport(Map<String, Integer> resultingData) {
        StringBuilder builder = new StringBuilder();
        resultingData.forEach((name, pay) -> builder.append(name)
                .append(" ")
                .append(pay)
                .append(System.lineSeparator()));
        return builder.toString();
    }
}

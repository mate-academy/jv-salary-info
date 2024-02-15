package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);
        String[] temp;
        LocalDate dataDate;
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            for (String oneData : data) {
                temp = oneData.split(" ");
                dataDate = LocalDate.parse(temp[0], formatter);
                if (isFulfillConditions(temp, dataDate, fromDate, toDate, names[i])) {
                    sum = sum + Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ").append(sum);
            sum = 0;
            if (i == names.length - 1) {
                break;
            }
        }
        return stringBuilder.toString();
    }

    private boolean isFulfillConditions(String[] temp, LocalDate dataDate,
                                        LocalDate fromDate, LocalDate toDate, String names) {
        return names.equals(temp[1])
                && ((dataDate.isEqual(fromDate)
                || dataDate.isEqual(toDate))
                || dataDate.isAfter(fromDate)
                && dataDate.isBefore(toDate));
    }
}

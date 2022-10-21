package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] tmpStrArr;
        int len = names.length;
        int[] tmpIntArr = new int[len];
        StringBuilder stringBuilder =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        LocalDate convertDate;
        LocalDate convertDateFrom;
        LocalDate convertDateTo;
        try {
            convertDateFrom = LocalDate.parse(dateFrom, formatter);
        } catch (DateTimeParseException e) {
            convertDateFrom = LocalDate.EPOCH;
        }
        try {
            convertDateTo = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            convertDateTo = LocalDate.EPOCH;
        }

        int index = 0;
        for (String name: names) {
            for (String record: data) {
                tmpStrArr = record.split(" ");
                if (tmpStrArr[1].equals(name)) {
                    try {
                        convertDate = LocalDate.parse(tmpStrArr[0], formatter);
                    } catch (DateTimeParseException e) {
                        convertDate = LocalDate.EPOCH;
                    }
                    if (convertDate.isAfter(convertDateFrom) && convertDate.isBefore(convertDateTo)
                            || convertDate.isEqual(convertDateFrom)
                            || convertDate.isEqual(convertDateTo)) {
                        try {
                            tmpIntArr[index] +=
                                    Integer.parseInt(tmpStrArr[2]) * Integer.parseInt(tmpStrArr[3]);
                        } catch (NumberFormatException e) {
                            tmpIntArr[index] += 0;
                        }
                    }
                }
            }
            ++index;
        }

        for (int i = 0; i < len; ++i) {
            stringBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(tmpIntArr[i]);
        }
        return stringBuilder.toString();
    }
}

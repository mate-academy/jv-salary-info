package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        LocalDate tempDate;
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int nameSum = 0;
            for (String info : data) {
                String[] tempInfo = info.split(" ");
                tempDate = LocalDate.parse(tempInfo[DATE_INDEX], FORMATTER);
                if (tempInfo[NAME_INDEX].equals(name)
                        && (tempDate.equals(from) || tempDate.isAfter(from))
                        && (tempDate.equals(to) || tempDate.isBefore(to))) {
                    nameSum += Integer.parseInt(tempInfo[HOURS_INDEX])
                            * Integer.parseInt(tempInfo[RATE_INDEX]);
                }
            }
            stringBuilder.append(nameSum);
        }
        return stringBuilder.toString();
    }
}

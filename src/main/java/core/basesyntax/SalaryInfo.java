package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        LocalDate tempDate;
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int nameSum = ZERO;
            for (String info : data) {
                String[] tempInfo = info.split(" ");
                tempDate = LocalDate.parse(tempInfo[ZERO], FORMATTER);
                if (tempInfo[ONE].equals(name)
                        && (tempDate.equals(from) || tempDate.isAfter(from))
                        && (tempDate.equals(to) || tempDate.isBefore(to))) {
                    nameSum += Integer.parseInt(tempInfo[TWO])
                            * Integer.parseInt(tempInfo[THREE]);
                }
            }
            stringBuilder.append(nameSum);
        }
        return stringBuilder.toString();
    }
}

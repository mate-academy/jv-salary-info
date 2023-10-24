package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo
                + System.lineSeparator());
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ");
            int nameSum = 0;
            for (String info : data) {
                String[] tempInfo = info.split(" ");
                LocalDate tempDate = LocalDate.parse(tempInfo[0], FORMATTER);
                if (tempInfo[1].equals(names[i])
                        && (tempDate.equals(from) || tempDate.isAfter(from))
                        && (tempDate.equals(to) || tempDate.isBefore(to))) {
                    nameSum += Integer.parseInt(tempInfo[2])
                            * Integer.parseInt(tempInfo[3]);
                }
            }
            stringBuilder.append(nameSum);
            if (i != names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}

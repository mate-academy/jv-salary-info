package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo))
                .append(System.lineSeparator());
        int[] salaryInfo = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String checkedData : data) {
                String[] info = checkedData.split(" ");
                if (names[i].equals(info[1])) {
                    try {
                        LocalDate dateAfter = LocalDate.parse(dateFrom, formatter);
                        LocalDate dateBefore = LocalDate.parse(dateTo, formatter);
                        LocalDate date = LocalDate.parse(info[0], formatter);
                        if (date.compareTo(dateAfter) >= 0
                                && date.compareTo(dateBefore) <= 0) {
                            int hour = Integer.parseInt(info[2]);
                            int income = Integer.parseInt(info[3]);
                            salaryInfo[i] += hour * income;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Date is not parsable");
                        throw e;
                    }
                }
            }
            stringBuilder.append(names[i])
                    .append(" - ")
                    .append(salaryInfo[i]);
            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}

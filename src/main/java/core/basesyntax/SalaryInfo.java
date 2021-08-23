package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws RuntimeException {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        String separator = System.lineSeparator();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(separator);
        int[] salaryArr = new int[names.length];
        for (int name = 0; name < names.length; name++) {
            for (int record = 0; record < data.length; record++) {
                if (data[record].contains(names[name])) {
                    String [] lineOfData = data[record].split(" ");
                    LocalDate day = LocalDate.parse(lineOfData[0], FORMATTER);;
                    if (!day.isBefore(fromDate) && !day.isAfter(toDate)) {
                        String[] dataLineArr = data[record].split(" ");
                        salaryArr[name] = salaryArr[name] + Integer.parseInt(dataLineArr[2])
                                * Integer.parseInt(dataLineArr[3]);
                    }
                }
            }
            if (name == names.length - 1) {
                stringBuilder.append(names[name]).append(" - ").append(salaryArr[name]);
            } else {
                stringBuilder.append(names[name]).append(" - ")
                        .append(salaryArr[name]).append(separator);
            }
        }
        String result = stringBuilder.toString();
        return result;
    }
}

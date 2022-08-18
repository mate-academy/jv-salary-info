package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] resultSalary = new int[names.length];
        StringBuilder result = new StringBuilder();
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (String record : data) {
                String[] valuesData = record.split(" ");
                boolean isNull = false;
                for (String value : valuesData) {
                    if (value == null) {
                        isNull = true;
                        break;
                    }
                }
                if (isNull) {
                    continue;
                }
                LocalDate currentDate = LocalDate.parse(valuesData[0], FORMATTER);
                if (names[i].equals(valuesData[1])
                        && !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate)) {
                    resultSalary[i] += Integer.parseInt(valuesData[2])
                            * Integer.parseInt(valuesData[3]);
                }
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(resultSalary[i]);
        }
        return result.toString();
    }
}

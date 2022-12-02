package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salarySum = calculateSalary(names, data, fromDate, toDate);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(fromDate.format(FORMATTER)).append(" - ")
                .append(toDate.format(FORMATTER)).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ").append(salarySum[i]);
            if (i != names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    private int[] calculateSalary(
            String[] names, String[] data, LocalDate fromDate, LocalDate toDate) {
        int[] salarySum = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] strings = data[j].split(" ");
                if (strings[1].equals(names[i])
                        && (LocalDate.parse(strings[0], FORMATTER).isAfter(fromDate)
                        || LocalDate.parse(strings[0], FORMATTER).equals(fromDate))
                        && (LocalDate.parse(strings[0], FORMATTER).isBefore(toDate)
                        || LocalDate.parse(strings[0], FORMATTER).equals(toDate))) {
                    salarySum[i] += (Integer.parseInt(strings[2])
                            * Integer.parseInt(strings[3]));
                }
            }
        }
        return salarySum;
    }
}

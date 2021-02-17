package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static final String DATE_PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        formatter.format(parseDateFrom);
        LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);
        formatter.format(parseDateTo);
        int[] saleryArray = new int[3];
        StringBuilder builder = new StringBuilder();
        for (String datum : data) {
            String dateStringValue = datum.substring(0, DATE_PATTERN.length());
            LocalDate salaryDate = LocalDate.parse(dateStringValue, formatter);
            if (salaryDate.isAfter(parseDateFrom) && (salaryDate.isBefore(parseDateTo))
                    || (salaryDate.isEqual(parseDateTo))) {
                String[] substringSalaryArray = datum.substring(DATE_PATTERN.length() + 1)
                        .split(" ");
                String[] namesAndSaleryArray = new String[substringSalaryArray.length - 1];
                namesAndSaleryArray[0] = substringSalaryArray[0];
                namesAndSaleryArray[1] = Integer.toString(
                        Integer.parseInt(substringSalaryArray[1])
                                * Integer.parseInt(substringSalaryArray[2]));

                if (substringSalaryArray[0].equals(names[0])) {
                    saleryArray[0] += Integer.parseInt(namesAndSaleryArray[1]);
                } else if (substringSalaryArray[0].equals(names[1])) {
                    saleryArray[1] += Integer.parseInt(namesAndSaleryArray[1]);
                } else if (substringSalaryArray[0].equals(names[2])) {
                    saleryArray[2] += Integer.parseInt(namesAndSaleryArray[1]);
                }
            }
        }
        return (builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n").append(names[0]).append(" - ").append(saleryArray[0])
                .append("\n").append(names[1]).append(" - ").append(saleryArray[1])
                .append("\n").append(names[2]).append(" - ").append(saleryArray[2]).toString());

    }
}

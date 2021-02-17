package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);
        int[] salaryArray = new int[3];
        StringBuilder builder = new StringBuilder();
        for (String datum : data) {
            String dateStringValue = datum.substring(0, DATE_PATTERN.length());
            LocalDate salaryDate = LocalDate.parse(dateStringValue, formatter);
            if (salaryDate.isAfter(parseDateFrom) && (salaryDate.isBefore(parseDateTo))
                    || (salaryDate.isEqual(parseDateTo))) {
                String[] substringSalaryArray = datum.substring(DATE_PATTERN.length() + 1)
                        .split(" ");
                String[] namesAndSalaryArray = new String[substringSalaryArray.length - 1];
                namesAndSalaryArray[0] = substringSalaryArray[0];
                namesAndSalaryArray[1] = Integer.toString(
                        Integer.parseInt(substringSalaryArray[1])
                                * Integer.parseInt(substringSalaryArray[2]));

                if (substringSalaryArray[0].equals(names[0])) {
                    salaryArray[0] += Integer.parseInt(namesAndSalaryArray[1]);
                } else if (substringSalaryArray[0].equals(names[1])) {
                    salaryArray[1] += Integer.parseInt(namesAndSalaryArray[1]);
                } else if (substringSalaryArray[0].equals(names[2])) {
                    salaryArray[2] += Integer.parseInt(namesAndSalaryArray[1]);
                }
            }
        }
        return (builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n").append(names[0]).append(" - ").append(salaryArray[0])
                .append("\n").append(names[1]).append(" - ").append(salaryArray[1])
                .append("\n").append(names[2]).append(" - ").append(salaryArray[2]).toString());

    }
}

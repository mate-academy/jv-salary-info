package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        String[] dataLineSplit;
        LocalDate dataLineDate;
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            salary[i] = 0;
            for (String dataLine : data) {
                dataLineSplit = dataLine.split(" ");
                dataLineDate = LocalDate.parse(dataLineSplit[0], FORMATTER);
                if ((dataLineDate.isAfter(localDateFrom)
                        || dataLineDate.equals(localDateFrom))
                        && (dataLineDate.isBefore(localDateTo)
                        || dataLineDate.equals(localDateTo))
                        && dataLineSplit[1].equals(names[i])) {
                    salary[i] += Integer.parseInt(dataLineSplit[2])
                            * Integer.parseInt(dataLineSplit[3]);
                }
            }
        }
        StringBuilder builder = new StringBuilder("");
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (i != names.length - 1) {
                builder.append(names[i]).append(" - ").append(salary[i])
                        .append(System.lineSeparator());
            } else {
                builder.append(names[i]).append(" - ").append(salary[i]);
            }
        }
        return builder.toString();
    }
}

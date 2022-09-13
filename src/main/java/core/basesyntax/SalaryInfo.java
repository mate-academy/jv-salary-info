package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static String getSalaryInfo
             (String[] names, String[] data, String dateFrom, String dateTo) {

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate to = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            long salary = 0;
            for (String line : data) {
                String[] lineInfo = line.split(" ");
                LocalDate workingDate = LocalDate.parse(lineInfo[0], dateTimeFormatter);
                String employeeName = lineInfo[1];
                int rate = Integer.parseInt(lineInfo[2]);
                long money = Integer.parseInt(lineInfo[3]);
                if (name.equals(employeeName)) {
                    if ((workingDate.isAfter(from) || workingDate.equals(from))
                            && (workingDate.isBefore(to) || workingDate.equals(to))) {
                        salary += rate * money;
                    }
                }
            }
            builder.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}

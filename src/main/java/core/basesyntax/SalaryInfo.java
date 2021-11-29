package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            return "";
        }
        StringBuilder report = new StringBuilder();

        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            report.append(names[i]).append(" - ");
            for (int j = 0; j < data.length; j++) {
                String [] result = data[j].split(" ");
                LocalDate start = LocalDate.parse(dateFrom, dateTimeFormatter);
                LocalDate end = LocalDate.parse(dateTo, dateTimeFormatter);
                LocalDate dateOfArray =
                        LocalDate.parse(result[0], dateTimeFormatter);
                if ((dateOfArray.isAfter(start) || start.equals(dateOfArray))
                            && (dateOfArray.isBefore(end) || end.equals(dateOfArray))) {
                    if (names[i].equals(result[1])) {
                        sum = sum + (Integer.parseInt(result[2]))
                                    * (Integer.parseInt(result[3]));
                    }
                }
            }
            report.append(sum).append(System.lineSeparator());
        }
        report.setLength(report.length() - 2);
        System.out.println(report);
        return report.toString();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private LocalDate localDate;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(names[i]).append(" - ");
            int sum = 0;
            for (String dates : data) {
                localDate = LocalDate.parse(dates.substring(0, 10), dateTimeFormatter);
                if (isCalculate(localDate, dateFrom, dateTo)) {
                    String[] strings = dates.split(" ");
                    if (names[i].equals(strings[1])) {
                        sum += Integer.parseInt(strings[2]) * Integer.parseInt(strings[3]);
                    }
                }
            }
            if (i == names.length - 1) {
                salaryInfo.append(sum);
                continue;
            }
            salaryInfo.append(sum).append("\n");
        }
        return "Report for period " + dateFrom + " - " + dateTo + "\n" + salaryInfo;
    }

    private boolean isCalculate(LocalDate localDate, String dateFrom, String dateTo) {
        return (localDate.isEqual(LocalDate.parse(dateFrom, dateTimeFormatter))
                || localDate.isEqual(LocalDate.parse(dateTo, dateTimeFormatter))
                || localDate.isAfter(LocalDate.parse(dateFrom, dateTimeFormatter))
                && localDate.isBefore(LocalDate.parse(dateTo, dateTimeFormatter)));
    }
}

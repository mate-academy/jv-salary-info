package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATA_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            for (String rowData : data) {
                String[] strings = rowData.split(" ");
                LocalDate localDate = LocalDate.parse(strings[0], DATA_TIME_FORMATTER);
                if (isCalculate(localDate, dateFrom, dateTo) && names[i].equals(strings[1])) {
                    sum += Integer.parseInt(strings[2]) * Integer.parseInt(strings[3]);
                }
            }
            salaryInfo.append("\n").append(names[i]).append(" - ").append(sum);
        }
        return salaryInfo.toString();
    }

    private boolean isCalculate(LocalDate localDate, String dateFrom, String dateTo) {
        return (localDate.isEqual(LocalDate.parse(dateFrom, DATA_TIME_FORMATTER))
                || localDate.isEqual(LocalDate.parse(dateTo, DATA_TIME_FORMATTER))
                || localDate.isAfter(LocalDate.parse(dateFrom, DATA_TIME_FORMATTER))
                && localDate.isBefore(LocalDate.parse(dateTo, DATA_TIME_FORMATTER)));
    }
}

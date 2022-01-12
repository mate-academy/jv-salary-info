package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int salaryDay;
        for (String name : names) {
            salaryDay = 0;
            for (String dataValue : data) {
                String[] dataSplit = dataValue.split(" ");
                boolean cd1 = compareDates(dataSplit[0], dateFrom) >= 0;
                boolean cd2 = compareDates(dataSplit[0], dateTo) <= 0;
                if (cd1 && cd2 && dataSplit[1].equals(name)) {
                    int hour = Integer.parseInt(dataSplit[2]);
                    int income = Integer.parseInt(dataSplit[3]);
                    salaryDay = salaryDay + hour * income;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salaryDay);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }

    public int compareDates(String date1, String date2) {
        return LocalDate.parse(date1, FORMATTER).compareTo(LocalDate.parse(date2, FORMATTER));
    }
}

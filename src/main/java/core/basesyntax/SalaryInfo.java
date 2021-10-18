package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        double[] salary = new double[names.length];
        LocalDate dateFromLD = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLD = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            for (String oneDayData : data) {
                String[] oneDayDataArr = oneDayData.split(" ");
                LocalDate date = LocalDate.parse(oneDayDataArr[0], formatter);
                if ((date.isEqual(dateFromLD) || date.isAfter(dateFromLD))
                        && (date.isEqual(dateToLD) || date.isBefore(dateToLD))
                        && oneDayDataArr[1].equals(names[i])) {
                    salary[i] += Double.parseDouble(oneDayDataArr[2])
                        * Double.parseDouble(oneDayDataArr[3]);
                }
            }
            report.append(names[i]).append(" - ").append((int) salary[i]);
            if (i != names.length - 1) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }
}

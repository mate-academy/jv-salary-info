package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        double[] salary = new double[names.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        LocalDate dateFromLD = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLD = LocalDate.parse(dateTo, formatter);
        for (int i = 0; i < names.length; i++) {
            for (String oneDayData : data) {
                String[] oneDayDataArr = oneDayData.split(" ");
                LocalDate date = LocalDate.parse(oneDayDataArr[0], formatter);
                if ((date.isEqual(dateFromLD) || date.isAfter(dateFromLD))
                        && (date.isEqual(dateToLD) || date.isBefore(dateToLD))) {
                    if (oneDayDataArr[1].equals(names[i])) {
                        salary[i] += Double.parseDouble(oneDayDataArr[2])
                                    * Double.parseDouble(oneDayDataArr[3]);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int j = 0; j < names.length; j++) {
            sb.append(names[j]).append(" - ").append((int) salary[j]);
            if (j != names.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}

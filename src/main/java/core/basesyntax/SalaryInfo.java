package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period ");
        sb.append(dateFrom).append(" - ").append(dateTo).append("\n");
        LocalDate tempDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate tempDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalIncome = 0;
            for (String dataInfo : data) {
                String[] temp = dataInfo.split(" ");
                LocalDate tempDate = LocalDate.parse(temp[0], FORMATTER);
                if (dataInfo.contains(name)
                        && (tempDate.isAfter(tempDateFrom) || tempDate.isEqual(tempDateFrom))
                        && (tempDate.isBefore(tempDateTo) || tempDate.isEqual(tempDateTo))) {
                    totalIncome += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
                }
            }
            sb.append(name).append(" - ").append(totalIncome).append("\n");
        }
        System.out.println(sb.toString());
        return sb.toString().trim();
    }
}

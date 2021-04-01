package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateBeforeStart = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate dateAfterEnd = LocalDate.parse(dateTo, formatter).plusDays(1);
        int[] salary = new int[names.length];
        for (String lineOfData : data) {
            String[] lineArray = lineOfData.split(" ");
            LocalDate date = LocalDate.parse(lineArray[0], formatter);
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(lineArray[1])
                        && date.isAfter(dateBeforeStart)
                        && date.isBefore(dateAfterEnd)) {
                    salary[i] += Integer.parseInt(lineArray[2]) * Integer.parseInt(lineArray[3]);
                }
            }
        }
        StringBuilder salaryInfo = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(names[i])
                    .append(" - ")
                    .append(salary[i])
                    .append("\n");
        }
        
        return salaryInfo.toString().trim();
    }
}

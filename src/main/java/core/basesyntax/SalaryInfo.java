package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int WORKER = 1;
    private static final int HOURS = 2;
    private static final int MONEY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate beginPeriod = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endPeriod = LocalDate.parse(dateTo, FORMATTER);
        report.append("Report for period ")
                    .append(dateFrom).append(" - ")
                    .append(dateTo)
                    .append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] partsOfData = datum.split("\\s");
                LocalDate currentPeriod = LocalDate.parse(partsOfData[DATE], FORMATTER);
                boolean isInPeriod = !currentPeriod.isAfter(endPeriod)
                            && !currentPeriod.isBefore(beginPeriod);
                if (partsOfData[WORKER].equals(name) && isInPeriod) {
                    salary += (Integer.parseInt(partsOfData[HOURS])
                                * Integer.parseInt(partsOfData[MONEY]));
                }
            }
            report.append(name)
                        .append(" - ")
                        .append(salary)
                        .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}

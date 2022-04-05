package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int NAME_INDEX_1 = 1;
    private static final int DATE_INDEX_2 = 2;
    private static final int PRISE_INDEX_3 = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder reportBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] splitterLine;
        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                splitterLine = line.split(" ");
                LocalDate date = LocalDate.parse(splitterLine[0],FORMATTER);
                if ((localDateFrom.isBefore(date) || localDateFrom.isEqual(date))
                        && (localDateTo.isAfter(date) || localDateTo.isEqual(date))
                        && splitterLine[NAME_INDEX_1].equals(name)) {
                    salary += (Integer.parseInt(splitterLine[PRISE_INDEX_3])
                            * Integer.parseInt(splitterLine[DATE_INDEX_2]));
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}

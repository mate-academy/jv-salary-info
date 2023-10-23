package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String lines : data) {
                String[] line = lines.split(" ");
                if (name.equals(line[NAME_INDEX])) {
                    LocalDate date = LocalDate.parse(line[DATE_INDEX],FORMATTER);
                    if (date.compareTo(from) >= 0 && date.compareTo(to) <= 0) {
                        salary += Integer.parseInt(line[HOURS_INDEX])
                                * Integer.parseInt(line[SALARY_INDEX]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name). append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}

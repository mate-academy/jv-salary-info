package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int DAY_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        LocalDate formatDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate formatDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] temporary = datum.split(" ");
                LocalDate dateFromRow = LocalDate.parse(temporary[DATE_INDEX], formatter);
                if (name.equals(temporary[NAME_INDEX])) {
                    if (dateFromRow.isAfter(formatDateFrom)
                            && dateFromRow.isBefore(formatDateTo)
                            || dateFromRow.isEqual(formatDateFrom)
                            || dateFromRow.isEqual(formatDateTo)) {
                        totalSalary += Integer.parseInt(temporary[DAY_INDEX])
                                * Integer.parseInt(temporary[SALARY_INDEX]);
                    }
                }
            }
            report.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}

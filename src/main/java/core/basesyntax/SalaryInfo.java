package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DELIMITER = " ";
    private static final int WORKDAY = 0;
    private static final int NAMEOFEMPLOYEE = 1;
    private static final int WORKINGHOUR = 2;
    private static final int INCOMEPERHOUSE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        int salary = 0;
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            for (String dates : data) {
                String[] splitData = dates.split(DELIMITER);
                LocalDate dataWork = LocalDate.parse(splitData[WORKDAY], formatter);
                if (name.equals(splitData[NAMEOFEMPLOYEE])
                        && ((from.minusDays(1).isBefore(dataWork)
                        && dataWork.isBefore(to.plusDays(1))))) {
                    salary = salary
                            + Integer.parseInt(splitData[WORKINGHOUR])
                            * Integer.parseInt(splitData[INCOMEPERHOUSE]);
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
            salary = 0;
        }
        return report.toString();
    }
}

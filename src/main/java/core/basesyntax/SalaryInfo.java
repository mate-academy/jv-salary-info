package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int WHICH_DATE = 0;
    private static final int HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salaryCounter = 0;
            for (String information : data) {
                if (information.contains(name)) {
                    String[] splitterDate = information.split(" ");
                    LocalDate currentDate = LocalDate.parse(splitterDate[WHICH_DATE], FORMATTER);
                    if ((currentDate.isEqual(fromDate) || currentDate.isAfter(fromDate))
                            && (currentDate.isEqual(toDate) || currentDate.isBefore(toDate))) {
                        salaryCounter += (Integer.parseInt(splitterDate[HOURS])
                                * Integer.parseInt(splitterDate[SALARY_PER_HOUR]));
                    }
                }
            }
            result.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(salaryCounter);
        }
        return result.toString();
    }
}

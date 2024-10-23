package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder summaryReport = new StringBuilder("Report for period ");
        summaryReport
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);
        for (int i = 0; i < names.length; i++) {
            summaryReport.append("\n");
            int salaryInfo = 0;
            for (int j = 0; j < data.length; j++) {
                String[] tempDataDetails = data[j].split(" ");
                LocalDate employeeDate = parseDate(tempDataDetails[DATE_INDEX]);
                if (names[i].equals(tempDataDetails[NAME_INDEX])) {
                    if (employeeDate.isAfter(startDate)
                            && (employeeDate.isBefore(endDate)
                            || employeeDate.isEqual(endDate))) {
                        salaryInfo += Integer.parseInt(tempDataDetails[HOUR_INDEX])
                                * Integer.parseInt(tempDataDetails[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
            summaryReport
                    .append(names[i])
                    .append(" - ")
                    .append(salaryInfo);
        }
        return summaryReport.toString();
    }

    private static LocalDate parseDate(String dateFrom) {
        return LocalDate.parse(dateFrom, FORMATTER);
    }
}

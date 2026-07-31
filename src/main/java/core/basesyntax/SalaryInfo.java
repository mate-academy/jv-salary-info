package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        private static final String REGEX = "\\s+";
        private static final int INDEX_DATE = 0;
        private static final int INDEX_NAME = 1;
        private static final int INDEX_WORKING_HOUR = 2;
        private static final int INDEX_SALARY = 3;
        private static final String DATE_FORMAT = "dd.MM.yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate salaryDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate salaryDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int sumSalary = 0;
            for (String datum : data) {
                String[] parsDatum = datum.split(REGEX);
                LocalDate currentDate = LocalDate.parse(parsDatum[INDEX_DATE], dateTimeFormatter);
                if (!currentDate.isBefore(salaryDateFrom) && !currentDate.isAfter(salaryDateTo)
                        && parsDatum[INDEX_NAME].equals(name)) {
                    sumSalary += Integer.parseInt(parsDatum[INDEX_WORKING_HOUR])
                            * Integer.parseInt(parsDatum[INDEX_SALARY]);
                }
            }
            if (sumSalary >= 0) {
                stringBuilder.append(System.lineSeparator())
                        .append(name).append(" - ")
                        .append(sumSalary);
            }
        }
        return stringBuilder.toString();
    }
}

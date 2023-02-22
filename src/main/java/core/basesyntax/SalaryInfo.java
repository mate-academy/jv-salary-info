package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_LENGTH = 3;
    private static final int YEAR_ARRAY_INDEX = 0;
    private static final int WORK_HOURS_ARRAY_INDEX = 2;
    private static final int SALARY_PERHOUR_ARRAY_INDEX = 3;
    private static final int EQUAL_YEAR_CONDITION = 0;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate date1 = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate date2 = LocalDate.parse(dateTo, FORMATTER);

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (int k = 0; k < data.length; k++) {
                if (data[k].contains(names[i])) {
                    String[] separateData = data[k].split(" ");
                    LocalDate date = LocalDate.parse(separateData[YEAR_ARRAY_INDEX], FORMATTER);
                    if (date.compareTo(date1) >= EQUAL_YEAR_CONDITION
                            && date.compareTo(date2) <= EQUAL_YEAR_CONDITION) {
                        totalSalary += Integer.parseInt(separateData[WORK_HOURS_ARRAY_INDEX])
                                * Integer.parseInt(separateData[SALARY_PERHOUR_ARRAY_INDEX]);
                    }
                }
            }
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(totalSalary);
        }
        return result.toString();

    }
}

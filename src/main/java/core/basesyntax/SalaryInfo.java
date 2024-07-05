package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SALARY_INFO_SEPARATOR = " - ";
    private static final String DATA_SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOURS_INDEX = 3;
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);

        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(SALARY_INFO_SEPARATOR)
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            report.append(System.lineSeparator())
                    .append(name)
                    .append(SALARY_INFO_SEPARATOR);

            for (String currentData : data) {
                String[] splitData = currentData.split(DATA_SEPARATOR);
                LocalDate date = parseDate(splitData[DATE_INDEX]);

                if ((date.equals(startDate) || date.isAfter(startDate))
                        && (date.equals(endDate) || date.isBefore(endDate))
                        && name.equals(splitData[NAME_INDEX])) {
                    salary += Integer.parseInt(splitData[WORK_HOURS_INDEX])
                            * Integer.parseInt(splitData[SALARY_PER_HOURS_INDEX]);
                }
            }
            report.append(salary);
        }

        return report.toString();
    }

    private LocalDate parseDate(String data) {
        return LocalDate.parse(data, formatter);
    }
}

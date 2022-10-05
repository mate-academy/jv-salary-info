package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_FOR_PERIOD = "Report for period ";
    private static final String DATE_TIME_SEPARATOR = " - ";
    private static final int DATE_OF_START_WORK_INDEX = 0;
    private static final int WORKER_NAME_INDEX = 1;
    private static final int WORKDAY_INDEX = 2;
    private static final int WORKING_HOURS_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMAT);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REPORT_FOR_PERIOD)
                .append(dateFrom)
                .append(DATE_TIME_SEPARATOR)
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] info = record.split(" ");
                LocalDate parsedDate = LocalDate.parse(info[DATE_OF_START_WORK_INDEX], DATE_TIME_FORMAT);
                if (info[WORKER_NAME_INDEX].equals(name)
                        && (parsedDate.compareTo(from) >= 0
                        && parsedDate.compareTo(to) <= 0)) {
                    int salaryForDay = Integer.parseInt(info[WORKDAY_INDEX])
                            * Integer.parseInt(info[WORKING_HOURS_INDEX]);
                    salary = salary + salaryForDay;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(DATE_TIME_SEPARATOR)
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}

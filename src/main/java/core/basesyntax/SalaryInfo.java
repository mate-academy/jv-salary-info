package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKING_DATE_NUMBER_INDEX = 0;
    private static final int NAME_NUMBER_INDEX = 1;
    private static final int WORK_HOURS_PER_DAY_NUMBER_INDEX = 2;
    private static final int PRICE_FOR_ONE_WORK_HOUR_INDEX = 3;
    private static final String SPLIT_BY_THIS_STRING = " ";
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        LocalDate workingDate;
        String[] employerInfo;
        for (String name : names) {
            int salary = 0;
            for (String dataString : data) {
                employerInfo = dataString.split(SPLIT_BY_THIS_STRING);
                workingDate = LocalDate.parse(employerInfo[WORKING_DATE_NUMBER_INDEX],
                        dateTimeFormatter);
                if (name.equals(employerInfo[NAME_NUMBER_INDEX])
                        && (workingDate.isAfter(localDateFrom)
                        && workingDate.isBefore(localDateTo)
                        || workingDate.equals(localDateTo)
                        || workingDate.equals(localDateFrom))) {
                    salary += Integer.parseInt(employerInfo[PRICE_FOR_ONE_WORK_HOUR_INDEX])
                            * Integer.parseInt(employerInfo[WORK_HOURS_PER_DAY_NUMBER_INDEX]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}

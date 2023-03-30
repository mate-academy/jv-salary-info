package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKING_DATE_NUMBER = 0;
    private static final int NAME_NUMBER = 1;
    private static final int WORK_HOURS_PER_DAY_NUMBER = 2;
    private static final int PRICE_FOR_ONE_WORK_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        LocalDate workingDate;
        String[] employerInfo;
        for (String name : names) {
            int salary = 0;
            for (String dataString : data) {
                employerInfo = dataString.split(" ");
                workingDate = LocalDate.parse(employerInfo[WORKING_DATE_NUMBER], dateTimeFormatter);
                if (name.equals(employerInfo[NAME_NUMBER])
                        && (workingDate.isAfter(localDateFrom)
                        && workingDate.isBefore(localDateTo)
                        || workingDate.equals(localDateTo)
                        || workingDate.equals(localDateFrom))) {
                    salary += Integer.parseInt(employerInfo[PRICE_FOR_ONE_WORK_HOUR])
                            * Integer.parseInt(employerInfo[WORK_HOURS_PER_DAY_NUMBER]);
                }
            }
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}

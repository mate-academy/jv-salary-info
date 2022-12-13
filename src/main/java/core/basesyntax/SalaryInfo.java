package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateF = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateT = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        String[] dataArray = new String[4];
        int salary;
        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String dataInfo : data) {
                dataArray = dataInfo.split(" ");
                LocalDate workDate = LocalDate.parse(dataArray[DATE_INDEX], dateTimeFormatter);
                if (names[i].equals(dataArray[NAME_INDEX]) && !workDate.isBefore(dateF)
                        && !workDate.isAfter(dateT)) {
                    salary += Integer.valueOf(dataArray[WORKING_HOURS_INDEX])
                            * Integer.valueOf(dataArray[INCOME_PER_HOUR_INDEX]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}

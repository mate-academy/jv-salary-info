package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATE_SYMBOL = " ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (String person : names) {
            int salaryOfPerson = 0;
            for (String record : data) {
                String[] newData = record.split(SEPARATE_SYMBOL);
                LocalDate workDate = LocalDate.parse(newData[WORKING_DAY_INDEX], FORMATTER);
                if (newData[NAME_INDEX].equals(person)
                        && (workDate.isAfter(fromDate) || workDate.equals(fromDate))
                        && (workDate.isBefore(toDate) || workDate.equals(toDate))) {
                    salaryOfPerson += Integer.parseInt(newData[WORKING_HOURS_INDEX])
                            * Integer.parseInt(newData[SALARY_PER_HOUR_INDEX]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(person)
                    .append(" - ")
                    .append(salaryOfPerson);
        }
        return builder.toString();
    }
}

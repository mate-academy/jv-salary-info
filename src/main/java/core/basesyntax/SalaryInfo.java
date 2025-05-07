package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, format);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, format);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int salaryPerPeriod = 0;
            for (String newData : data) {
                String[] seperatedData = newData.split(" ");
                LocalDate dateTime = LocalDate.parse(seperatedData[DATE_INDEX], format);
                if ((dateTime.isAfter(formattedDateFrom) && dateTime.isBefore(formattedDateTo)
                        || dateTime.equals(formattedDateFrom)
                        || dateTime.equals(formattedDateTo))
                        && name.equals(seperatedData[NAME_INDEX])) {
                    int workingHuors = Integer.parseInt(seperatedData[WORKING_HOURS_INDEX]);
                    int salaryPerHour = Integer.parseInt(seperatedData[SALARY_PER_HOUR_INDEX]);
                    salaryPerPeriod = salaryPerPeriod + (salaryPerHour * workingHuors);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryPerPeriod);
        }
        return builder.toString();
    }
}

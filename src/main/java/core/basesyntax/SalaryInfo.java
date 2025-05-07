package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int INDEX_OF_HOUR = 2;
    private static final int SALARY_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salaryForPeriod = 0;
            for (String eachData : data) {
                String[] dataNameSalary = eachData.split(" ");
                final String employerName = dataNameSalary[NAME_INDEX];
                final int hoursOfWork = Integer.parseInt(dataNameSalary[INDEX_OF_HOUR]);
                final int salaryPerHour = Integer.parseInt(dataNameSalary[SALARY_INDEX]);
                LocalDate localDateOfWork = LocalDate.parse(dataNameSalary[DATA_INDEX], formatter);
                if (name.equals(employerName)
                            && (localDateOfWork.isAfter(localDateFrom)
                            && localDateOfWork.isBefore(localDateTo)
                            || localDateOfWork.isEqual(localDateFrom)
                            || localDateOfWork.isEqual(localDateTo))) {
                    salaryForPeriod += hoursOfWork * salaryPerHour;
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryForPeriod);
        }
        return builder.toString();
    }
}

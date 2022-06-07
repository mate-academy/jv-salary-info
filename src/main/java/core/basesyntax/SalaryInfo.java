package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryReport = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedArrayData = line.split(" ");
                LocalDate splittedArrayDate
                        = LocalDate.parse(splittedArrayData[DAY_INDEX], FORMATTER);
                String splittedArrayName = splittedArrayData[NAME_INDEX];
                int workingHour = Integer.parseInt(splittedArrayData[WORKING_HOUR_INDEX]);
                int incomePerHour = Integer.parseInt(splittedArrayData[INCOME_PER_HOUR_INDEX]);
                if (splittedArrayName.equals(name)
                        && (splittedArrayDate.isAfter(localDateFrom)
                        || splittedArrayDate.isEqual(localDateFrom))
                        && (splittedArrayDate.isBefore(localDateTo)
                        || splittedArrayDate.isEqual(localDateTo))) {
                    salary += workingHour * incomePerHour;
                }
            }
            salaryReport.append(LINE_SEPARATOR).append(name).append(" - ").append(salary);
        }
        return salaryReport.toString();
    }
}

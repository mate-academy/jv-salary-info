package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int START_POSITION_FOR_SUBSTRING_DATE = 0;
    private static final int END_POSITION_FOR_SUBSTRING_DATE = 10;
    private static final int START_POSITION_FOR_SUBSTRING_INCOME_DATA = 12;
    private static final int ARRAY_POSITION_OF_WORKING_HOURS = 0;
    private static final int ARRAY_POSITION_OF_INC_PER_HOUR = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String simpleData : data) {
                if (simpleData.contains(name)) {
                    LocalDate rowDate = LocalDate.parse(simpleData.substring(
                                    START_POSITION_FOR_SUBSTRING_DATE,
                                    END_POSITION_FOR_SUBSTRING_DATE),
                            FORMATTER);
                    if (!rowDate.isBefore(localDateFrom) && !rowDate.isAfter(localDateTo)) {
                        String[] dayIncomeInfo = simpleData.substring(
                                START_POSITION_FOR_SUBSTRING_INCOME_DATA
                                        + name.length()).split(" ");
                        salary += Integer.parseInt(dayIncomeInfo[ARRAY_POSITION_OF_WORKING_HOURS])
                                * Integer.parseInt(dayIncomeInfo[ARRAY_POSITION_OF_INC_PER_HOUR]);
                    }
                }
            }
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }
}

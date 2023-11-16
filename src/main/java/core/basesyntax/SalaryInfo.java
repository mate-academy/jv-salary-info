package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final String DATA_DASH = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate deadLineDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(DATA_DASH).append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataRow : data) {
                String[] dataRowArray = dataRow.split(" ");
                if (name.equals(dataRowArray[NAME_INDEX])) {
                    LocalDate rowDate = LocalDate.parse(dataRowArray[DATE_INDEX], FORMATTER);
                    if ((rowDate.isAfter(startDate) && rowDate.isBefore(deadLineDate))
                            || rowDate.equals(startDate)
                            || rowDate.equals(deadLineDate)) {
                        salary += Integer.parseInt(dataRowArray[HOUR_INDEX])
                                * Integer.parseInt(dataRowArray[SALARY_INDEX]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(DATA_DASH).append(salary);
        }

        return builder.toString();
    }
}

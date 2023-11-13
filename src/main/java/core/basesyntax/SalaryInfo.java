package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " - ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateFromStartWork = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFinishWork = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                     .append(dateFrom)
                     .append(SEPARATOR)
                     .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataRow : data) {
                String[] dataRowArray = dataRow.split(" ");
                if (dataRowArray[NAME_INDEX].equals(name)) {
                    LocalDate rowDate = LocalDate.parse(dataRowArray[DATE_INDEX], FORMATTER);
                    if ((rowDate.isBefore(dateToFinishWork) && rowDate.isAfter(dateFromStartWork))
                            || rowDate.equals(dateFromStartWork)
                            || rowDate.equals(dateToFinishWork)) {
                        salary += Integer.parseInt(dataRowArray[HOURS_INDEX])
                            * Integer.parseInt(dataRowArray[INCOME_PER_HOUR_INDEX]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                         .append(name)
                         .append(SEPARATOR)
                         .append(salary);
        }
        return stringBuilder.toString();
    }
}

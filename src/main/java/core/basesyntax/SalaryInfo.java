package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " - ";
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateFromStartWork = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFinishWork = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(SEPARATOR).append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataRow : data) {
                String[] dataRowArray = dataRow.split(" ");
                if (dataRowArray[1].equals(name)) {
                    LocalDate rowDate = LocalDate.parse(dataRowArray[0], FORMATTER);
                    if ((rowDate.isBefore(dateToFinishWork) && rowDate.isAfter(dateFromStartWork))
                        || rowDate.equals(dateFromStartWork) || rowDate.equals(dateToFinishWork)) {
                        salary += Integer.parseInt(dataRowArray[2]) * Integer.parseInt(dataRowArray[3]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(SEPARATOR).append(salary);
        }
        return stringBuilder.toString();
    }
}

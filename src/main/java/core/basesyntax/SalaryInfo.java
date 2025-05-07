package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NAME_INDEX = 1;
    private static final int DATE_INDEX = 0;
    private static final int INDEX_HOURS_WORKED = 2;
    private static final int INDEX_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder salaryIfo = new StringBuilder("Report for period ");
        salaryIfo.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int employeeSalary = 0;
            for (String record : data) {
                String[] parcedRecord = record.split(" ");
                LocalDate date = LocalDate.parse(parcedRecord[DATE_INDEX],FORMATTER);
                if (name.equals(parcedRecord[NAME_INDEX])) {
                    if ((date.isAfter(fromDate) || date.isEqual(fromDate))
                            && (date.isBefore(toDate) || (date.isEqual(toDate)))) {
                        employeeSalary += Integer.parseInt(parcedRecord[INDEX_HOURS_WORKED])
                                * Integer.parseInt(parcedRecord[INDEX_PER_HOUR]);
                    }
                }
            }
            salaryIfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(employeeSalary);
        }
        return salaryIfo.toString();
    }
}

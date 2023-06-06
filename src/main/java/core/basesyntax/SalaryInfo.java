package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, DATE_FORMATTER);
        int salary = 0;
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            for (String component : data) {
                String[] dataComponents = component.split(" ");
                LocalDate dateOfWork = LocalDate.parse(dataComponents[DATE_INDEX], DATE_FORMATTER);
                String nameFromData = dataComponents[NAME_INDEX];
                int workingHour = Integer.parseInt(dataComponents[HOURS_WORKED_INDEX]);
                int income = Integer.parseInt(dataComponents[INCOME_INDEX]);
                if ((dateOfWork.isAfter(dateStart) || dateOfWork.isEqual(dateStart))
                        && (dateOfWork.isBefore(dateFinish) || dateOfWork.isEqual(dateFinish))
                        && (nameFromData.equals(name))) {
                    salary += workingHour * income;
                }
            }
            builder.append(System.lineSeparator());
            builder.append(name)
                    .append(" - ")
                    .append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}

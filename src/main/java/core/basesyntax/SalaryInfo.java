package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int INDEX_DATA = 0;
    static final int INDEX_NAME = 1;
    static final int INDEX_WORK_TIME = 2;
    static final int INDEX_SALARY = 3;
    int salary = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate localFrom = LocalDate.parse(dateFrom, FORMATTER);
        StringBuilder builder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for(int i = 0; i < names.length; i++) {
            for(int k = 0; k < data.length; k++) {
                String[] dayAtWork = data[k].split(" ");
                LocalDate workday = LocalDate.parse(dayAtWork[INDEX_DATA], FORMATTER);
                String name = dayAtWork[INDEX_NAME];

                if ((workday.isAfter(localFrom) || workday.isEqual(localFrom))
                        && (workday.isBefore(localTo) || workday.isEqual(localTo))
                        && name.equals(names[i])) {
                    salary += Integer.parseInt(dayAtWork[INDEX_WORK_TIME])
                            * Integer.parseInt(dayAtWork[INDEX_SALARY]);
                }
            }
            builder.append(names[i])
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());

            salary = 0;
        }
        return builder.toString().trim();
    }
}
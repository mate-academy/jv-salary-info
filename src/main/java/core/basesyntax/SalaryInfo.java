package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATES_LIST = 0;
    private static final int NAMES_LIST = 1;
    private static final int WORKING_HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromFormated = LocalDate.parse(dateFrom,formatter);
        LocalDate dateToFormated = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (int j = 0; j < names.length; j++) {
            String name = names[j];
            int salary = 0;

            for (int i = 0; i < data.length; i++) {
                String[] dataSeparate = data[i].split(" ");
                LocalDate dateFromData = LocalDate.parse(dataSeparate[DATES_LIST], formatter);

                if (name.equals(dataSeparate[NAMES_LIST])
                        && (dateFromData.isBefore(dateToFormated)
                        || dateFromData.isEqual(dateToFormated))
                        && (dateFromData.isAfter(dateFromFormated)
                        || dateFromData.isEqual(dateFromFormated))) {
                    salary += Integer.parseInt(dataSeparate[WORKING_HOURS])
                            * Integer.parseInt(dataSeparate[INCOME_PER_HOUR]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int WORKING_HOURS_POSITION = 2;
    private static final int INCOME_POSITION = 3;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DTF);
        LocalDate to = LocalDate.parse(dateTo, DTF);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataInfo : data) {
                String[] info = dataInfo.split(" ");
                if (name.equals(info[NAME_POSITION])) {
                    LocalDate recordDate = LocalDate.parse(info[DATE_POSITION], DTF);
                    if (!recordDate.isBefore(from) && !recordDate.isAfter(to)) {
                        salary += Integer.parseInt(info[INCOME_POSITION])
                                * Integer.parseInt(info[WORKING_HOURS_POSITION]);
                    }
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return salaryInfo.toString();
    }
}

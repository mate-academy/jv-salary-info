package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int LOCAL_DATE = 0;
    public static final int NAME = 1;
    public static final int HOURS = 2;
    public static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            int salary = 0;

            for (String datas : data) {
                String[] info = datas.split(" ");

                if (name.equals(info[NAME]) && isDateFit(info[LOCAL_DATE], dateFrom, dateTo)) {
                    salary += Integer.parseInt(info[HOURS])
                            * Integer.parseInt(info[SALARY_PER_HOUR]);
                }

            }
            salaryInfo.append(salary);
        }

        return salaryInfo.toString();
    }

    private boolean isDateFit(String localDate, String dateFrom, String dateTo) {
        LocalDate parsedDate = LocalDate.parse(localDate,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate parsedDateTo = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return ((parsedDate.isAfter(parsedDateFrom) || parsedDate.isEqual(parsedDateFrom))
                && (parsedDate.isBefore(parsedDateTo) || parsedDate.isEqual(parsedDateTo)));
    }
}

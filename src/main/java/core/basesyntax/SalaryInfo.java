package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_SALARY_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String title = "Report for period ";
        StringBuilder builder = new StringBuilder();
        builder.append(title).append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateF = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateT = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String arg : data) {
                String[] elements = arg.split(" ");
                LocalDate dataArray = LocalDate.parse(elements[DATE_INDEX], FORMATTER);
                String nameOfEmployee = elements[NAME_INDEX];
                if (!dataArray.isBefore(dateF) && !dataArray.isAfter(dateT)
                        && name.equals(nameOfEmployee)) {
                    int hourOfWork = Integer.parseInt(elements[HOUR_SALARY_INDEX]);
                    int moneyPerWork = Integer.parseInt(elements[SALARY_INDEX]);
                    salary += hourOfWork * moneyPerWork;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}

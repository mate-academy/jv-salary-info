package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int SALARY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String stringDateFrom, String stringDateTo) {
        StringBuilder output = new StringBuilder("Report for period ");
        output.append(stringDateFrom).append(" - ")
                .append(stringDateTo).append(System.lineSeparator());

        LocalDate dateFrom = LocalDate.parse(stringDateFrom, dateFormatter);
        LocalDate dateTo = LocalDate.parse(stringDateTo, dateFormatter);
        for (String name : names) {
            int salary = 0;
            for (String oneData : data) {
                String[] localData = oneData.split(" ");
                LocalDate date = LocalDate.parse(localData[DATE_POSITION], dateFormatter);
                if (localData[NAME_POSITION].equals(name) && date.compareTo(dateFrom) >= 0
                        && date.compareTo(dateTo) <= 0) {
                    salary += Integer.parseInt(localData[HOURS_POSITION])
                            * Integer.parseInt(localData[SALARY_POSITION]);
                }
            }
            output.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}

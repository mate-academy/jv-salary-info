package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATE_POS = 0;
    static final int HOURS_POS = 2;
    static final int SALARY_POS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int tempSalaryForPerson = 0;
            for (String oneDate : data) {
                if (oneDate.contains(name)) {
                    String[] dataForOnePerson = oneDate.split(" ");
                    LocalDate personDay = LocalDate.parse(dataForOnePerson[DATE_POS], FORMATTER);
                    if (personDay.compareTo(localDateFrom) >= 0
                            && personDay.compareTo(localDateTo) <= 0) {
                        tempSalaryForPerson += Integer.parseInt(dataForOnePerson[HOURS_POS])
                                * Integer.parseInt(dataForOnePerson[SALARY_POS]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(tempSalaryForPerson);
        }
        return builder.toString();
    }
}

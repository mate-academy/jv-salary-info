package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, format);
        LocalDate to = LocalDate.parse(dateTo, format);

        StringBuilder a = new StringBuilder();
        a.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (String name : names) {
            a.append(new PersonalSalaryCalc().getPersonalSalary(data, name, from, to) 
                    + System.lineSeparator());
        }
        return a.toString().trim();
    }
}

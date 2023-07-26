package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static int salary;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DATA_INDEX = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
        StringBuilder reportSalary = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo);
        for (String name : names) {
            for (String informPerson : data) {
                String[] splitData = informPerson.split(DATA_INDEX);
                LocalDate dateWorkLocal = LocalDate.parse(splitData[0], formatter);
                if (name.equals(splitData[1]) && ((dateFromLocal.isBefore(dateWorkLocal)
                        && dateWorkLocal.isBefore(dateToLocal))
                        || dateWorkLocal.isEqual(dateFromLocal)
                        || dateWorkLocal.isEqual(dateToLocal))) {
                    salary = salary
                            + Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            reportSalary.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return reportSalary.toString();
    }
}

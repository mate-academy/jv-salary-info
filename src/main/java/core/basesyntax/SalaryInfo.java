package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultString
                = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String employee : names) {
            int salary = 0;
            for (String dataUnit : data) {
                if (dataUnit.contains(employee)
                        && isDateWithinRange(dataUnit.split(" ")[0], dateFrom, dateTo)) {
                    salary += Integer.parseInt(dataUnit.split(" ")[2])
                            * Integer.parseInt(dataUnit.split(" ")[3]);
                }
            }
            addWorkerToResult(resultString, employee, salary);
        }
        return resultString.toString();
    }

    private boolean isDateWithinRange(String dateToCheckString,
                                      String dateFromString,
                                      String dateToString) {
        LocalDate dateToCheck = LocalDate.parse(dateToCheckString, dateFormatter);
        LocalDate dateFrom = LocalDate.parse(dateFromString, dateFormatter);
        LocalDate dateTo = LocalDate.parse(dateToString, dateFormatter);
        return !dateToCheck.isBefore(dateFrom) && !dateToCheck.isAfter(dateTo);
    }

    private void addWorkerToResult(StringBuilder result, String employee, int salary) {
        result.append(System.lineSeparator()).append(employee).append(" - ").append(salary);
    }
}

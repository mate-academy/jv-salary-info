package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String WHITESPACE = " ";
    private static final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter
            .ofPattern("d.MM.yyyy");
    private static final String HYPHEN = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, DATETIMEFORMATTER);
        LocalDate dateToLocal = LocalDate.parse(dateTo, DATETIMEFORMATTER);
        StringBuilder outputNamesSalary = new StringBuilder();
        outputNamesSalary.append("Report for period " + dateFrom + HYPHEN + dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String infSalary : data) {
                String[] datPart = infSalary.split(WHITESPACE);
                String date_index = datPart[0];
                LocalDate dateData = LocalDate.parse(date_index, DATETIMEFORMATTER);
                if ((names[i].equals(datPart[1])) && (dateFromLocal.isBefore(dateData)
                        || dateFromLocal.equals(dateData)) && ((dateData.isBefore(dateToLocal)
                        || (dateToLocal.equals(dateData))))) {
                    salary = salary + Integer.parseInt(datPart[2])
                            * Integer.parseInt(datPart[3]);
                }
            }
            outputNamesSalary.append(System.lineSeparator()).append(names[i] + HYPHEN + salary);
        }
        return outputNamesSalary.toString();
    }
}

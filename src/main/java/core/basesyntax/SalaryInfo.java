package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int HOURS_INDEX = 3;
    private static final String SPLITTER = " ";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startWorkDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate finishWorkDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                    + dateFrom + " - "
                    + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String allData : data) {
                String[] dataInArray = allData.split(SPLITTER);
                LocalDate certainDay = LocalDate.parse(dataInArray[DATE_INDEX], FORMATTER);
                int salaryPerDay = Integer.parseInt(dataInArray[SALARY_INDEX])
                            * Integer.parseInt(dataInArray[HOURS_INDEX]);
                if (startWorkDate.getDayOfYear() < certainDay.getDayOfYear()
                        && certainDay.getDayOfYear() <= finishWorkDate.getDayOfYear()) {
                    if (dataInArray[NAME_INDEX].equals(name)) {
                        salary += salaryPerDay;
                    }
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}

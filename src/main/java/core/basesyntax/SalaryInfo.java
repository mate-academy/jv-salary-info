
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final String White_Space = " ";
    private static final int Date_Index = 0;
    private static final int Name_Index = 1;
    private static final int Hours_Index = 2;
    private static final int Salary_Index = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currentDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate currentDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salaryCount = 0;
            for (String line : data) {
                String[] splitData = line.split(White_Space);
                final LocalDate currentDate = LocalDate.parse(splitData[Date_Index], formatter);
                if (splitData[Name_Index].equals(name)
                        && ((currentDate.isAfter(currentDateFrom))
                        || (currentDate.isEqual(currentDateFrom)))
                        && (currentDate.isBefore(currentDateTo)
                        || currentDate.isEqual(currentDateTo))) {
                    salaryCount = salaryCount + Integer.parseInt(splitData[Hours_Index])
                            * Integer.parseInt(splitData[Salary_Index]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salaryCount);
        }
        return report.toString();
    }
}

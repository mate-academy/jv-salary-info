package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private final int startPositionForSubstringDate = 0;
    private final int endPositionForSubstringDate = 10;
    private final int startPositionForSubstringIncomeData = 12;
    private final int arrayPositionOfWorkingHours = 0;
    private final int arrayPositionOfIncomePerHour = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String simpleData : data) {
                if (simpleData.contains(name)) {
                    LocalDate rowDate = LocalDate.parse(simpleData.substring(
                                    startPositionForSubstringDate, endPositionForSubstringDate),
                            formatter);
                    if ((rowDate.isAfter(localDateFrom) || rowDate.isEqual(localDateFrom))
                            && rowDate.isBefore(localDateTo) || rowDate.isEqual(localDateTo)) {

                        String[] dayIncomeInfo = simpleData.substring(
                                startPositionForSubstringIncomeData
                                        + name.length()).split(" ");
                        salary += Integer.parseInt(dayIncomeInfo[arrayPositionOfWorkingHours])
                                * Integer.parseInt(dayIncomeInfo[arrayPositionOfIncomePerHour]);
                    }
                }
            }
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }
}

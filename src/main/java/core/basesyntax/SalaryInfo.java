package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

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
                    LocalDate rowDate = LocalDate.parse(simpleData.substring(0, 10), formatter);
                    if ((rowDate.isAfter(localDateFrom) || rowDate.isEqual(localDateFrom))
                            && rowDate.isBefore(localDateTo) || rowDate.isEqual(localDateTo)) {

                        String[] dayIncomeInformation = simpleData.substring(12
                                + name.length()).split(" ");
                        salary += Integer.parseInt(dayIncomeInformation[0])
                                * Integer.parseInt(dayIncomeInformation[1]);
                    }
                }
            }
            salaryInfo.append(salary);
        }

        return salaryInfo.toString();
    }

}

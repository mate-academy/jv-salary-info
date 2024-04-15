package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int dataDate = 0;
    private static final int dataName = 1;
    private static final int dataHours = 2;
    private static final int dataIncome = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate salaryDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate salaryDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] tempData = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(tempData[dataDate], formatter);

                if (!currentDate.isBefore(salaryDateFrom)
                        && !currentDate.isAfter(salaryDateTo)) {
                    if (name.equals(tempData[dataName])) {
                        totalSalary += Integer.parseInt(tempData[dataHours])
                                * Integer.parseInt(tempData[dataIncome]);
                    }
                }
            }
            reportBuilder.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return reportBuilder.toString();
    }
}

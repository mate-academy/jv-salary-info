package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int PAYMENT_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formatterDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate formatterDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder salaryReport = new StringBuilder();
        salaryReport.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        String[] dataInfo;
        for (String staff : names) {
            int totalSalary = 0;
            for (String datas : data) {
                dataInfo = datas.split(" ");
                if (staff.equals(dataInfo[NAME_INDEX])) {
                    LocalDate workingDate = LocalDate.parse(dataInfo[DATE_INDEX], FORMATTER);
                    if ((workingDate.isBefore(formatterDateTo)
                            || workingDate.isEqual(formatterDateTo))
                            && (workingDate.isAfter(formatterDateFrom)
                            || workingDate.isEqual(formatterDateFrom))) {
                        totalSalary += Integer.parseInt(dataInfo[WORKING_HOUR_INDEX])
                                * Integer.parseInt(dataInfo[PAYMENT_HOUR_INDEX]);
                    }
                }
            }
            salaryReport.append(System.lineSeparator())
                    .append(staff).append(" - ").append(totalSalary);
        }
        return salaryReport.toString();
    }
}

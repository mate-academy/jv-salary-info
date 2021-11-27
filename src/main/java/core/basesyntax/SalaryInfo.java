package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        var parseDateFrom = LocalDate.parse(dateFrom, dtf);
        var parseDateTo = LocalDate.parse(dateTo, dtf);
        var salaryInfo = new StringBuilder("Report for period "
                + dtf.format(parseDateFrom) + " - " + dtf.format(parseDateTo));
        String salaryEmployee = "";
        for (String name: names) {
            int salary = 0;
            for (String dataEmployee: data) {
                String[] dataEmployeeSplit = dataEmployee.split(" ");
                var parseDateWork = LocalDate.parse(dataEmployeeSplit[0], dtf);
                if (name.equals(dataEmployeeSplit[1])) {
                    if (parseDateFrom.isBefore(parseDateWork.plusDays(1))
                            && parseDateTo.isAfter(parseDateWork.minusDays(1))) {
                        salary += Integer.parseInt(dataEmployeeSplit[2])
                                * Integer.parseInt(dataEmployeeSplit[3]);
                    }
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}

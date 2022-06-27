package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate boundDateFrom = LocalDate.parse(dateFrom,formatter);
        LocalDate boundDateTo = LocalDate.parse(dateTo,formatter);
        int[] sumSalary = new int[names.length];
        for (String employeeData : data) {
            String[] employeeDataArray = employeeData.split(" ");
            LocalDate workingDate = LocalDate.parse(employeeDataArray[0],formatter);
            if ((workingDate.isEqual(boundDateFrom) || workingDate.isAfter(boundDateFrom))
                    && (workingDate.isEqual(boundDateTo) || workingDate.isBefore(boundDateTo))) {
                switch (employeeDataArray[1]) {
                    case("John"):
                        sumSalary[0] += Integer.parseInt(employeeDataArray[2])
                                * Integer.parseInt(employeeDataArray[3]);
                        break;
                    case("Andrew"):
                        sumSalary[1] += Integer.parseInt(employeeDataArray[2])
                                * Integer.parseInt(employeeDataArray[3]);
                        break;
                    case("Kate"):
                        sumSalary[2] += Integer.parseInt(employeeDataArray[2])
                                * Integer.parseInt(employeeDataArray[3]);
                        break;
                    default:
                        break;
                }
            }
        }
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator());
            report.append(names[i]);
            report.append(" - ");
            report.append(sumSalary[i]);
        }
        return new String(report);
    }

}

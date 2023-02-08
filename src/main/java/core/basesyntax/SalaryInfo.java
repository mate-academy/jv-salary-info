package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalaryInfo {
    private static final DateTimeFormatter
            DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ZERO_INCOME = 0;
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORK_HOURS = 2;
    private static final int INDEX_OF_INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> hashMapOfPersonAndCalculatedSalary =
                getMapOfEmployeeAndCalculatedSalary(names, data, dateFrom, dateTo);
        StringBuilder reportHeader = getReportHeader(dateFrom, dateTo);
        return getFullReport(reportHeader, hashMapOfPersonAndCalculatedSalary, names);
    }

    private StringBuilder getReportHeader(String dateFrom, String dateTo) {
        StringBuilder reportHeader = new StringBuilder();
        reportHeader
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        return reportHeader;
    }

    private String getFullReport(StringBuilder reportHeader,
                                 Map<String, Integer> hashMapOfPersonAndCalculatedSalary,
                                 String[] names) {
        return reportHeader
                .append(Arrays.stream(names)
                        .map(name -> System.lineSeparator()
                                + name + " - "
                                + (hashMapOfPersonAndCalculatedSalary
                                .getOrDefault(name, ZERO_INCOME)))
                        .collect(Collectors.joining()))
                .toString();
    }

    private Map<String, Integer> getMapOfEmployeeAndCalculatedSalary(String[] names,
                                                                     String[] data,
                                                                     String dateFrom,
                                                                     String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        final LocalDate[] dateOfSalary = new LocalDate[1]; // buffer
        return Stream.of(data)
                .map(employeeData -> employeeData.split(" "))
                .filter(employeeDataArray ->
                !(dateOfSalary[0] = LocalDate.parse(
                                employeeDataArray[INDEX_OF_DATE],
                                DATE_TIME_FORMATTER)).isBefore(localDateFrom)
                                && !dateOfSalary[0].isAfter(localDateTo)
                                && List.of(names)
                                .contains(employeeDataArray[INDEX_OF_NAME]))
                .collect(Collectors.toMap(
                        employeeDataArray -> employeeDataArray[INDEX_OF_NAME],
                        employeeDataArray ->
                                Integer.parseInt(employeeDataArray[INDEX_OF_WORK_HOURS])
                                * Integer.parseInt(employeeDataArray[INDEX_OF_INCOME]),
                        Integer::sum));
    }
}


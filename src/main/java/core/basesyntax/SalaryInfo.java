package core.basesyntax;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SalaryInfo {
    private static final String FIRST_LINE_FORMAT = "Report for period %s - %s";
    private static final String SALARY_LINE_FORMAT = "\n%s - %d";
    private static final int DEFAULT_SALARY_VALUE = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, SalaryData.DATE_TIME_FORMATTER);
        LocalDate dateToLocal = LocalDate.parse(dateTo, SalaryData.DATE_TIME_FORMATTER);
        DateRange dateRange = new DateRange(dateFromLocal, dateToLocal);
        Map<String, Integer> nameSalaryMap = getNameSalaryMap(names, data, dateRange);
        StringBuilder sb = new StringBuilder(String.format(FIRST_LINE_FORMAT, dateFrom, dateTo));
        for (String name : names) {
            int salary = nameSalaryMap.getOrDefault(name, DEFAULT_SALARY_VALUE);
            sb.append(String.format(SALARY_LINE_FORMAT, name, salary));
        }
        return sb.toString();
    }

    private static Map<String, Integer> getNameSalaryMap(String[] names,
                                                         String[] data,
                                                         DateRange dateRange) {
        return Arrays.stream(data)
                .map(SalaryData::parse)
                .filter(sd -> arrayContainsObject(names, sd.name()))
                .filter(sd -> dateRange.isInRangeInclusive(sd.date()))
                .collect(Collectors.groupingBy(SalaryData::name,
                        Collectors.summingInt(sd -> sd.hoursWorked() * sd.paymentPerHour())));
    }

    private static boolean arrayContainsObject(Object[] arr, Object object) {
        return Arrays.asList(arr).contains(object);
    }

    private static class DateRange {
        private LocalDate dateFrom;
        private LocalDate dateTo;

        public DateRange(LocalDate dateFrom, LocalDate dateTo) {
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
        }

        public boolean isInRangeInclusive(LocalDate date) {
            return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
        }
    }
}

package core.basesyntax;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SalaryInfo {
    private static final String FIRST_LINE_FORMAT = "Report for period %s - %s";
    private static final String SALARY_LINE_FORMAT = "%s - %d";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateRange dateRange = DateRange.of(dateFrom, dateTo);
        List<PersonTotalSalary> personTotalSalaries =
                getPersonTotalSalaries(names, data, dateRange);
        String firstLine = String.format(FIRST_LINE_FORMAT, dateFrom, dateTo);
        return getReport(firstLine, personTotalSalaries);
    }

    private static List<PersonTotalSalary> getPersonTotalSalaries(String[] names,
                                                                  String[] data,
                                                                  DateRange dateRange) {
        List<PersonTotalSalary> list = namesToPersonTotalSalariesList(names);
        Arrays.stream(data)
                .map(SalaryData::parse)
                .filter(salaryData -> arrayContainsObject(names, salaryData.name()))
                .filter(salaryData -> dateRange.isInRangeInclusive(salaryData.date()))
                .forEach(salaryData -> findPersonAndIncrementSalary(list, salaryData));
        return list;
    }

    private static String getReport(String firstLine,
                                    List<PersonTotalSalary> personTotalSalaries) {
        return personTotalSalaries.stream()
                .map(person -> String.format(SALARY_LINE_FORMAT,
                        person.getName(), person.getSalary()))
                .map(line -> System.lineSeparator() + line)
                .collect(Collectors.joining("", firstLine, ""));
    }

    private static void findPersonAndIncrementSalary(List<PersonTotalSalary> list,
                                                     SalaryData salaryData) {
        int salary = salaryData.hoursWorked() * salaryData.paymentPerHour();
        list.stream()
                .filter(person -> person.getName().equals(salaryData.name()))
                .findFirst()
                .ifPresent(person -> person.incrementSalary(salary));
    }

    private static List<PersonTotalSalary> namesToPersonTotalSalariesList(String[] names) {
        return Arrays.stream(names)
                .map(PersonTotalSalary::new)
                .toList();
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

        public static DateRange of(String dateFromStr, String dateToStr) {
            LocalDate dateFrom = DateParser.parseDate(dateFromStr);
            LocalDate dateTo = DateParser.parseDate(dateToStr);
            return new DateRange(dateFrom, dateTo);
        }

        public boolean isInRangeInclusive(LocalDate date) {
            return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
        }
    }
}

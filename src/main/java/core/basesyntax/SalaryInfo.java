package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String TITLE_FOR_REPORT = "Report for period ";
    private static final String DASH_FOR_REPORT = " - ";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String STRING_FOR_SPLIT = " ";
    private static final int NUMBER_FOR_CORRECTLY_DATE = 1;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int NUMBER_OF_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        List<EmployeeWorkingDay> peoplesSalaries = parseEmployeeWorkingDay(data);
        return getSalary(names, peoplesSalaries, fromDate, toDate);
    }

    private String getSalary(String[] names, List<EmployeeWorkingDay> peoplesSalaries,
                             LocalDate fromDate, LocalDate toDate) {
        StringBuilder report = new StringBuilder(TITLE_FOR_REPORT);
        report.append(fromDate.format(DATE_TIME_FORMATTER))
                .append(DASH_FOR_REPORT)
                .append(toDate.format(DATE_TIME_FORMATTER))
                .append(NEW_LINE);
        for (String name : names) {
            int salary = peoplesSalaries.stream()
                    .filter(s -> name.equals(s.getName())
                            && s.getDate().isAfter(fromDate.minusDays(NUMBER_FOR_CORRECTLY_DATE))
                            && s.getDate().isBefore(toDate.plusDays(NUMBER_FOR_CORRECTLY_DATE)))
                    .map(EmployeeWorkingDay::getSalaryPerDay)
                    .mapToInt(s -> s).sum();
            report.append(name).append(DASH_FOR_REPORT).append(salary)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    private List<EmployeeWorkingDay> parseEmployeeWorkingDay(String[] data) {
        List<EmployeeWorkingDay> salaries = new ArrayList<>();
        for (String datum : data) {
            salaries.add(writeDataToPeoplesSalary(datum));
        }
        return salaries;
    }

    private EmployeeWorkingDay writeDataToPeoplesSalary(String data) {
        String[] dataArray = data.split(STRING_FOR_SPLIT);
        EmployeeWorkingDay peoplesSalary = new EmployeeWorkingDay();
        peoplesSalary.setDate(LocalDate.parse(dataArray[DATE_INDEX], DATE_TIME_FORMATTER));
        peoplesSalary.setName(dataArray[NAME_INDEX]);
        peoplesSalary.setNumberOfHours(Integer.parseInt(dataArray[NUMBER_OF_HOURS_INDEX]));
        peoplesSalary.setSalaryPerHour(Integer.parseInt(dataArray[SALARY_PER_HOUR]));
        return peoplesSalary;
    }
}

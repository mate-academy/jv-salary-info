package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    private static final String PATTERN_FOR_DATE = "dd.MM.yyyy";
    private static final String TITLE_FOR_REPORT = "Report for period ";
    private static final String DASH_FOR_REPORT = " - ";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String STRING_FOR_SPLIT = " ";

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern(PATTERN_FOR_DATE));
        LocalDate toDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(PATTERN_FOR_DATE));
        List<PeoplesSalary> peoplesSalaries = writeDataToListPeoplesSalary(data);
        System.out.println(getSalary(names, peoplesSalaries, fromDate, toDate));
        return getSalary(names, peoplesSalaries, fromDate, toDate);
    }

    private static String getSalary(String[] names, List<PeoplesSalary> peoplesSalaries,
                                    LocalDate fromDate, LocalDate toDate) {
        StringBuilder report = new StringBuilder(TITLE_FOR_REPORT);
        report.append(fromDate.format(DateTimeFormatter.ofPattern(PATTERN_FOR_DATE)))
                .append(DASH_FOR_REPORT)
                .append(toDate.format(DateTimeFormatter.ofPattern(PATTERN_FOR_DATE)))
                .append(NEW_LINE);
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int salary = peoplesSalaries.stream()
                    .filter(s -> name.equals(s.getName())
                            && s.getWorkDay().isAfter(fromDate.minusDays(1))
                            && s.getWorkDay().isBefore(toDate.plusDays(1)))
                    .map(s -> (s.getNumberOfHours() * s.getSalaryForHours()))
                    .mapToInt(s -> s).sum();
            report.append(names[i]).append(DASH_FOR_REPORT).append(salary)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    private static List<PeoplesSalary> writeDataToListPeoplesSalary(String[] data) {
        List<PeoplesSalary> salaries = new ArrayList<>();
        for (String datum : data) {
            salaries.add(writeDataToPeoplesSalary(datum));
        }
        return salaries;
    }

    private static PeoplesSalary writeDataToPeoplesSalary(String data) {
        String[] dataArray = data.split(STRING_FOR_SPLIT);
        PeoplesSalary peoplesSalary = new PeoplesSalary();
        peoplesSalary.setWorkDay(LocalDate.parse(dataArray[0],
                DateTimeFormatter.ofPattern(PATTERN_FOR_DATE)));
        peoplesSalary.setName(dataArray[1]);
        peoplesSalary.setNumberOfHours(Integer.parseInt(dataArray[2]));
        peoplesSalary.setSalaryForHours(Integer.parseInt(dataArray[3]));
        return peoplesSalary;
    }
}

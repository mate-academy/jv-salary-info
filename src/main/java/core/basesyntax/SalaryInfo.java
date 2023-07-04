package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SPACE_SEPARATOR = " ";
    private static final String DASH_SEPARATOR = " - ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int PER_HOUR_SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String record: data) {
                String[] splitRecord = record.split(SPACE_SEPARATOR);
                LocalDate dateWork = LocalDate.parse(splitRecord[DATE_INDEX], FORMATTER);
                if (names[i].equals(splitRecord[NAME_INDEX]) && dateWork.isAfter(formattedDateFrom)
                        && dateWork.isBefore(formattedDateTo)) {
                    salaries[i] += Integer.parseInt(splitRecord[WORKED_HOURS_INDEX])
                            * Integer.parseInt(splitRecord[PER_HOUR_SALARY_INDEX]);
                }
            }
        }
        StringBuilder infoAboutWorkers = new StringBuilder();
        infoAboutWorkers.append("Report for period ")
                .append(dateFrom)
                .append(DASH_SEPARATOR)
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            infoAboutWorkers.append(NEW_LINE)
                    .append(names[i])
                    .append(DASH_SEPARATOR)
                    .append(salaries[i]);
        }

        return infoAboutWorkers.toString();
    }
}

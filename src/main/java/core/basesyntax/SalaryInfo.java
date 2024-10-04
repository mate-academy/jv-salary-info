package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int NAME_INDEX_ZERO = 0;
    public static final int NAME_INDEX_FIRST = 1;
    public static final int NAME_INDEX_SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    public static final String INPUT_DIVIDER = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        String [] peopleNames = new String[THIRD];
        int [] salaries = new int[THIRD];
        String [] splitInputString = new String[FOURTH];
        peopleNames[NAME_INDEX_ZERO] = names[NAME_INDEX_ZERO];
        peopleNames[NAME_INDEX_FIRST] = defineName(names, peopleNames[NAME_INDEX_ZERO],
                peopleNames[NAME_INDEX_ZERO]);
        peopleNames[NAME_INDEX_SECOND] = defineName(names, peopleNames[NAME_INDEX_ZERO],
                peopleNames[NAME_INDEX_FIRST]);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);
        LocalDate parsedCurrentData;

        for (int i = 0; i < data.length; i++) {
            splitInputString = data[i].split(INPUT_DIVIDER);
            parsedCurrentData = LocalDate.parse(splitInputString[0], formatter);

            if (isWithinDateRange(parsedCurrentData, parsedDateFrom, parsedDateTo)) {

                salaries[NAME_INDEX_ZERO] += defineIncome(peopleNames[NAME_INDEX_ZERO],
                        splitInputString[NAME_INDEX_FIRST], splitInputString[NAME_INDEX_SECOND],
                        splitInputString[THIRD]);

                salaries[NAME_INDEX_FIRST] += defineIncome(peopleNames[NAME_INDEX_FIRST],
                        splitInputString[NAME_INDEX_FIRST], splitInputString[NAME_INDEX_SECOND],
                        splitInputString[THIRD]);

                salaries[NAME_INDEX_SECOND] += defineIncome(peopleNames[NAME_INDEX_SECOND],
                        splitInputString[NAME_INDEX_FIRST], splitInputString[NAME_INDEX_SECOND],
                        splitInputString[THIRD]);
            }
        }
        return createReport(peopleNames, salaries, dateFrom, dateTo);
    }

    private String defineName(String [] names, String firstName, String secondName) {
        String name = "";
        for (int i = 1; i < names.length; i++) {
            if (!firstName.equals(names[i]) && !secondName.equals(names[i])) {
                name = names[i];
                break;
            }
        }
        return name;
    }

    private int defineIncome(String personName, String comparisonName,
                             String hours, String income) {
        if (comparisonName.equals(personName)) {
            return Integer.parseInt(hours)
                    * Integer.parseInt(income);
        }
        return 0;
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
    }

    private String createReport(String [] names, int [] salaries, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        return builder.append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator())
                .append(names[NAME_INDEX_ZERO])
                .append(" - ").append(salaries[NAME_INDEX_ZERO])
                .append(System.lineSeparator())
                .append(names[NAME_INDEX_FIRST])
                .append(" - ")
                .append(salaries[NAME_INDEX_FIRST])
                .append(System.lineSeparator())
                .append(names[NAME_INDEX_SECOND])
                .append(" - ")
                .append(salaries[NAME_INDEX_SECOND]).toString();
    }
}

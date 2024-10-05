package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int FIRST_NAME = 0;
    private static final int SECOND_NAME = 1;
    private static final int THIRD_NAME = 2;
    private static final int SALARY = 3;
    private static final int HOURS_QUANTITY = 4;
    private static final String INPUT_DIVIDER = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        String [] peopleNames = new String[SALARY];
        int [] salaries = new int[SALARY];
        String [] splitInputString = new String[HOURS_QUANTITY];
        peopleNames[FIRST_NAME] = names[FIRST_NAME];
        peopleNames[SECOND_NAME] = defineName(names, peopleNames[FIRST_NAME],
                peopleNames[FIRST_NAME]);
        peopleNames[THIRD_NAME] = defineName(names, peopleNames[FIRST_NAME],
                peopleNames[SECOND_NAME]);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);
        LocalDate parsedCurrentData;

        for (int i = 0; i < data.length; i++) {
            splitInputString = data[i].split(INPUT_DIVIDER);
            parsedCurrentData = LocalDate.parse(splitInputString[0], formatter);

            if (isWithinDateRange(parsedCurrentData, parsedDateFrom, parsedDateTo)) {

                salaries[FIRST_NAME] += defineIncome(peopleNames[FIRST_NAME],
                        splitInputString[SECOND_NAME], splitInputString[THIRD_NAME],
                        splitInputString[SALARY]);

                salaries[SECOND_NAME] += defineIncome(peopleNames[SECOND_NAME],
                        splitInputString[SECOND_NAME], splitInputString[THIRD_NAME],
                        splitInputString[SALARY]);

                salaries[THIRD_NAME] += defineIncome(peopleNames[THIRD_NAME],
                        splitInputString[SECOND_NAME], splitInputString[THIRD_NAME],
                        splitInputString[SALARY]);
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
                .append(names[FIRST_NAME])
                .append(" - ").append(salaries[FIRST_NAME])
                .append(System.lineSeparator())
                .append(names[SECOND_NAME])
                .append(" - ")
                .append(salaries[SECOND_NAME])
                .append(System.lineSeparator())
                .append(names[THIRD_NAME])
                .append(" - ")
                .append(salaries[THIRD_NAME]).toString();
    }
}

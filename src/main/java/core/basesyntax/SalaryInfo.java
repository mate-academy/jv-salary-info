package core.basesyntax;

public class SalaryInfo {
    public static final int ZERO = 0;
    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    public static final int FIFTH = 5;
    public static final String INPUT_DIVIDER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        String [] peopleNames = new String[THIRD];
        int [] salaries = new int[THIRD];
        String [] splitInputString = new String[FOURTH];
        int currentDay = 0;
        int currentMonth = 0;
        peopleNames[ZERO] = names[ZERO];
        peopleNames[FIRST] = defineName(names, peopleNames[ZERO], peopleNames[ZERO]);
        peopleNames[SECOND] = defineName(names, peopleNames[ZERO], peopleNames[FIRST]);

        for (int i = 0; i < data.length; i++) {
            splitInputString = data[i].split(INPUT_DIVIDER);
            currentDay = Integer.parseInt(data[i].substring(ZERO, SECOND));
            currentMonth = Integer.parseInt(data[i].substring(THIRD, FIFTH));

            if (defineBorders(parseInputString(dateFrom, dateTo), currentDay, currentMonth)) {

                salaries[ZERO] += defineIncome(peopleNames[ZERO], splitInputString[FIRST],
                        splitInputString[SECOND], splitInputString[THIRD]);

                salaries[FIRST] += defineIncome(peopleNames[FIRST], splitInputString[FIRST],
                        splitInputString[SECOND], splitInputString[THIRD]);

                salaries[SECOND] += defineIncome(peopleNames[SECOND], splitInputString[FIRST],
                        splitInputString[SECOND], splitInputString[THIRD]);
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

    private int [] parseInputString(String dateFrom, String dateTo) {
        int [] ranges = new int[FOURTH];
        ranges[ZERO] = Integer.parseInt(dateFrom.substring(ZERO, SECOND));
        ranges[FIRST] = Integer.parseInt(dateFrom.substring(THIRD, FIFTH));
        ranges[SECOND] = Integer.parseInt(dateTo.substring(ZERO, SECOND));
        ranges[THIRD] = Integer.parseInt(dateTo.substring(THIRD, FIFTH));
        return ranges;
    }

    private boolean defineBorders(int [] ranges, int currentDay, int currentMonth) {
        return (ranges[ZERO] <= currentDay && currentDay <= ranges[SECOND]
                && currentMonth == ranges[THIRD] && currentMonth == ranges[FIRST])
                || (ranges[ZERO] <= currentDay && ranges[FIRST] == currentMonth
                && currentMonth < ranges[THIRD])
                || (ranges[FIRST] < currentMonth && currentDay <= ranges[SECOND]
                && currentMonth == ranges[THIRD]);
    }

    private String createReport(String [] names, int [] salaries, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        return builder.append(dateFrom)
                        .append(" - ")
                        .append(dateTo)
                        .append(System.lineSeparator())
                        .append(names[ZERO])
                        .append(" - ").append(salaries[ZERO])
                        .append(System.lineSeparator())
                        .append(names[FIRST])
                        .append(" - ")
                        .append(salaries[FIRST])
                        .append(System.lineSeparator())
                        .append(names[SECOND])
                        .append(" - ")
                        .append(salaries[SECOND]).toString();
    }
}

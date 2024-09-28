package core.basesyntax;

public class SalaryInfo {
    public static final int ZERO = 0;
    public static final int FIRST = 1;
    public static final int SECOND = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String DivideR = "[.]";
        final int ThirD = 3;
        final int FiftH = 5;
        String [] peopleNames = new String[3];
        int [] salaries = new int[3];
        String [] splitInputString = new String[4];

        int currentDay = 0;
        int currentMonth = 0;
        int toDay = Integer.parseInt(dateTo.substring(ZERO, SECOND));
        int fromDay = Integer.parseInt(dateFrom.substring(ZERO, SECOND));
        int toMonth = Integer.parseInt(dateTo.substring(ThirD, FiftH));
        int fromMonth = Integer.parseInt(dateFrom.substring(ThirD, FiftH));

        peopleNames[ZERO] = names[0];
        peopleNames[FIRST] = defineName(names, peopleNames[ZERO], peopleNames[ZERO]);
        peopleNames[SECOND] = defineName(names, peopleNames[ZERO], peopleNames[FIRST]);

        for (int i = 0; i < data.length; i++) {
            splitInputString = data[i].split(" ");
            currentDay = Integer.parseInt(data[i].substring(ZERO, SECOND));
            currentMonth = Integer.parseInt(data[i].substring(ThirD, FiftH));

            if ((fromDay <= currentDay && currentDay <= toDay
                    && currentMonth == toMonth && currentMonth == fromMonth)
                    || (fromDay <= currentDay && fromMonth == currentMonth
                    && currentMonth < toMonth)
                    || (fromMonth < currentMonth && currentDay <= toDay
                    && currentMonth == toMonth)) {

                salaries[ZERO] += defineIncome(peopleNames[ZERO], splitInputString[FIRST],
                        splitInputString[SECOND], splitInputString[ThirD]);

                salaries[FIRST] += defineIncome(peopleNames[FIRST], splitInputString[FIRST],
                        splitInputString[SECOND], splitInputString[ThirD]);

                salaries[SECOND] += defineIncome(peopleNames[SECOND], splitInputString[FIRST],
                        splitInputString[SECOND], splitInputString[ThirD]);
            }
        }
        return createReport(peopleNames, salaries, dateFrom, dateTo);
    }

    private String defineName(String [] names, String firstName, String secondName) {
        for (int i = 1; i < names.length; i++) {
            if (!firstName.equals(names[i]) && !secondName.equals(names[i])) {
                return names[i];
            }
        }
        return "";
    }

    private int defineIncome(String personName, String comparisonName,
                             String hours, String income) {
        if (comparisonName.equals(personName)) {
            return Integer.parseInt(hours)
                    * Integer.parseInt(income);
        }
        return 0;
    }

    private String createReport(String [] names, int [] salaries, String dateFrom, String dateTo) {
        return "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator() + names[ZERO] + " - " + salaries[ZERO]
                + System.lineSeparator() + names[FIRST] + " - " + salaries[FIRST]
                + System.lineSeparator() + names[SECOND] + " - " + salaries[SECOND];
    }
}

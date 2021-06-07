package core.basesyntax;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final char DOT = '.';

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] totalSalaryForPerson = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            if (getYear(data[i]) >= getYear(dateFrom) && getYear(data[i]) <= getYear(dateTo)) {
                if (getMonth(data[i]) >= getMonth(dateFrom)
                        && getMonth(data[i]) <= getMonth(dateTo)) {
                    if (getDay(data[i]) >= getDay(dateFrom)) {
                        for (int j = 0; j < names.length; j++) {
                            if (names[j].equals(getName(data[i]))) {
                                totalSalaryForPerson[j] +=
                                        getPayPerDay(data[i]) * getDaysOfWork(data[i]);
                            }
                        }
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ")
                    .append(totalSalaryForPerson[i]).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private int getYear(String string) {
        int year = Integer.parseInt(string.substring(string.lastIndexOf(DOT)
                + 1, string.lastIndexOf(DOT) + 5));
        return year;
    }

    private int getMonth(String string) {
        int month = Integer.parseInt(string.substring(string.indexOf(DOT) + 1,
                string.lastIndexOf(DOT)));
        return month;
    }

    private int getDay(String string) {
        int day = Integer.parseInt(string.substring(0, string.indexOf(DOT)));
        return day;
    }

    private String getName(String string) {
        String name = string.substring(string.indexOf(SPACE) + 1,
                string.indexOf(SPACE, string.indexOf(SPACE) + 1));
        return name;
    }

    private int getDaysOfWork(String string) {
        int daysOfWork = Integer.parseInt(string.substring(
                string.lastIndexOf(SPACE)));
        return daysOfWork;
    }

    private int getPayPerDay(String string) {
        int payPerDay = Integer.parseInt(string.substring(
                string.lastIndexOf(SPACE) + 1));
        return payPerDay;
    }

}

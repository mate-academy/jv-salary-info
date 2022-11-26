package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    static final int DATE = 0;
    static final int NAME = 1;
    static final int HOURS = 2;
    static final int PRICE = 3;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.of(Integer.parseInt(dateFrom.substring(6)),
                Integer.parseInt(dateFrom.substring(3, 5)),
                Integer.parseInt(dateFrom.substring(0, 2)));
        LocalDate toDate = LocalDate.of(Integer.parseInt(dateTo.substring(6)),
                Integer.parseInt(dateTo.substring(3, 5)),
                Integer.parseInt(dateTo.substring(0, 2)));
        User[] users = new User[names.length];
        String[] array = new String[4];

        for (int i = 0; i < names.length; i++) {
            users[i] = new User(names[i]);
            users[i].setEarned(0);
        }

        LocalDate dynamicDate = fromDate;

        for (String element: data) {
            dynamicDate = LocalDate.of(Integer.parseInt(element.substring(6, 10)),
                    Integer.parseInt(element.substring(3, 5)),
                    Integer.parseInt(element.substring(0, 2)));
            array = element.split(" ");
            if ((dynamicDate.isAfter(fromDate) && dynamicDate.isBefore(toDate)) ||
                    (dynamicDate.isEqual(fromDate)) || (dynamicDate.isEqual(toDate))) {
                for (User user: users) {
                    if (array[1].equals(user.getName())) {
                        user.addMoney(Integer.parseInt(array[2]), Integer.parseInt(array[3]));
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (User user: users) {
            stringBuilder.append(System.lineSeparator())
                    .append(user.getName())
                    .append(" - ")
                    .append(user.getEarned());
        }
        return stringBuilder.toString();
    }
}

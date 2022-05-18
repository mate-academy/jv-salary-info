package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int DATE_FROM_PERSON_ARRAY = 0;
    private static final int NAME_FROM_PERSON_ARRAY = 1;
    private static final int HOURS_FROM_PERSON_ARRAY = 2;
    private static final int INCOME_FROM_PERSON_ARRAY = 3;

    private boolean isDateValid(String userDate, String dateFrom, String dateTo) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date user = simpleDateFormat.parse(userDate);
            Date from = simpleDateFormat.parse(dateFrom);
            Date to = simpleDateFormat.parse(dateTo);
            return ((user.after(from) && user.before(to)) || (user.equals(from) && user.before(to))
                    || (user.after(from) && user.equals(to)));
        } catch (ParseException e) {
            System.out.println("Date is not valid");
        }
        return false;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        StringBuilder resultsMessage = new StringBuilder("Report for period ");
        resultsMessage.append(dateFrom).append(" - ").append(dateTo);

        for (int j = 0; j < names.length; j++) {
            for (String datum : data) {
                String[] person = datum.split(" ");
                if (names[j].equals(person[NAME_FROM_PERSON_ARRAY])
                        && isDateValid(person[DATE_FROM_PERSON_ARRAY], dateFrom, dateTo)) {
                    salary[j] += Integer.parseInt(person[HOURS_FROM_PERSON_ARRAY])
                            * Integer.parseInt(person[INCOME_FROM_PERSON_ARRAY]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            resultsMessage.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return resultsMessage.toString();
    }
}

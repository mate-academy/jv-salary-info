package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Calendar dateFromDateFormat = changeDateFormat(dateFrom);
        Calendar dateToDateFormat = changeDateFormat(dateTo);
        dateFromDateFormat.add(Calendar.DAY_OF_MONTH, -1);
        dateToDateFormat.add(Calendar.DAY_OF_MONTH, 1);
        int[] salary = new int[names.length];

        for (String dataRow : data) {
            String[] dataInfo = dataRow.split(" ");
            if (changeDateFormat(dataInfo[0]).after(dateFromDateFormat)
                    && changeDateFormat(dataInfo[0]).before(dateToDateFormat)) {
                for (int i = 0; i < names.length; i++) {
                    if (Objects.equals(names[i], dataInfo[1])) {
                        salary[i] += Integer.parseInt(dataInfo[2]) * Integer.parseInt(dataInfo[3]);
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (i != names.length - 1) {
                stringBuilder.append(names[i]).append(" - ").append(salary[i])
                        .append(System.lineSeparator());
            } else {
                stringBuilder.append(names[i]).append(" - ").append(salary[i]);
            }
        }

        return stringBuilder.toString();
    }

    public Calendar changeDateFormat(String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(sdf.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }
}

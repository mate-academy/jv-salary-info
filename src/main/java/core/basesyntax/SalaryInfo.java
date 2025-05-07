package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int beginIndex = 10;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        int[] salary = new int[names.length];
        int hour = 0;
        int incomePerHour = 0;
        Date dateBegin;
        Date dateEnd;
        Date dateTemp;

        try {
            dateBegin = formatter.parse(dateFrom);
            dateEnd = formatter.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (String datum : data) {

            try {
                dateTemp = formatter.parse(datum);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (dateBegin.compareTo(dateTemp) <= 0 && dateEnd.compareTo(dateTemp) >= 0) {
                for (int j = 0; j < names.length; j++) {
                    if (datum.contains(names[j])) {
                        Pattern pattern = Pattern.compile("\\d+");
                        Matcher matcher = pattern.matcher(datum.substring(beginIndex));

                        if (matcher.find()) {
                            hour = Integer.parseInt(matcher.group());
                        }
                        if (matcher.find()) {
                            incomePerHour = Integer.parseInt(matcher.group());
                        }
                        salary[j] = salary[j] + hour * incomePerHour;
                    }

                }
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                builder.append(names[i] + " - " + salary[i]);
            } else {
                builder.append(names[i] + " - " + salary[i] + System.lineSeparator());
            }
        }
        return builder.toString();
    }
}

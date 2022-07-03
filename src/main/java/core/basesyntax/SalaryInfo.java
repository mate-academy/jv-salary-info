package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int DATA_TO_CHECK = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int totalSalaryOfAPerson = 0;
            for (int j = 0; j < data.length; j++) {
                String[] oneLineOfData = data[j].split(" ");
                if (!oneLineOfData[NAME].equals(names[i])) {
                    continue;
                }
                if (isDateRelevant(oneLineOfData[DATA_TO_CHECK], dateFrom, dateTo)) {
                    totalSalaryOfAPerson += Integer.parseInt(oneLineOfData[HOURS])
                            * Integer.parseInt(oneLineOfData[INCOME_PER_HOUR]);
                }
            }
            builder.append(names[i]).append(" - ").append(totalSalaryOfAPerson);
            if (i != names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    private boolean isDateRelevant(String dataToCheck, String dataFrom, String dataTo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date toCheck = sdf.parse(dataToCheck);
            Date from = sdf.parse(dataFrom);
            Date to = sdf.parse(dataTo);
            return (toCheck.compareTo(from) < 0 || toCheck.compareTo(to) > 0) ? false : true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}

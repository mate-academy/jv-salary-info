package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int[] salariesInfo = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String line : data) {
                if (dateNotInRange(line.split(" ")[DATE_INDEX], dateFrom, dateTo)) {
                    continue;
                }
                if (line.split(" ")[NAME_INDEX].equals(names[i])) {
                    salariesInfo[i] += Integer.parseInt(line.split(" ")[HOURS_INDEX])
                            * Integer.parseInt(line.split(" ")[SALARY_PER_HOUR_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salariesInfo[i]);
        }
        return reportBuilder.toString();
    }

    private boolean dateNotInRange(String dateChecked, String dateFrom, String dateTo) {
        try {
            return simpleDateFormat.parse(dateChecked).before(simpleDateFormat.parse(dateFrom))
                    || simpleDateFormat.parse(dateChecked).after(simpleDateFormat.parse(dateTo));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}


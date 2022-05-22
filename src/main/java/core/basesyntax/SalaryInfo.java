package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private static final int DATE_FROM_DATA = 0;
    private static final int NAME_FROM_DATA = 1;
    private static final int HOURS_FROM_DATA = 2;
    private static final int SALARY_PER_HOUR_FROM_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder getSalaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        int[] salariesInfo = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String currentData : data) {
                if (dateNotInRange(currentData.split(" ")[DATE_FROM_DATA], dateFrom, dateTo)) {
                    continue;
                }
                //System.out.println("#1 " + currentData);
                if (currentData.split(" ")[NAME_FROM_DATA].equals(names[i])) {
                    salariesInfo[i] += Integer.parseInt(currentData.split(" ")[HOURS_FROM_DATA])
                            * Integer.parseInt(currentData.split(" ")[SALARY_PER_HOUR_FROM_DATA]);
                }
            }
            getSalaryInfo.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salariesInfo[i]);
        }
        System.out.println("#2 " + getSalaryInfo);
        return getSalaryInfo.toString();
    }

    private boolean dateNotInRange(String dateChecked, String dateFrom, String dateTo) {
        try {
            return SDF.parse(dateChecked).before(SDF.parse(dateFrom))
                    || SDF.parse(dateChecked).after(SDF.parse(dateTo));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}


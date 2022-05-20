package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder getSalaryInfo = new StringBuilder("Report for period "
                                                            + dateFrom + " - " + dateTo);
        int[] salariesInfo = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                if (dateNotInRange(datum.split(" ")[0], dateFrom, dateTo)) {
                    continue;
                }
                if (datum.split(" ")[1].equals(names[i])) {
                    salariesInfo[i] += Integer.parseInt(datum.split(" ")[2])
                            * Integer.parseInt(datum.split(" ")[3]);
                }
            }
            getSalaryInfo.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salariesInfo[i]);
        }
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


package core.basesyntax;

import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        String infoSalary = null;
        int[] salary = new int[names.length];

        int yearFrom = Integer.parseInt(dateFrom.substring(6, 10));
        int yearTo = Integer.parseInt(dateTo.substring(6, 10));
        int monthFrom = Integer.parseInt(dateFrom.substring(3, 5));
        int monthTo = Integer.parseInt(dateTo.substring(3, 5));
        int dayFrom = Integer.parseInt(dateFrom.substring(0, 2));
        int dayTo = Integer.parseInt(dateTo.substring(0, 2));
        for (int i = 0; i < data.length; i++) {
            int dataYear = Integer.parseInt(data[i].substring(6, 10));
            int dataMonth = Integer.parseInt(data[i].substring(3, 5));
            int dataDay = Integer.parseInt(data[i].substring(0, 2));
            if (dataYear != yearFrom && dataYear != yearTo) {
                data[i] = null;
                continue;
            } else if (dataMonth != monthFrom && dataMonth != monthTo) {
                data[i] = null;
                continue;
            } else if  (dataDay < dayFrom || dataDay > dayTo) {
                data[i] = null;
                continue;
            }
        }
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j] == null) {
                    continue;
                } else {
                    String[] splitName = data[j].split(" ");
                    for (int k = 0; k < splitName.length; k++) {
                        if (splitName[k].equals(names[i])) {
                            salary[i] = salary[i] + Integer.parseInt(splitName[2]) * Integer.parseInt(splitName[3]);
                        }
                    }
                }
            }
            infoSalary = builder.append("\n").append(names[i])
                    .append(" - ").append(salary[i]).toString();
        }
        return "Report for period " + dateFrom + " - " +  dateTo  +  infoSalary;
    }
}

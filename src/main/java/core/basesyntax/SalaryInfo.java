package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        String infoSalary = null;
        int salary = 0;

        int yearFrom = Integer.parseInt(dateFrom.substring(6, 10));
        int yearTo = Integer.parseInt(dateTo.substring(6, 10));
        int monthFrom = Integer.parseInt(dateFrom.substring(3, 5));
        int monthTo = Integer.parseInt(dateFrom.substring(3, 5));
        int dayFrom = Integer.parseInt(dateFrom.substring(0, 2));
        int dayTo = Integer.parseInt(dateTo.substring(0, 2));

        for (int i = 0; i < data.length; i++) {
            int dataYear = Integer.parseInt(data[i].substring(6, 10));
            int dataMonth = Integer.parseInt(data[i].substring(3, 5));
            int dataDay = Integer.parseInt(data[i].substring(0, 2));
            if (dataYear != yearFrom
                    || dataYear != yearTo
                    || dataMonth != monthFrom
                    || dataMonth != monthTo
                    || dataDay < dayFrom
                    || dataDay > dayTo) {
                data[i] = null;
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
                            salary = salary + Integer.parseInt(splitName[2]) * Integer.parseInt(splitName[3]);
                        }
                    }
                }
            }
            infoSalary = builder.append("Report for period").append(" ").append(dateFrom)
                    .append(" - ").append(dateTo).append("\n").append(names[i])
                    .append(" - ").append(salary).append("\n").toString();
        }
        return infoSalary;
    }
}





  /* Report for period #date_1# - #date_2#
           #Name of employee# - #money earned#*/
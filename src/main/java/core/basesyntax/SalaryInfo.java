package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String date = data[j].substring(0, 10);
                if (data[j].contains(names[i]) && isInRange(dateFrom, dateTo, date)) {
                    int lastSpace = data[j].lastIndexOf(' ');
                    int preLastSpace = data[j].substring(0, lastSpace).lastIndexOf(' ');
                    salary += Integer.parseInt(data[j].substring(lastSpace + 1))
                            * Integer.parseInt(data[j].substring(preLastSpace + 1, lastSpace));
                }
            }
            report.append(names[i]).append(" - ").append(String.valueOf(salary));
            if (i != names.length - 1) {
                report.append("\n");
            }
        }

        return report.toString();
    }

    private boolean isInRange(String dateFrom, String dateTo, String currentDate) {
        int dayFrom = Integer.parseInt(dateFrom.substring(0, 2));
        int monthFrom = Integer.parseInt(dateFrom.substring(3, 5));
        int yearFrom = Integer.parseInt(dateFrom.substring(6));

        int dayTo = Integer.parseInt(dateTo.substring(0, 2));
        int monthTo = Integer.parseInt(dateTo.substring(3, 5));
        int yearTo = Integer.parseInt(dateTo.substring(6));

        int day = Integer.parseInt(currentDate.substring(0, 2));
        int month = Integer.parseInt(currentDate.substring(3, 5));
        int year = Integer.parseInt(currentDate.substring(6));

        boolean isGreaterFrom = false;
        boolean isLesserTo = false;

        if (
                year > yearFrom
                        || year == yearFrom && month > monthFrom
                        || year == yearFrom && month == monthFrom && day >= dayFrom
        ) {
            isGreaterFrom = true;
        }

        if (
                year < yearTo
                        || year == yearTo && month < monthTo
                        || year == yearTo && month == monthTo && day <= dayTo
        ) {
            isLesserTo = true;
        }

        return isGreaterFrom && isLesserTo;
    }
}

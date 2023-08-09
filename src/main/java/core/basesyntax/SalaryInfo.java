package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder info = new StringBuilder("Report for period ");
        info.append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            int tempSalaryInMonth = 0;
            for (int j = 0; j < data.length; j++) {
                String[] tempSpaceSeparatorData = data[j].split(" ");
                String[] tempPointSeparatorData = tempSpaceSeparatorData[0].split("\\.");

                String[] tempSpaceSeparatorDateFrom = dateFrom.split(" ");
                String[] tempPointSeparatorDateFrom = tempSpaceSeparatorDateFrom[0].split("\\.");

                String[] tempSpaceSeparatorDateTo = dateTo.split(" ");
                String[] tempPointSeparatorDateTo = tempSpaceSeparatorDateTo[0].split("\\.");

                int tempYearDateFrom = Integer.parseInt(tempPointSeparatorDateFrom[2]);
                int tempMonthDateFrom = Integer.parseInt(tempPointSeparatorDateFrom[1]);
                int tempDayDateFrom = Integer.parseInt(tempPointSeparatorDateFrom[0]);

                int tempYearDateTo = Integer.parseInt(tempPointSeparatorDateTo[2]);
                int tempMonthDateTo = Integer.parseInt(tempPointSeparatorDateTo[1]);
                int tempDayDateTo = Integer.parseInt(tempPointSeparatorDateTo[0]);

                int tempYearData = Integer.parseInt(tempPointSeparatorData[2]);
                int tempMonthData = Integer.parseInt(tempPointSeparatorData[1]);
                int tempDayData = Integer.parseInt(tempPointSeparatorData[0]);

                if (compareDate(tempYearData, tempYearDateFrom, tempYearDateTo, tempMonthData,
                        tempMonthDateFrom, tempMonthDateTo, tempDayData,
                        tempDayDateFrom, tempDayDateTo)) {
                    if (data[j].contains(names[i])) {
                        int tempHourInThatDay = Integer.parseInt(tempSpaceSeparatorData[2]);
                        int tempSalaryPerHourInThatDay = Integer
                                .parseInt(tempSpaceSeparatorData[3]);

                        int tempSalarySumInThatDay = tempHourInThatDay * tempSalaryPerHourInThatDay;
                        tempSalaryInMonth += tempSalarySumInThatDay;
                    }
                }
                if (j == data.length - 1) {
                    info.append(System.lineSeparator()).append(names[i])
                                    .append(" - ").append(tempSalaryInMonth);
                }
            }
        }
        return info.toString();
    }

    public static boolean compareDate(int year, int yearFrom, int yearTo,
                                      int month, int monthFrom, int monthTo,
                                      int day,int dayFrom, int dayTo) {

        if (year > yearFrom && year < yearTo) {
            return true;
        }
        if (!(year >= yearFrom && year <= yearTo)) {
            return false;
        }
        if (year == yearFrom && year != yearTo) {
            if (month > monthFrom) {
                return true;
            }
            if (month == monthFrom && day >= dayFrom) {
                return true;
            }
        }
        if (year == yearTo && year != yearFrom) {
            if (month < monthTo) {
                return true;
            }
            if (month == monthTo && day <= dayTo) {
                return true;
            }
        }
        if (yearFrom == yearTo) {
            if (month > monthFrom && month < monthTo) {
                return true;
            }
            if (month == monthFrom && month != monthTo && day >= dayFrom) {
                return true;
            }
            if (month == monthTo && month != monthFrom && day <= dayTo) {
                return true;
            }
            if (monthFrom == monthTo && day >= dayFrom && day <= dayTo) {
                return true;
            }
        }
        return false;
    }
}

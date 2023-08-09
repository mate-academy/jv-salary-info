package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder info = new StringBuilder("Report for period ");
        info.append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            int tempSalaryInMonth = 0;
            for (int j = 0; j < data.length; j++) {
                String[] tempSpaceSeparator_DATA = data[j].split(" ");
                String[] tempPointSeparator_DATA = tempSpaceSeparator_DATA[0].split("\\.");

                String[] tempSpaceSeparator_dateFrom = dateFrom.split(" ");
                String[] tempPointSeparator_dateFrom = tempSpaceSeparator_dateFrom[0].split("\\.");

                String[] tempSpaceSeparator_dateTo = dateTo.split(" ");
                String[] tempPointSeparator_dateTo = tempSpaceSeparator_dateTo[0].split("\\.");

                int tempYear_DateFrom = Integer.parseInt(tempPointSeparator_dateFrom[2]);
                int tempMonth_DateFrom = Integer.parseInt(tempPointSeparator_dateFrom[1]);
                int tempDay_DateFrom = Integer.parseInt(tempPointSeparator_dateFrom[0]);

                int tempYear_DateTo = Integer.parseInt(tempPointSeparator_dateTo[2]);
                int tempMonth_DateTo = Integer.parseInt(tempPointSeparator_dateTo[1]);
                int tempDay_DateTo = Integer.parseInt(tempPointSeparator_dateTo[0]);

                int tempYear_DATA = Integer.parseInt(tempPointSeparator_DATA[2]);
                int tempMonth_DATA = Integer.parseInt(tempPointSeparator_DATA[1]);
                int tempDay_DATA = Integer.parseInt(tempPointSeparator_DATA[0]);

                if (compareDate(tempYear_DATA, tempYear_DateFrom, tempYear_DateTo, tempMonth_DATA, tempMonth_DateFrom, tempMonth_DateTo, tempDay_DATA, tempDay_DateFrom, tempDay_DateTo)) {
                    if (data[j].contains(names[i])) {
                        int tempHourInThatDay = Integer.parseInt(tempSpaceSeparator_DATA[2]);
                        int tempSalaryPerHourInThatDay = Integer.parseInt(tempSpaceSeparator_DATA[3]);

                        int tempSalarySumInThatDay = tempHourInThatDay * tempSalaryPerHourInThatDay;
                        tempSalaryInMonth += tempSalarySumInThatDay;
                    }
                }
                if (j == data.length - 1) {
                    info.append(System.lineSeparator()).append(names[i]).append(" - ").append(tempSalaryInMonth);
                }
            }
        }
        return info.toString();
    }

    public static boolean compareDate(int year, int yearFrom, int yearTo, int month, int monthFrom, int monthTo, int day,int dayFrom, int dayTo) {

        if(year > yearFrom  && year < yearTo){ return true; }
        if(!(year >= yearFrom  && year <= yearTo)){ return false; }
        if(year == yearFrom && year != yearTo){
            if(month > monthFrom){ return true; }
            if(month == monthFrom && day >= dayFrom){ return true; }
        }
        if(year == yearTo && year != yearFrom){
            if(month < monthTo){ return true; }
            if(month == monthTo && day <= dayTo){ return true; }        }
        if(yearFrom == yearTo){
            if(month > monthFrom && month < monthTo){ return true; }
            if(month == monthFrom && month != monthTo && day >= dayFrom){ return true; }
            if(month == monthTo && month != monthFrom && day <= dayTo){ return true; }
            if(monthFrom == monthTo && day >= dayFrom && day <= dayTo){ return true; }
        }
        return false;
    }
}

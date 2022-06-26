package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period ");
        salaryInfo.append(dateFrom).append(" - ").append(dateTo);
        int[] salaryCount = new int[names.length];
        double dateFrom_MonthDay = dateToMonthDayFormat(dateFrom.substring(3,6), dateFrom.substring(0,2));
        double dateTo_MonthDay = dateToMonthDayFormat(dateTo.substring(3,6), dateTo.substring(0,2));
        for (String personData: data) {
            double personDateMonthDay = dateToMonthDayFormat(personData.substring(3,6), personData.substring(0,2));
            if (dateFrom_MonthDay <= personDateMonthDay && personDateMonthDay <= dateTo_MonthDay ) {
                String[] arrayPersonData = personData.split(" ");
                for (int j = 0; j < names.length; j++) {
                    if (arrayPersonData[1].equals(names[j])) {
                        salaryCount[j] += Integer.parseInt(arrayPersonData[2]) * Integer.parseInt(arrayPersonData[3]);
                        j = names.length;
                    }
                }
            }
        }
        for (int j = 0; j < names.length; j++) {
            salaryInfo.append(System.lineSeparator()).append(names[j]).append(" - ").append(salaryCount[j]);
        }
        return salaryInfo.toString();
    }

    public double dateToMonthDayFormat(String monthOfDate, String dayOfDate) {
        return Double.parseDouble(monthOfDate + dayOfDate);
    }
}

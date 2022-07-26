package core.basesyntax;

public class SalaryInfo {
    private static final short NAME_INDEX = 0;
    private static final short SALARY_INDEX = 1;
    private static final short DATE_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        String[][] salary = new String[names.length][2];
        for (int i = 0; i < names.length; i++) {
            salary[i][NAME_INDEX] = names[i];
            salary[i][SALARY_INDEX] = "0";
        }
        int digitDateFrom = this.digitalDate(dateFrom);
        int digitDateTo = this.digitalDate(dateTo);
        for (String dat: data) {
            if (digitDateFrom <= this.digitalDate(dat.split(" ")[DATE_INDEX])
                    && digitDateTo >= this.digitalDate(dat.split(" ")[DATE_INDEX])) {
                for (int i = 0; i < salary.length; i++) {
                    /** Index "1" - date of working hours
                    Index "2" - working hours
                    Index "3" - hourly pay **/
                    if (salary[i][NAME_INDEX].equals(dat.split(" ")[1])) {
                        salary[i][1] = String.valueOf(Integer.parseInt(salary[i][1])
                                + (Integer.parseInt(dat.split(" ")[2])
                                * Integer.parseInt(dat.split(" ")[3])));
                    }
                }
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < salary.length; i++) {
            report.append(System.lineSeparator())
                    .append(salary[i][0])
                    .append(" - ")
                    .append(salary[i][1]);
        }
        return report.toString();
    }

    private int digitalDate(String date) {
        String[] stringDate = date.split("\\.");
        if (stringDate.length == 0) {
            return 0;
        }
        int day = Integer.valueOf(stringDate[0]);
        int mounth = Integer.valueOf(stringDate[1]);
        int year = Integer.valueOf(stringDate[2]);
        int res = 0;
        for (int i = 1; i < mounth; i++) {
            res += (28 + (i + i / 8) % 2 + 2 % i + 1 / i * 2) + 1;
            //formula for calculating the number of days in a month
        }
        res += day + (year * 365);
        return res;
    }
}

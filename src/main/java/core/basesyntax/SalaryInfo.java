package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[][] salary = new String[names.length][2];
        for (int i = 0; i < names.length; i++) {
            salary[i][0] = names[i];
            salary[i][1] = "0";
        }
        int digitDateFrom = this.digitalDate(dateFrom);
        int digitDateTo = this.digitalDate(dateTo);
        for (String dat: data) {
            if (digitDateFrom <= this.digitalDate(dat.split(" ")[0])
                    && digitDateTo >= this.digitalDate(dat.split(" ")[0])) {
                for (int i = 0; i < salary.length; i++) {
                    if (salary[i][0].equals(dat.split(" ")[1])) {
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
        int res = day
                + (mounth * 31)
                + (year * 365);
        return res;
    }
}

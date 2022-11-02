package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                                                    .append(dateFrom).append(" - ")
                                                    .append(dateTo);
        int[] salary = new int[names.length];
        for (int i = 0; i < salary.length; i++) {
            for (String info : data) {
                String[] splitedInfo = info.split(" ");
                if (names[i].equals(splitedInfo[1])
                        && compareDates(splitedInfo[0], dateFrom, dateTo)) {
                    salary[i] += (Integer.parseInt(splitedInfo[2])
                                * Integer.parseInt(splitedInfo[3]));
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return builder.toString();
    }

    private boolean compareDates(String workDate, String dateFrom, String dateTo) {
        String[] splitedWorkDate = workDate.split("\\.");
        String[] splitedDateFrom = dateFrom.split("\\.");
        String[] splitedDateTo = dateTo.split("\\.");
        int daysInWorkDate = countDaysInDate(splitedWorkDate);
        int daysInDateFrom = countDaysInDate(splitedDateFrom);
        int daysInDateTo = countDaysInDate(splitedDateTo);
        return daysInWorkDate >= daysInDateFrom && daysInWorkDate <= daysInDateTo ? true : false;
    }

    private int countDaysInDate(String[] date) {
        return Integer.parseInt(date[0])
            + (Integer.parseInt(date[1]) * 30)
            + (Integer.parseInt(date[2]) * 365);
    }
}

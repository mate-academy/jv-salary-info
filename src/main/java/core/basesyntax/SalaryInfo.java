package core.basesyntax;

public class SalaryInfo {
    private String normalizeDate(String date) {
        String[] parts = date.split("\\.");
        return parts[2] + parts[1] + parts[0];
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] totalSalaries = new int[names.length];
        String from = this.normalizeDate(dateFrom);
        String to = this.normalizeDate(dateTo);

        for(String record : data) {
            String[] parts = record.split("\\s+");
            String recordDate = this.normalizeDate(parts[0]);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);
            if (recordDate.compareTo(from) >= 0 && recordDate.compareTo(to) <= 0) {
                for(int i = 0; i < names.length; ++i) {
                    if (names[i].equals(name)) {
                        totalSalaries[i] += hours * rate;
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for(int i = 0; i < names.length; ++i) {
            sb.append("\n").append(names[i]).append(" - ").append(totalSalaries[i]);
        }

        return sb.toString().trim();
    }
}


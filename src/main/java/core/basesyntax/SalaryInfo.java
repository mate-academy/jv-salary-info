package core.basesyntax;

class SalaryInfo {
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        String sep = System.lineSeparator();
        
        int[] from = parseDate(dateFrom);
        int[] to = parseDate(dateTo);
        
        for (String entry : data) {
            String[] parts = entry.split(" ");
            String dayStr = parts[0];
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);
            
            int[] current = parseDate(dayStr);
            
            if (compareDates(current, from) >= 0 && compareDates(current, to) <= 0) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * rate;
                    }
                }
            }
        }
        
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(sep);
        
        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
            if (i < names.length - 1) {
                result.append(sep);
            }
        }
        
        return result.toString();
    }
    
    private int[] parseDate(String date) {
        String[] parts = date.split("\\.");
        return new int[]{
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[0])
        };
    }
    
    private int compareDates(int[] a, int[] b) {
        if (a[0] != b[0]) {
            return a[0] - b[0];
        }
        if (a[1] != b[1]) {
            return a[1] - b[1];
        }
        return a[2] - b[2];
    }
}

package core.basesyntax;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserInfo {
    private Map<String, Integer> salaryMap = new LinkedHashMap<>();

    public void addUserAndSalary(String userName, int salary) {
        salaryMap.put(userName, salary);
    }

    public void addSalary(String userName, int salary) {
        salaryMap.merge(userName, salary, Integer::sum);
    }

    public String getAllUsers(String[] names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int salary = salaryMap.getOrDefault(name, 0);
            if (i == names.length - 1) {
                sb.append(name).append(" - ").append(salary);
            } else {
                sb.append(name).append(" - ").append(salary).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public boolean hasUser(String userName) {
        return salaryMap.containsKey(userName);
    }
}

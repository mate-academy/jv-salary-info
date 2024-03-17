package core.basesyntax;

public class UserReportSupplier {
    private final User user;
    public UserReportSupplier(User user) {
        this.user = user;
    }
    public String createUserReportTextLine() {
        return System.lineSeparator() + user.getUserName() + " - " + user.getSalaryAmount();
    }
}


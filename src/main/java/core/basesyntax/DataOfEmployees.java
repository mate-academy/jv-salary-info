package core.basesyntax;

import java.time.LocalDate;

public class DataOfEmployees {
  private LocalDate date;
  private String name;
  private int workedHours;
  private int salaryRate;

  public DataOfEmployees(LocalDate date, String name, int workedHours, int salaryRate) {
    this.date = date;
    this.name = name;
    this.workedHours = workedHours;
    this.salaryRate = salaryRate;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getName() {
    return name;
  }

  public int getWorkedHours() {
    return workedHours;
  }

  public int getSalaryRate() {
    return salaryRate;
  }
}

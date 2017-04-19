package by.stqa.pft.mantis.model;

/**
 * Created by Artsiom on 4/19/2017.
 */
public class Project {

  private int id;
  private String name;
  private String status;

  public String getStatus() {
    return status;
  }

  public Project withStatus(String status) {
    this.status = status;
    return this;
  }

  public int getId() {
    return id;
  }

  public Project withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }
}
package by.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by Artsiom on 4/17/2017.
 */

@Entity
@Table (name = "mantis_user_table")

public class UserData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Column(name = "username")
  private String name;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withName(String name) {
    this.name = name;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserData userData = (UserData) o;

    if (id != userData.id) return false;
    if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
    if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
    return password != null ? password.equals(userData.password) : userData.password == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }
}

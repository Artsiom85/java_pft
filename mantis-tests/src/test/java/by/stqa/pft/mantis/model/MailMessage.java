package by.stqa.pft.mantis.model;

/**
 * Created by Artsiom on 4/15/2017.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage (String to, String text) {
    this.to = to;
    this.text = text;
  }
}

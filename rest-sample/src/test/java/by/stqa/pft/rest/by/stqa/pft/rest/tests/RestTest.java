package by.stqa.pft.rest.by.stqa.pft.rest.tests;

import by.stqa.pft.rest.by.stqa.pft.rest.model.Issue;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Artsiom on 4/19/2017.
 */
public class RestTest extends TestBase{

  @Test
  public void testRest() throws IOException {

    skipIfNotFixed(1);
    Set<Issue> oldIssues = getIssue();
    Issue newIssue = new Issue().withSubject("test issue").withDescription("New test issue").withStatus("New");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssue();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
}

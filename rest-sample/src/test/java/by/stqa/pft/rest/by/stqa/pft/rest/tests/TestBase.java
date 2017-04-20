package by.stqa.pft.rest.by.stqa.pft.rest.tests;

import by.stqa.pft.rest.by.stqa.pft.rest.appmanager.RestHelper;
import by.stqa.pft.rest.by.stqa.pft.rest.model.Issue;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Artsiom on 4/19/2017.
 */
public class TestBase extends RestHelper {

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {

    Set<Issue> issues = RestTest.getIssue();
    Issue checkIssue = null;

    for (Issue issue : issues) {
      if (issue.getId() == issueId) {
        checkIssue = issue;
      }
    }
    if ((String.valueOf(checkIssue.getStatus()) == "new")
            || (String.valueOf(checkIssue.getStatus()) == "open")) {
      return false;
    } else return true;
  }
}

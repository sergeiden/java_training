import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

/**
 * Created by serg on 10.05.2017.
 */
public class TestBase {

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public  boolean isIssueOpen(int issueId) throws IOException {
    String issueStatus = getIssueData(issueId).iterator().next().getStatus();
    if (issueStatus.equals("Resolved") || issueStatus.equals("Closed"))
      return false;
    else
      return true;
  }

  private Set<Issue> getIssueData(int id) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/"+id+".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }
}
import android.content.Context;
import com.twansoftware.basedroid.singleton.BasedroidHttpClient;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.HttpResponseGenerator;
import com.xtremelabs.robolectric.tester.org.apache.http.TestHttpResponse;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import roboguice.test.RobolectricRoboTestRunner;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Author: achuinard
 * 4/18/12
 */
@RunWith(RobolectricRoboTestRunner.class)
public class BasedroidHttpClientTest {
    protected Context context = new RoboActivity();
    protected BasedroidHttpClient client = RoboGuice.getInjector(context).getInstance(BasedroidHttpClient.class);

    @Test
    public void testSimpleInjection() {
        assertNotNull(client.getHttpClient());
    }

    /**
     * Tests a sample HTTP call.
     * You don't actually make the HTTP call.
     * You just add in the mock data that you ideally expect from the server.
     * So this tests JSON parsing more than HTTP fetching / your server working.
     */
    @Test
    public void testCheckUsernameExistence() {
        Robolectric.addPendingHttpResponse(new HttpResponseGenerator() {
            @Override
            public HttpResponse getResponse(final HttpRequest httpRequest) {
                return new TestHttpResponse(200, "{'bool':'true'}");
            }
        });

        final boolean usernameExists = client.speakbinUserExists("achuinard");
        assertTrue(usernameExists);
    }
}

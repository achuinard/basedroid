import android.content.Context;
import com.twansoftware.basedroid.entity.SamplePojo;
import com.twansoftware.basedroid.singleton.BasedroidStateManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import roboguice.test.RobolectricRoboTestRunner;

import java.util.UUID;

import static junit.framework.Assert.assertEquals;

/**
 * Author: achuinard
 * 4/23/12
 */
@RunWith(RobolectricRoboTestRunner.class)
public class BasedroidStateManagerTest {
    protected Context context = new RoboActivity();
    protected BasedroidStateManager basedroidStateManager = RoboGuice.getInjector(context).getInstance(BasedroidStateManager.class);
    
    @Test
    public void testSaveAndLoadString() {
        final String key = "testSaveStringMethod()";
        final String value = UUID.randomUUID().toString();
        basedroidStateManager.saveString(key, value);
        assertEquals(value, basedroidStateManager.loadString(key));
    }

    @Test
    public void testSaveAndLoadInteger() {
        final String key = "testSaveIntegerMethod()";
        final Integer value = UUID.randomUUID().toString().hashCode();
        basedroidStateManager.saveInteger(key, value);
        assertEquals(value, basedroidStateManager.loadInteger(key));
    }
    
    @Test
    public void testSaveAndLoadPojo() {
        final String stringOne = "Tony";
        final Integer intOne = 21;
        final SamplePojo samplePojo = new SamplePojo(stringOne, intOne);
        final String key = "testSaveAndLoadPojo()";
        basedroidStateManager.saveObject(key, samplePojo);
        final SamplePojo loaded = (SamplePojo) basedroidStateManager.loadObject(key, SamplePojo.class);
        assertEquals(samplePojo.getIntOne(), loaded.getIntOne());
    }
}

import android.content.Context;
import com.twansoftware.basedroid.entity.SamplePojo;
import com.twansoftware.basedroid.singleton.StateManager;
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
public class StateManagerTest {
    protected Context context = new RoboActivity();
    protected StateManager stateManager = RoboGuice.getInjector(context).getInstance(StateManager.class);
    
    @Test
    public void testSaveAndLoadString() {
        final String key = "testSaveStringMethod()";
        final String value = UUID.randomUUID().toString();
        stateManager.saveString(key, value);
        assertEquals(value, stateManager.loadString(key));
    }

    @Test
    public void testSaveAndLoadInteger() {
        final String key = "testSaveIntegerMethod()";
        final Integer value = UUID.randomUUID().toString().hashCode();
        stateManager.saveInteger(key, value);
        assertEquals(value, stateManager.loadInteger(key));
    }
    
    @Test
    public void testSaveAndLoadPojo() {
        final String stringOne = "Tony";
        final Integer intOne = 21;
        final SamplePojo samplePojo = new SamplePojo(stringOne, intOne);
        final String key = "testSaveAndLoadPojo()";
        stateManager.saveObject(key, samplePojo);
        final SamplePojo loaded = (SamplePojo) stateManager.loadObject(key, SamplePojo.class);
        assertEquals(samplePojo.getIntOne(), loaded.getIntOne());
    }
}

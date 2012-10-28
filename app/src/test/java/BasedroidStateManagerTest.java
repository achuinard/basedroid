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
        final UUID randomUuid = UUID.randomUUID();
        basedroidStateManager.saveRandomUuid(randomUuid);
        assertEquals(randomUuid, basedroidStateManager.getSavedRandomUuid());
    }

    @Test
    public void testSaveAndLoadInteger() {
        final Integer value = UUID.randomUUID().toString().hashCode();
        basedroidStateManager.saveStupidInteger(value);
        assertEquals(value, basedroidStateManager.getSavedStupidInteger());
    }
    
    @Test
    public void testSaveAndLoadPojo() {
        final String stringOne = "Tony";
        final Integer intOne = 21;
        final SamplePojo samplePojo = new SamplePojo(stringOne, intOne);
        basedroidStateManager.saveImportantPojo(samplePojo);
        final SamplePojo loaded = basedroidStateManager.getSavedImportantPojo();
        assertEquals(samplePojo, loaded);
    }
}

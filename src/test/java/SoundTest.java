import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import ua.od.maxz.Sound;

import static org.junit.Assert.fail;

/**
 * Example how to use 'Sound' with JUnit.
 * User: maxz
 * Date: 08.10.2017
 */
public class SoundTest {

    /**
     * All you need is this.
     */
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            Sound.fail();
        }

        @Override
        protected void succeeded(Description description) {
            Sound.ok();
        }
    };

    @Test
    public void testSuccess() {
        // will play sound 'ok'
    }

    @Test
    public void testFail() {
        // will play sound 'fail'
        fail();
    }

}

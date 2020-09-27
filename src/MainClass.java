import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

public class MainClass {
    public static final Logger logMain=Logger.getLogger(MainClass.class);
    public static void main(String[] args) {
        logMain.info("Application started");
        if (logMain.isDebugEnabled()) {
            logMain.debug("logMain: In debug message");
        }
        disableWarning();
        GUI newFrame=new GUI();
        newFrame.setSizeAndLocation();
        newFrame.setVisible(true);
        logMain.info("Application finished");
    }
    public static void disableWarning() {
        try {
            Field theUnsafe = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            sun.misc.Unsafe u = (sun.misc.Unsafe)theUnsafe.get(null);
            Class<?> cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }
}

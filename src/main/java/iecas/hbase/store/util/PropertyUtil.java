//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package iecas.hbase.store.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    public PropertyUtil() {
    }

    public static Properties createProperties(String path) {
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}

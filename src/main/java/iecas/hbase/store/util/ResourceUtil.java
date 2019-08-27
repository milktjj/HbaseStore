//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package iecas.hbase.store.util;

import org.geowebcache.io.ByteArrayResource;
import org.geowebcache.storage.TileObject;

public class ResourceUtil {
    public ResourceUtil() {
    }

    public static void setResource(TileObject tileObject, byte[] data) {
        ByteArrayResource resource = new ByteArrayResource(data);
        tileObject.setBlob(resource);
        tileObject.setBlobSize((int)resource.getSize());
        tileObject.setCreated(resource.getLastModified());
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package iecas.hbase.store.blobstore;

import org.geowebcache.storage.BlobStore;
import org.geowebcache.storage.BlobStoreListener;
import org.geowebcache.storage.StorageException;
import org.geowebcache.storage.TileObject;
import org.geowebcache.storage.TileRange;

public class NullBlobStore implements BlobStore {
    public NullBlobStore() {
    }

    public boolean delete(String var1) throws StorageException {
        throw new UnsupportedOperationException("Delete Failed");
    }

    public boolean deleteByGridsetId(String var1, String var2) throws StorageException {
        throw new UnsupportedOperationException("deleteByGridsetId Failed");
    }

    public boolean delete(TileObject var1) throws StorageException {
        throw new UnsupportedOperationException("Delete Failed");
    }

    public boolean delete(TileRange var1) throws StorageException {
        throw new UnsupportedOperationException("Delete Failed");
    }

    public boolean get(TileObject var1) {
        throw new UnsupportedOperationException("Get Failed");
    }

    public void put(TileObject var1) {
        throw new UnsupportedOperationException("Put Failed");
    }

    /** @deprecated */
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException("public void clear()");
    }

    public void destroy() {
        throw new UnsupportedOperationException("public void destroy()");
    }

    public void addListener(BlobStoreListener var1) {
    }

    public boolean removeListener(BlobStoreListener var1) {
        System.out.println("public boolean removeListener(BlobStoreListener var1)");
        return true;
    }

    public boolean rename(String var1, String var2) throws StorageException {
        throw new UnsupportedOperationException("boolean rename(String var1, String var2) throws StorageException");
    }

    public String getLayerMetadata(String var1, String var2) {
        throw new UnsupportedOperationException("public String getLayerMetadata(String var1, String var2)");
    }

    public void putLayerMetadata(String var1, String var2, String var3) {
        throw new UnsupportedOperationException("public void putLayerMetadata(String var1, String var2, String var3)");
    }

    public boolean layerExists(String var1) {
        throw new UnsupportedOperationException("public boolean layerExists(String var1)");
    }
}

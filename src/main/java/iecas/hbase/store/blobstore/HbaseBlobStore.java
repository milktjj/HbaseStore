//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package iecas.hbase.store.blobstore;

import iecas.hbase.store.configuration.HadoopConfiguration;
import iecas.hbase.store.util.PropertyUtil;
import iecas.hbase.store.util.ResourceUtil;
import iecas.hbase.store.util.hbase.HbaseTableUtil;
import iecas.hbase.store.util.hbase.HbaseWriteAndReadUtil;
import iecas.hbase.store.util.quadTree.QuadTreeUtil;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.PropertyConfigurator;
import org.geowebcache.io.ByteArrayResource;
import org.geowebcache.storage.BlobStore;
import org.geowebcache.storage.TileObject;
import org.geowebcache.storage.StorageObject.Status;

public class HbaseBlobStore extends NullBlobStore implements BlobStore {
    private static final String HBASE_TILE_TABLE = "hbase_tile_table";
    private static Log log = LogFactory.getLog(HbaseBlobStore.class);
    private Connection connection;
    private Configuration configuration;
    private Properties properties = PropertyUtil.createProperties("setting.properties");

    public HbaseBlobStore() throws IOException {
        PropertyConfigurator.configure(this.properties.getProperty("log4jpath"));
        this.configuration = HadoopConfiguration.getConfigurationInstance();
        this.configuration.set("hbase.zookeeper.qiorum", this.properties.getProperty("zooquorum"));
        this.configuration.set("hbase.client.ipc.pool.size", this.properties.getProperty("hbase_pool_size"));
        this.connection = ConnectionFactory.createConnection(this.configuration);
    }

    public boolean delete(String layerName) {
        HBaseAdmin admin = null;
        boolean ret = false;
        try {
            admin = new HBaseAdmin(this.configuration);
            assert admin.tableExists(layerName);
            admin.disableTable(layerName);
            admin.deleteTable(layerName);
            ret = !admin.tableExists(layerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean get(TileObject tileObject) {
        String dataKey = tileObject.getLayerName() + QuadTreeUtil.xyz2QuadTreeCode(tileObject.getXYZ());
        Result result = null;
        Table table = null;

        try {
            table = HbaseTableUtil.getTable(this.connection, HBASE_TILE_TABLE);
            result = HbaseWriteAndReadUtil.getResult(table, dataKey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (result != null && !result.isEmpty()) {
            byte[] data = result.getValue(Bytes.toBytes("cf"), Bytes.toBytes("temp"));
            ResourceUtil.setResource(tileObject, data);
            log.info("Read data from hbase:" + dataKey);
            return true;
        } else {
            tileObject.setStatus(Status.MISS);
            return false;
        }
    }

    public void put(TileObject tileObject) {
        PropertyConfigurator.configure(this.properties.getProperty("log4jpath"));
        Table table = null;

        try {
            table = HbaseTableUtil.getTable(this.connection, HBASE_TILE_TABLE);
            String dataKey = tileObject.getLayerName() + QuadTreeUtil.xyz2QuadTreeCode(tileObject.getXYZ());
            Put put = new Put(Bytes.toBytes(dataKey));
            byte[] data = ((ByteArrayResource)tileObject.getBlob()).getContents();
            long time = System.currentTimeMillis();
            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("temp"), time, data);
            table.put(put);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void put() {
        PropertyConfigurator.configure(this.properties.getProperty("log4jpath"));
        Table table = null;

        try {
            table = HbaseTableUtil.getTable(this.connection, HBASE_TILE_TABLE);
            String dataKey = "ttttt";
            Put put = new Put(Bytes.toBytes(dataKey));
            byte[] data = dataKey.getBytes();
            long time = System.currentTimeMillis();
            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("temp"), time, data);
            table.put(put);
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {
        HbaseBlobStore blobStore = new HbaseBlobStore();
        blobStore.put();
    }
}

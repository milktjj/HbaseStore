//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package iecas.hbase.store.util.hbase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceNotFoundException;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Table;

public class HbaseTableUtil {
    private static final Log log = LogFactory.getLog(HbaseTableUtil.class);

    public HbaseTableUtil() {
    }

    public static Table getTable(Connection connection, String tableName) throws Exception {
        TableName tableName1 = TableName.valueOf(tableName);
        log.info("===========" + tableName + "-----------------");
        HBaseAdmin admin = new HBaseAdmin(connection);
        if (!admin.tableExists(tableName1)) {
            createTable(connection, TableName.valueOf(tableName), new String[]{"cf"});
        }

        return connection.getTable(tableName1);
    }

    private static void createTable(Connection connection, TableName tableName, String[] columnFamilies) throws Exception {
        HBaseAdmin admin = new HBaseAdmin(connection);
        String namespace = tableName.getNamespaceAsString();
        HTableDescriptor descriptor = new HTableDescriptor(tableName);
        String[] cFs = columnFamilies;
        int length = columnFamilies.length;

        for(int i = 0; i < length; ++i) {
            String columnFamily = cFs[i];
            descriptor.addFamily(new HColumnDescriptor(columnFamily));
        }

        try {
            admin.createTable(descriptor);
        } catch (NamespaceNotFoundException e) {
            admin.createNamespace(NamespaceDescriptor.create(namespace).build());
            admin.createTable(descriptor);
        } catch (TableExistsException e) {
            e.printStackTrace();
        }

    }

}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package iecas.hbase.store.util.hbase;

import java.io.IOException;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseWriteAndReadUtil {
    public HbaseWriteAndReadUtil() {
    }

    public static void writeByteArray2Hbase(byte[] data, Table table, String row, String columnFamily, String column) throws IOException {
        Put put = new Put(Bytes.toBytes(row));
        long time = System.currentTimeMillis();
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), time, data);
        table.put(put);
    }

    public static void writeByteArray2Hbase(byte[] data, Connection connection, String tableName, String row, String columnFamily, String column) throws IOException {
        Table table = null;

        try {
            table = HbaseTableUtil.getTable(connection, tableName);
            writeByteArray2Hbase(data, table, row, columnFamily, column);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            table.close();
        }

    }

    public static Result getResult(Table table, String row) throws IOException {
        Get get = new Get(Bytes.toBytes(row));
        Result result = table.get(get);
        return result;
    }
}
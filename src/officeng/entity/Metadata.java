package officeng.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import officeng.db.ConnectionManager;

public class Metadata {
	public class Entity{
		public long fid;
		public String key;
		public String value;
	}

	public long add(long fid, String key, String value){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `metadata` (`fid`, `key`, `value`) values (?, ? , ?);");
            ps.setLong(1, fid);
            ps.setString(2, key);
            ps.setString(3, value);
            int res = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
              result = rs.getLong(1);
            }
        }   
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    public boolean edit(long mdid, long fid, String key, String value){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `metadata` set `fid`=?, `key`=?, `value`=? where `mdid`=?;");
            ps.setLong(1, fid);
            ps.setString(2, key);
            ps.setString(3, value);
            ps.setLong(5, mdid);
            int res = ps.executeUpdate();
            result = res == 1;
        }   
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    public boolean remove(long mdid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `metadata` where `mdid`=?;");
            ps.setLong(1, mdid);
            int res = ps.executeUpdate();
            result = res == 1;
        }   
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    
    public List<Metadata.Entity> list(Long mdid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Metadata.Entity> result = new ArrayList<Metadata.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `fid`, `key`, `value` from `metadata` where mdid=?;");
            ps.setLong(1, mdid);
            rs = ps.executeQuery();
            while(rs.next()){
                Metadata.Entity e = new Metadata.Entity();
                e.fid = rs.getLong(1);
                e.key = rs.getString(2);
                e.value = rs.getString(3);
                result.add(e);
            }
        }   
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
        
        return result;
    }
}

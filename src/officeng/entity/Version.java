package officeng.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import officeng.db.ConnectionManager;

public class Version {
	public class Entity{
		public long owner;
		public long fid;
		public String name;
		public String path;
		public String size;
		public String description;
	}
	
	public long add(long owner, long fid, String name, String path, String size, String description){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `versions` (`owner`, `fid`, `name`, `path`, `size`, `description`) values (?, ?, ?, ?, ?, ?);");
            ps.setLong(1, owner);
            ps.setLong(2, fid);
            ps.setString(3, name);
            ps.setString(4, path);
            ps.setString(5, size);
            ps.setString(6, description);
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
    
    public boolean edit(long vid, long owner, long fid, String name, String path, String size, String description){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `versions` set `owner`=?, `fid`=?, `name`=?, `path`=?, `size`=?, `description`=? where `vid`=?;");
            ps.setLong(1, owner);
            ps.setLong(2, fid);
            ps.setString(3, name);
            ps.setString(4, path);
            ps.setString(5, size);
            ps.setString(6, description);
            ps.setLong(7, vid);
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
    
    public boolean remove(long vid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `versions` where `vid`=?;");
            ps.setLong(1, vid);
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
    
    
    public List<Version.Entity> list(Long vid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Version.Entity> result = new ArrayList<Version.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `owner`, `fid`, `name`, `path`, `size`, `description` from `versions` where vid=?;");
            ps.setLong(1, vid);
            rs = ps.executeQuery();
            while(rs.next()){
                Version.Entity e = new Version.Entity();
                e.owner = rs.getLong(1);
                e.fid = rs.getLong(2);
                e.name = rs.getString(3);
                e.path = rs.getString(4);
                e.size = rs.getString(5);
                e.description = rs.getString(6);
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

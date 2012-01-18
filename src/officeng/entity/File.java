package officeng.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import officeng.db.ConnectionManager;


public class File {
	public class Entity{
		public long owner;
		public String name;
		public String type;
		public String mime;
	}
	public long add(long owner, String name, String type, String mime){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `files` (`owner`, `name`, `type`, `mime`) values (?, ? , ?, ?);");
            ps.setLong(1, owner);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setString(4, mime);
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
    
    public boolean edit(long fid, long owner, String name, String type, String mime){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `files` set `owner`=?, `name`=?, `type`=?, `mime`=? where `fid`=?;");
            ps.setLong(1, owner);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setString(4, mime);
            ps.setLong(5, fid);
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
    
    public boolean remove(long fid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `users` where `fid`=?;");
            ps.setLong(1, fid);
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
    
    
    public List<File.Entity> list(Long fid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<File.Entity> result = new ArrayList<File.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `owner`, `name`, `type`, `mime`, from `files` where fid=?;");
            ps.setLong(1, fid);
            rs = ps.executeQuery();
            while(rs.next()){
                File.Entity e = new File.Entity();
                e.owner = rs.getLong(1);
                e.name = rs.getString(2);
                e.type = rs.getString(3);
                e.mime = rs.getString(4);
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


package officeng.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import officeng.db.ConnectionManager;

public class Group {
	public class Entity{
		public long owner;
		public String name;
	}
	
	public long add(long owner, String name){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `groups` (`owner`, `name`) values (?, ?);");
            ps.setLong(1, owner);
            ps.setString(2, name);
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
    
    public boolean edit(long gid, long owner, String name){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `groups` set `owner`=?, `name`=? where `gid`=?;");
            ps.setLong(1, owner);
            ps.setString(2, name);
            ps.setLong(4, gid);
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
    
    public boolean remove(long gid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `groups` where `gid`=?;");
            ps.setLong(1, gid);
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
    
    
    public List<Group.Entity> list(Long gid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Group.Entity> result = new ArrayList<Group.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `owner`, `name` from `groups` where gid=?;");
            ps.setLong(1, gid);
            rs = ps.executeQuery();
            while(rs.next()){
                Group.Entity e = new Group.Entity();
                e.owner = rs.getLong(1);
                e.name = rs.getString(2);
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

package officeng.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import officeng.db.ConnectionManager;

public class Note {
	public class Entity{
		public long owner;
		public long vid;
		public String note;
	}
	
	public long add(long owner, long vid, String note){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `notes` (`owner`, `vid`, `note`) values (?, ?, ?);");
            ps.setLong(1, owner);
            ps.setLong(2, vid);
            ps.setString(3, note);
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
    
    public boolean edit(long nid, long owner, long vid, String note){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `notes` set `owner`=?, `vid`=?, `note`=? where `nid`=?;");
            ps.setLong(1, owner);
            ps.setLong(2, vid);
            ps.setString(3, note);
            ps.setLong(4, nid);
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
    
    public boolean remove(long nid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `notes` where `nid`=?;");
            ps.setLong(1, nid);
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
    
    
    public List<Note.Entity> list(Long nid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Note.Entity> result = new ArrayList<Note.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `owner`, `vid`, `note` from `notes` where nid=?;");
            ps.setLong(1, nid);
            rs = ps.executeQuery();
            while(rs.next()){
                Note.Entity e = new Note.Entity();
                e.owner = rs.getLong(1);
                e.vid = rs.getLong(2);
                e.note = rs.getString(3);
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

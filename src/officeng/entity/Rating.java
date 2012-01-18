package officeng.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import officeng.db.ConnectionManager;

public class Rating {
	public class Entity{
		public long owner;
		public long vid;
		public int rating;
	}
	
	public long add(long owner, long vid, int rating){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `ratings` (`owner`, `vid`, `rating`) values (?, ?, ?);");
            ps.setLong(1, owner);
            ps.setLong(2, vid);
            ps.setInt(3, rating);
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
    
    public boolean edit(long rid, long owner, long vid, int rating){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `ratings` set `owner`=?, `vid`=?, `rating`=? where `rid`=?;");
            ps.setLong(1, owner);
            ps.setLong(2, vid);
            ps.setInt(3, rating);
            ps.setLong(4, rid);
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
    
    public boolean remove(long rid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `notes` where `nid`=?;");
            ps.setLong(1, rid);
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
    
    
    public List<Rating.Entity> list(Long rid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Rating.Entity> result = new ArrayList<Rating.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `owner`, `vid`, `rating` from `notes` where rid=?;");
            ps.setLong(1, rid);
            rs = ps.executeQuery();
            while(rs.next()){
                Rating.Entity e = new Rating.Entity();
                e.owner = rs.getLong(1);
                e.vid = rs.getLong(2);
                e.rating = rs.getInt(3);
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

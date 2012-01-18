package officeng.entity;
//TODO determine setChar(), getChar()  alternative.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import officeng.db.ConnectionManager;

public class Member {
	public class Entity{
		public long gid;
		public long uid;
		public char state;
	}
	
	public long add(long gid, long uid, char state){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `members` (`gid`, `uid`, `state`) values (?, ?, ?);");
            ps.setLong(1, gid);
            ps.setLong(2, uid);
            ps.setChar(3, state);
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
    
    public boolean edit(long mid, long gid, long uid, char state){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `members` set `gid`=?, `uid`=?, `state`=? where `mid`=?;");
            ps.setLong(1, gid);
            ps.setLong(2, uid);
            ps.setChar(3, state);
            ps.setLong(4, mid);
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
    
    public boolean remove(long mid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `members` where `mid`=?;");
            ps.setLong(1, mid);
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
    
    
    public List<Member.Entity> list(Long mid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Member.Entity> result = new ArrayList<Member.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `gid`, `uid`, `state` from `members` where mid=?;");
            ps.setLong(1, mid);
            rs = ps.executeQuery();
            while(rs.next()){
                Member.Entity e = new Member.Entity();
                e.gid = rs.getLong(1);
                e.uid = rs.getLong(2);
                e.state = rs.getChar(3);
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

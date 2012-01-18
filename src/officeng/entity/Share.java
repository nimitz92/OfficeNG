package officeng.entity;
//TODO determine setChar(), getChar()  alternative.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import officeng.db.ConnectionManager;

public class Share {
	public class Entity{
		public long fid;
		public long uid;
		public char state;
	}

	public long add(long fid, long uid, char state){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `shares` (`fid`, `uid`, `state`) values (?, ?, ?);");
            ps.setLong(1, fid);
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
    
    public boolean edit(long shid, long fid, long uid, char state){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `shares` set `fid`=?, `uid`=?, `state`=? where `shid`=?;");
            ps.setLong(1, fid);
            ps.setLong(2, uid);
            ps.setChar(3, state);
            ps.setLong(4, shid);
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
    
    public boolean remove(long shid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `shares` where `shid`=?;");
            ps.setLong(1, shid);
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
    
    
    public List<Share.Entity> list(Long shid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Share.Entity> result = new ArrayList<Share.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `fid`, `uid`, `state` from `shares` where shid=?;");
            ps.setLong(1, shid);
            rs = ps.executeQuery();
            while(rs.next()){
                Share.Entity e = new Share.Entity();
                e.fid = rs.getLong(1);
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

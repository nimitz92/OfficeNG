package officeng.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import officeng.db.ConnectionManager;

public class User {
    public class Entity {
        public String email;
        public String name;
        public String title;
        public String phone;
    }
    
    public long add(String email, String password, String name, String title, String phone){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = -1;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("insert into `users` (`email`, `password`, `name`, `title`, `phone`) values (?, MD5(concat(?, ?)), ?, ?, ?);");
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, title);
            ps.setString(5, phone);
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
    
    public boolean edit(long uid, String name, String title, String phone){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `users` set `name`=?, `title`=?, `phone`=? where `uid`=?;");
            ps.setString(1, name);
            ps.setString(2, title);
            ps.setString(3, phone);
            ps.setLong(4, uid);
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
    
    public boolean remove(long uid){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("delete from `users` where `uid`=?;");
            ps.setLong(1, uid);
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
    
    public boolean secure(String email, String password, String newpass){
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("update `users` set `password`=MD5(concat(?, ?)) where `email`=? and `password`=MD5(concat(?, ?));");
            ps.setString(1, email);
            ps.setString(2, newpass);
            ps.setString(3, email);
            ps.setString(4, password);
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
    
    public List<User.Entity> list(Long uid){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User.Entity> result = new ArrayList<User.Entity>();
        try {
            conn = ConnectionManager.getConnection();
            ps = conn.prepareStatement("select `email`, `name`, `title`, `phone` from `users` where uid=?;");
            ps.setLong(1, uid);
            rs = ps.executeQuery();
            while(rs.next()){
                User.Entity e = new User.Entity();
                e.email = rs.getString(1);
                e.name = rs.getString(2);
                e.title = rs.getString(3);
                e.phone = rs.getString(4);
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

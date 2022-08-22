package User;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
public class LoginAction extends ActionSupport {
    public String uname = "";
    private String password = "";
    public String getUname() {
        return uname;
    }
//    public void setUname(String uname) {
//        this.uname = uname;
//    }
    public String getPassword() {
        return password;
    }
//    public void setPassword(String password) {
//        this.password = password;
//
//    }
    LoginAction(String uname,String password){
        this.uname = uname;
        this.password = password;

    }
    public void jsonformat()
    {
        System.out.println(uname);
        LoginAction l1=new LoginAction(uname,password);
        Gson json=new Gson();
        System.out.println(json.toJson(l1));

    }


}









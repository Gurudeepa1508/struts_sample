package employee;
import com.google.gson.*;
import com.mysql.cj.xdevapi.JsonValue;
import com.opensymphony.xwork2.ActionSupport;
import context.employeeContext;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.HTTP;
import org.json.JSONException;
import org.json.simple.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
public class LoginAction extends ActionSupport implements ServletRequestAware {
    public String name="";
    public String password="";
    public String branch="";
    public String rollnum="";
    public int id=0;
    public String value="";
    org.json.simple.JSONObject jsonchildbject;

    private boolean responseAsJson = true;
    //private JsonObject employeeContextjson;
    private employeeContext employeecontext=new employeeContext();
    public employeeContext getEmployeecontext() {
        return employeecontext;

    }
    public void setEmployeecontext(employeeContext employeecontext) {
        this.employeecontext = employeecontext;
        System.out.println("setting employee context");
    }


    @Override
//    public void setServletRequest(HttpServletRequest request) {
//        responseAsJson=request.getHeader("Content-type").contains("application/json");
//        System.out.println(responseAsJson);
//    }
    public void setServletRequest(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String line;
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null))
                        break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sb.append(line);
            }
            System.out.println(sb.toString());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String s = sb.toString();
        Object obj = JSONValue.parse(s);
        try {
            JSONObject jsonObject = (JSONObject) obj;
            jsonchildbject = (JSONObject) jsonObject.get("employeecontext");
        } catch (Exception e) {
            System.out.println(e);
        }


        name = (String) jsonchildbject.get("uname");
        password = (String) jsonchildbject.get("password");
        branch = (String) jsonchildbject.get("branch");
        rollnum = (String) jsonchildbject.get("rollnum");
        try {
            value = (String) jsonchildbject.get("id");
            id = Integer.parseInt(value);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String validateLogin()
    {
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","Guru@1508");
            System.out.println("connected succesfully");
            String InsertQuery="insert into student(name,rollnum,password,branch) values (?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(InsertQuery);
            ps.setString(1,name);
            ps.setString(2,rollnum);
            ps.setString(3,password);
            ps.setString(4,branch);
            ps.executeUpdate();
            System.out.println("data inserted");
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        employeecontext.setUname(name);
        employeecontext.setBranch(branch);
        employeecontext.setPassword(password);
        employeecontext.setRollnum(rollnum);
        return "display";
    }
    public String GetData(){
        Connection connection=null;
        try{
            System.out.println(id);
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","Guru@1508");
            System.out.println("connected succesfully");
            String SelectQuery="select * from student where id="+id+";";
            System.out.println(SelectQuery);
            Statement st = connection.createStatement();
            ResultSet rs=st.executeQuery(SelectQuery);
            System.out.println(rs);
            while(rs.next()){
                name=rs.getString(2);
                rollnum=rs.getString(3);
                password=rs.getString(4);
                branch=rs.getString(5);

                employeecontext.setUname(name);
                employeecontext.setBranch(branch);
                employeecontext.setPassword(password);
                employeecontext.setRollnum(rollnum);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "fetch";

    }


}






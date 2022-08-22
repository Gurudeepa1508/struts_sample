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
import java.text.ParseException;
public class LoginAction extends ActionSupport implements ServletRequestAware {
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
    public String validateLogin() {
      //  System.out.println("action class" + getEmployeecontext());
        return "display";
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
        Object obj= JSONValue.parse(s);

        org.json.simple.JSONObject jsonObject = (JSONObject) obj;
        org.json.simple.JSONObject jsonchildbject = (JSONObject) jsonObject.get("employeecontext");
       // String name=(String)jsonObject.get("uname");
        System.out.println(jsonObject);
        System.out.println((String)jsonchildbject.get("uname"));

    }
}






package employee;
import com.google.gson.JsonObject;
public class employeeContextJson {
    public String uname;
    private String password;
    private String rollnum;
    private String branch;

    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRollnum() {
        return rollnum;
    }
    public void setRollnum(String rollnum) {
        this.rollnum = rollnum;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;

    }

}

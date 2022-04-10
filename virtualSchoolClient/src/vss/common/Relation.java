package vss.common;
/**
 * 鐢ㄦ潵澶勭悊鍏崇郴鐨勭被
 */
public class Relation {
    private String uID;
    private String obj;

    public Relation() {
    }

    public Relation(String uID, String obj) {
        this.uID = uID;
        this.obj = obj;
    }

    public String getUID() {
        return this.uID;
    }

    public void setUID(String uID) {
        this.uID = uID;
    }

    public String getObj() {
        return this.obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "{" +
            " uID='" + getUID() + "'" +
            ", obj='" + getObj() + "'" +
            "}";
    }

}

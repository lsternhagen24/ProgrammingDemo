import java.util.*;
import java.util.HashSet;

public class Tool {
    public String strType;
    public String strBrand;
    public String strToolCode;
    public RentChargeProperties rentChrgProp;

    //Constructor for given toolcode.
    public Tool(String strToolCode) throws Exception{
        if(!Tool.dctToolCodeToData().containsKey(strToolCode)){
            throw new Exception("Attempting to create invalid tool. Tool Code " + strToolCode + " doesn't exist");
        }
        Tool tmp = Tool.dctToolCodeToData().get(strToolCode);
        this.strType = tmp.strType;
        this.strBrand = tmp.strBrand;
        this.strToolCode=  strToolCode;
        this.rentChrgProp = tmp.rentChrgProp;
    }
    //constructor to create copy based off input tool
    public Tool(Tool tmp) throws Exception{
        this.strType =tmp.strType;
        this.strBrand = tmp.strBrand;
        this.strToolCode = tmp.strToolCode;
        this.rentChrgProp = tmp.rentChrgProp.copy();
    }
    //constructor to create new tool
    private Tool(String strToolCode, String strBrand, String strType) throws Exception{
        this.strToolCode = strToolCode;
        this.strBrand = strBrand;
        this.strType = strType;
        this.rentChrgProp = new RentChargeProperties(strType);
    }

    //returns an array of all available tools.
    public static Tool[] AllTools() throws Exception {
        Tool ladw = new Tool("LADW", "Werner", "Ladder");
        Tool chns = new Tool("CHNS", "Stihl", "Chainsaw");
        Tool jakr = new Tool("JAKR", "Ridgid", "Jackhammer");
        Tool jakd = new Tool ("JAKD", "DeWalt", "Jackhammer");
        return new Tool[] {ladw, chns, jakr, jakd};
    }

    //creates a dictionary that maps each toolcode to the tool data
    public static Map<String, Tool> dctToolCodeToData() throws Exception{
        Map<String, Tool> dctToolCodeToData = new HashMap<String, Tool>();
        for(Tool i: AllTools()){
            dctToolCodeToData.put(i.strToolCode, i);
        }
        return dctToolCodeToData;
    }

    //returns a hashset of all available tool codes. Can be used for quick lookups to check if a tool code exists.
    public static HashSet<String> hshToolCodes() throws Exception{
        HashSet<String> hshAllCodes = new HashSet<String>();
        for(Tool i : AllTools()){
            hshAllCodes.add(i.strToolCode);
        }
        return hshAllCodes;
    }

    //deep copy of current Tool
    public Tool copy() throws Exception{
        return new Tool(this);
    }

    @Override
    public String toString(){
        return "ToolCode: " + this.strToolCode + "\n" + "Brand: " + this.strBrand + "\n" + "Tool Type: " + this.strType;
    }

    //hashcode for mapping tools. Use toolcode string hashcode.
    @Override
    public int hashCode() {
        return this.strToolCode.hashCode();
    }

    @Override
    public boolean equals(Object otherTool){
        //If we are comparing the wrong object type return false
        if(otherTool.getClass() != Tool.class) return false;
        //Cast and compare toocodes
        Tool other = (Tool)otherTool;
        return this.strToolCode == other.strToolCode;
    }

}

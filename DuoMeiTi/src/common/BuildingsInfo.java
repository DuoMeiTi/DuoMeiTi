package common;

public class BuildingsInfo{
	public String buildingName;
	public int buildingId;
	BuildingsInfo(){}
	public BuildingsInfo(String name,Integer id){
		this.buildingName=name;
		this.buildingId=id.intValue();
	}
}

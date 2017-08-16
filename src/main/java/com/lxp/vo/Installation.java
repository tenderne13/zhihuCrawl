package com.lxp.vo;

public class Installation {
	private Integer id;
	private String type;
	private String deviceName;
	private String objectId;
	private String communityId;
	private String  number;
	private String groupBy;
	private String createTime;
	private String yard;
	private String buildingNum;
	private String place;
	private String location;
	private String company;//单位名称
	private String office;//责任科室
	private String leader;//负责人
	private String managePerson;//管理人员
	private String phoneNum;
	private String measure;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getYard() {
		return yard;
	}
	public void setYard(String yard) {
		this.yard = yard;
	}
	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getManagePerson() {
		return managePerson;
	}
	public void setManagePerson(String managePerson) {
		this.managePerson = managePerson;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Installation() {
		super();
	}

    @Override
    public String toString() {
        return "Installation{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", objectId='" + objectId + '\'' +
                ", communityId='" + communityId + '\'' +
                ", number='" + number + '\'' +
                ", groupBy='" + groupBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", yard='" + yard + '\'' +
                ", buildingNum='" + buildingNum + '\'' +
                ", place='" + place + '\'' +
                ", location='" + location + '\'' +
                ", company='" + company + '\'' +
                ", office='" + office + '\'' +
                ", leader='" + leader + '\'' +
                ", managePerson='" + managePerson + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", measure='" + measure + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

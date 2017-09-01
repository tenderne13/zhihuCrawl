package com.lxp.vo;

public class Person {
	
	//ID
	private String id;
	//住户ID
    private String householdid;
    //编号
    private String addresscode;
    //成员关系
    private String reliationship;
    //姓名
    private String name;
    //性别
    private String sex;
    //民族
    private String nation;
    //证件类型
    private String zjlx;
    //证件号码
    private String idcard;
    //政治面貌
    private String politicalstatus;
    //文化程度
    private String education;
    //工作单位&学校
    private String workorschool;
    //联系电话
    private String telephone;
    //户籍类别
    private String householdcategory;
    //户籍状态
    private String householdstatus;
    //户籍地址
    private String householdaddress;
    //居住地址1
    private String liveaddress1;
    //居住地址2
    private String liveaddress2;
    //居住地址3
    private String liveaddress3;
    //居住地址4
    private String liveaddress4;
    //居住地址5
    private String liveaddress5;
    //年龄
    private String age;
    //出生日期
    private String borndate;
    //数据来源
    private String datafrom;
    //创建日期
    private String createtime;
    //更新日期
    private String updatetime;
    //死亡日期
    private String deadtime;
    //删除标记
    private Integer isdelete;
    //备注
    private String remark;
    
    //***********扩展表字段***********//
    //楼房图层id
    private String objectId;
    //户名
    private String householdname;
    //街道ID
    private String streetId;
    //街道名
    private String streetName;
    //社区ID
    private String communityId;
    //社区名
    private String communityName;
    //院号ID
    private String yardId;
    //院名
    private String yardName;
    //楼房名称
    private String buildingName;
    //楼房id
    private String buildingId;
    //单元id
    private String unitId;
    //房间id
    private String houseId;
    //人员转移后户ID
    private String newHouseholdId;
    //人员转移后ID
    private String newPersonId;
    
    //人员状况(多个ID逗号分隔)
    private String personStates;
    //房屋状况
    private String houseStates;
    

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHouseholdid() {
		return householdid;
	}
	public void setHouseholdid(String householdid) {
		this.householdid = householdid;
	}
	public String getAddresscode() {
		return addresscode;
	}
	public void setAddresscode(String addresscode) {
		this.addresscode = addresscode;
	}
	public String getReliationship() {
		return reliationship;
	}
	public void setReliationship(String reliationship) {
		this.reliationship = reliationship;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPoliticalstatus() {
		return politicalstatus;
	}
	public void setPoliticalstatus(String politicalstatus) {
		this.politicalstatus = politicalstatus;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getWorkorschool() {
		return workorschool;
	}
	public void setWorkorschool(String workorschool) {
		this.workorschool = workorschool;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getHouseholdcategory() {
		return householdcategory;
	}
	public void setHouseholdcategory(String householdcategory) {
		this.householdcategory = householdcategory;
	}
	public String getHouseholdstatus() {
		return householdstatus;
	}
	public void setHouseholdstatus(String householdstatus) {
		this.householdstatus = householdstatus;
	}
	public String getHouseholdaddress() {
		return householdaddress;
	}
	public void setHouseholdaddress(String householdaddress) {
		this.householdaddress = householdaddress;
	}
	public String getLiveaddress1() {
		return liveaddress1;
	}
	public void setLiveaddress1(String liveaddress1) {
		this.liveaddress1 = liveaddress1;
	}
	public String getLiveaddress2() {
		return liveaddress2;
	}
	public void setLiveaddress2(String liveaddress2) {
		this.liveaddress2 = liveaddress2;
	}
	public String getLiveaddress3() {
		return liveaddress3;
	}
	public void setLiveaddress3(String liveaddress3) {
		this.liveaddress3 = liveaddress3;
	}
	public String getLiveaddress4() {
		return liveaddress4;
	}
	public void setLiveaddress4(String liveaddress4) {
		this.liveaddress4 = liveaddress4;
	}
	public String getLiveaddress5() {
		return liveaddress5;
	}
	public void setLiveaddress5(String liveaddress5) {
		this.liveaddress5 = liveaddress5;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBorndate() {
		return borndate;
	}
	public void setBorndate(String borndate) {
		this.borndate = borndate;
	}
	public String getDatafrom() {
		return datafrom;
	}
	public void setDatafrom(String datafrom) {
		this.datafrom = datafrom;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getDeadtime() {
		return deadtime;
	}
	public void setDeadtime(String deadtime) {
		this.deadtime = deadtime;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getHouseholdname() {
		return householdname;
	}
	public void setHouseholdname(String householdname) {
		this.householdname = householdname;
	}
	public String getStreetId() {
		return streetId;
	}
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getYardId() {
		return yardId;
	}
	public void setYardId(String yardId) {
		this.yardId = yardId;
	}
	public String getYardName() {
		return yardName;
	}
	public void setYardName(String yardName) {
		this.yardName = yardName;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getNewHouseholdId() {
		return newHouseholdId;
	}
	public void setNewHouseholdId(String newHouseholdId) {
		this.newHouseholdId = newHouseholdId;
	}
	public String getNewPersonId() {
		return newPersonId;
	}
	public void setNewPersonId(String newPersonId) {
		this.newPersonId = newPersonId;
	}
	public String getPersonStates() {
		return personStates;
	}
	public void setPersonStates(String personStates) {
		this.personStates = personStates;
	}
	public String getHouseStates() {
		return houseStates;
	}
	public void setHouseStates(String houseStates) {
		this.houseStates = houseStates;
	}

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", householdid='" + householdid + '\'' +
                ", addresscode='" + addresscode + '\'' +
                ", reliationship='" + reliationship + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", zjlx='" + zjlx + '\'' +
                ", idcard='" + idcard + '\'' +
                ", politicalstatus='" + politicalstatus + '\'' +
                ", education='" + education + '\'' +
                ", workorschool='" + workorschool + '\'' +
                ", telephone='" + telephone + '\'' +
                ", householdcategory='" + householdcategory + '\'' +
                ", householdstatus='" + householdstatus + '\'' +
                ", householdaddress='" + householdaddress + '\'' +
                ", liveaddress1='" + liveaddress1 + '\'' +
                ", liveaddress2='" + liveaddress2 + '\'' +
                ", liveaddress3='" + liveaddress3 + '\'' +
                ", liveaddress4='" + liveaddress4 + '\'' +
                ", liveaddress5='" + liveaddress5 + '\'' +
                ", age='" + age + '\'' +
                ", borndate='" + borndate + '\'' +
                ", datafrom='" + datafrom + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", deadtime='" + deadtime + '\'' +
                ", isdelete=" + isdelete +
                ", remark='" + remark + '\'' +
                ", objectId='" + objectId + '\'' +
                ", householdname='" + householdname + '\'' +
                ", streetId='" + streetId + '\'' +
                ", streetName='" + streetName + '\'' +
                ", communityId='" + communityId + '\'' +
                ", communityName='" + communityName + '\'' +
                ", yardId='" + yardId + '\'' +
                ", yardName='" + yardName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", buildingId='" + buildingId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", houseId='" + houseId + '\'' +
                ", newHouseholdId='" + newHouseholdId + '\'' +
                ", newPersonId='" + newPersonId + '\'' +
                ", personStates='" + personStates + '\'' +
                ", houseStates='" + houseStates + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (householdid != null ? !householdid.equals(person.householdid) : person.householdid != null) return false;
        if (addresscode != null ? !addresscode.equals(person.addresscode) : person.addresscode != null) return false;
        if (reliationship != null ? !reliationship.equals(person.reliationship) : person.reliationship != null)
            return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (sex != null ? !sex.equals(person.sex) : person.sex != null) return false;
        if (nation != null ? !nation.equals(person.nation) : person.nation != null) return false;
        if (zjlx != null ? !zjlx.equals(person.zjlx) : person.zjlx != null) return false;
        if (idcard != null ? !idcard.equals(person.idcard) : person.idcard != null) return false;
        if (politicalstatus != null ? !politicalstatus.equals(person.politicalstatus) : person.politicalstatus != null)
            return false;
        if (education != null ? !education.equals(person.education) : person.education != null) return false;
        if (workorschool != null ? !workorschool.equals(person.workorschool) : person.workorschool != null)
            return false;
        if (telephone != null ? !telephone.equals(person.telephone) : person.telephone != null) return false;
        if (householdcategory != null ? !householdcategory.equals(person.householdcategory) : person.householdcategory != null)
            return false;
        if (householdstatus != null ? !householdstatus.equals(person.householdstatus) : person.householdstatus != null)
            return false;
        if (householdaddress != null ? !householdaddress.equals(person.householdaddress) : person.householdaddress != null)
            return false;
        if (liveaddress1 != null ? !liveaddress1.equals(person.liveaddress1) : person.liveaddress1 != null)
            return false;
        if (liveaddress2 != null ? !liveaddress2.equals(person.liveaddress2) : person.liveaddress2 != null)
            return false;
        if (liveaddress3 != null ? !liveaddress3.equals(person.liveaddress3) : person.liveaddress3 != null)
            return false;
        if (liveaddress4 != null ? !liveaddress4.equals(person.liveaddress4) : person.liveaddress4 != null)
            return false;
        if (liveaddress5 != null ? !liveaddress5.equals(person.liveaddress5) : person.liveaddress5 != null)
            return false;
        if (age != null ? !age.equals(person.age) : person.age != null) return false;
        if (borndate != null ? !borndate.equals(person.borndate) : person.borndate != null) return false;
        if (datafrom != null ? !datafrom.equals(person.datafrom) : person.datafrom != null) return false;
        if (createtime != null ? !createtime.equals(person.createtime) : person.createtime != null) return false;
        if (updatetime != null ? !updatetime.equals(person.updatetime) : person.updatetime != null) return false;
        if (deadtime != null ? !deadtime.equals(person.deadtime) : person.deadtime != null) return false;
        if (isdelete != null ? !isdelete.equals(person.isdelete) : person.isdelete != null) return false;
        if (remark != null ? !remark.equals(person.remark) : person.remark != null) return false;
        if (objectId != null ? !objectId.equals(person.objectId) : person.objectId != null) return false;
        if (householdname != null ? !householdname.equals(person.householdname) : person.householdname != null)
            return false;
        if (streetId != null ? !streetId.equals(person.streetId) : person.streetId != null) return false;
        if (streetName != null ? !streetName.equals(person.streetName) : person.streetName != null) return false;
        if (communityId != null ? !communityId.equals(person.communityId) : person.communityId != null) return false;
        if (communityName != null ? !communityName.equals(person.communityName) : person.communityName != null)
            return false;
        if (yardId != null ? !yardId.equals(person.yardId) : person.yardId != null) return false;
        if (yardName != null ? !yardName.equals(person.yardName) : person.yardName != null) return false;
        if (buildingName != null ? !buildingName.equals(person.buildingName) : person.buildingName != null)
            return false;
        if (buildingId != null ? !buildingId.equals(person.buildingId) : person.buildingId != null) return false;
        if (unitId != null ? !unitId.equals(person.unitId) : person.unitId != null) return false;
        if (houseId != null ? !houseId.equals(person.houseId) : person.houseId != null) return false;
        if (newHouseholdId != null ? !newHouseholdId.equals(person.newHouseholdId) : person.newHouseholdId != null)
            return false;
        if (newPersonId != null ? !newPersonId.equals(person.newPersonId) : person.newPersonId != null) return false;
        if (personStates != null ? !personStates.equals(person.personStates) : person.personStates != null)
            return false;
        return houseStates != null ? houseStates.equals(person.houseStates) : person.houseStates == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (householdid != null ? householdid.hashCode() : 0);
        result = 31 * result + (addresscode != null ? addresscode.hashCode() : 0);
        result = 31 * result + (reliationship != null ? reliationship.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (nation != null ? nation.hashCode() : 0);
        result = 31 * result + (zjlx != null ? zjlx.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (politicalstatus != null ? politicalstatus.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (workorschool != null ? workorschool.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (householdcategory != null ? householdcategory.hashCode() : 0);
        result = 31 * result + (householdstatus != null ? householdstatus.hashCode() : 0);
        result = 31 * result + (householdaddress != null ? householdaddress.hashCode() : 0);
        result = 31 * result + (liveaddress1 != null ? liveaddress1.hashCode() : 0);
        result = 31 * result + (liveaddress2 != null ? liveaddress2.hashCode() : 0);
        result = 31 * result + (liveaddress3 != null ? liveaddress3.hashCode() : 0);
        result = 31 * result + (liveaddress4 != null ? liveaddress4.hashCode() : 0);
        result = 31 * result + (liveaddress5 != null ? liveaddress5.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (borndate != null ? borndate.hashCode() : 0);
        result = 31 * result + (datafrom != null ? datafrom.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (deadtime != null ? deadtime.hashCode() : 0);
        result = 31 * result + (isdelete != null ? isdelete.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (householdname != null ? householdname.hashCode() : 0);
        result = 31 * result + (streetId != null ? streetId.hashCode() : 0);
        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
        result = 31 * result + (communityId != null ? communityId.hashCode() : 0);
        result = 31 * result + (communityName != null ? communityName.hashCode() : 0);
        result = 31 * result + (yardId != null ? yardId.hashCode() : 0);
        result = 31 * result + (yardName != null ? yardName.hashCode() : 0);
        result = 31 * result + (buildingName != null ? buildingName.hashCode() : 0);
        result = 31 * result + (buildingId != null ? buildingId.hashCode() : 0);
        result = 31 * result + (unitId != null ? unitId.hashCode() : 0);
        result = 31 * result + (houseId != null ? houseId.hashCode() : 0);
        result = 31 * result + (newHouseholdId != null ? newHouseholdId.hashCode() : 0);
        result = 31 * result + (newPersonId != null ? newPersonId.hashCode() : 0);
        result = 31 * result + (personStates != null ? personStates.hashCode() : 0);
        result = 31 * result + (houseStates != null ? houseStates.hashCode() : 0);
        return result;
    }
}

package com.cafe24.blognyj9112.bloodpressure.service;

public class BloodPressure {
	private String bloodPressureNo;
	private String memberNo;
	private int systolicPressure;
	private int diastolicPressure;
	private String bloodPressureDate;
	public String getBloodPressureNo() {
		return bloodPressureNo;
	}
	public void setBloodPressureNo(String bloodPressureNo) {
		this.bloodPressureNo = bloodPressureNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public int getSystolicPressure() {
		return systolicPressure;
	}
	public void setSystolicPressure(int systolicPressure) {
		this.systolicPressure = systolicPressure;
	}
	public int getDiastolicPressure() {
		return diastolicPressure;
	}
	public void setDiastolicPressure(int diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}
	public String getBloodPressureDate() {
		return bloodPressureDate;
	}
	public void setBloodPressureDate(String bloodPressureDate) {
		this.bloodPressureDate = bloodPressureDate;
	}
	@Override
	public String toString() {
		return "BloodPressure [bloodPressureNo=" + bloodPressureNo + ", memberNo=" + memberNo + ", systolicPressure="
				+ systolicPressure + ", diastolicPressure=" + diastolicPressure + ", bloodPressureDate="
				+ bloodPressureDate + "]";
	}
}
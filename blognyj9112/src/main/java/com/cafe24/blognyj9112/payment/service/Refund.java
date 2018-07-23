package com.cafe24.blognyj9112.payment.service;

public class Refund {
	private String refundNo;
	private String memberNo;
	private String memberName;
	private String memberId;
	private String refundTitle;
	private String refundContent;
	private int refundSum;
	private String refundDate;
	private String refundDirectorNo;
	private String refundApproval;
	private String refundApprovalDate;
	private String refundCompleteDate;
	private String refundCompleteDirectorNo;
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getRefundTitle() {
		return refundTitle;
	}
	public void setRefundTitle(String refundTitle) {
		this.refundTitle = refundTitle;
	}
	public String getRefundContent() {
		return refundContent;
	}
	public void setRefundContent(String refundContent) {
		this.refundContent = refundContent;
	}
	public int getRefundSum() {
		return refundSum;
	}
	public void setRefundSum(int refundSum) {
		this.refundSum = refundSum;
	}
	public String getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}
	public String getRefundDirectorNo() {
		return refundDirectorNo;
	}
	public void setRefundDirectorNo(String refundDirectorNo) {
		this.refundDirectorNo = refundDirectorNo;
	}
	public String getRefundApproval() {
		return refundApproval;
	}
	public void setRefundApproval(String refundApproval) {
		this.refundApproval = refundApproval;
	}
	public String getRefundApprovalDate() {
		return refundApprovalDate;
	}
	public void setRefundApprovalDate(String refundApprovalDate) {
		this.refundApprovalDate = refundApprovalDate;
	}
	public String getRefundCompleteDate() {
		return refundCompleteDate;
	}
	public void setRefundCompleteDate(String refundCompleteDate) {
		this.refundCompleteDate = refundCompleteDate;
	}
	public String getRefundCompleteDirectorNo() {
		return refundCompleteDirectorNo;
	}
	public void setRefundCompleteDirectorNo(String refundCompleteDirectorNo) {
		this.refundCompleteDirectorNo = refundCompleteDirectorNo;
	}
	@Override
	public String toString() {
		return "Refund [refundNo=" + refundNo + ", memberNo=" + memberNo + ", memberName=" + memberName + ", memberId="
				+ memberId + ", refundTitle=" + refundTitle + ", refundContent=" + refundContent + ", refundSum="
				+ refundSum + ", refundDate=" + refundDate + ", refundDirectorNo=" + refundDirectorNo
				+ ", refundApproval=" + refundApproval + ", refundApprovalDate=" + refundApprovalDate
				+ ", refundCompleteDate=" + refundCompleteDate + ", refundCompleteDirectorNo="
				+ refundCompleteDirectorNo + "]";
	}

}

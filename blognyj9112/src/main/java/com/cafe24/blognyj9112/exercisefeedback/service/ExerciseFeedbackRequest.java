package com.cafe24.blognyj9112.exercisefeedback.service;

public class ExerciseFeedbackRequest {
	private String exerciseFeedbackRequestNo;
	private String memberNo;
	private String memberName;
	private String memberId;
	private String memberWorkSpace;
	private int memberTotalFeedback;
	private int memberAgreeFeedback;
	private String feedbackAvailability;
	private String teacherNo;
	private String exerciseFeedbackRequestTitle;
	private String exerciseFeedbackRequestContent;
	private String exerciseFeedbackRequestDate;
	private String exerciseFeedbackApproval;
	private String exerciseFeedbackResult;
	private String exerciseFeedbackApprovalDate;
	private double evaluationAverageGrade;
	private String exerciseFeedbackTitle;
	private String exerciseFeedbackContent;
	private String exerciseFeedbackResultDate;
	public String getExerciseFeedbackRequestNo() {
		return exerciseFeedbackRequestNo;
	}
	public void setExerciseFeedbackRequestNo(String exerciseFeedbackRequestNo) {
		this.exerciseFeedbackRequestNo = exerciseFeedbackRequestNo;
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
	public String getMemberWorkSpace() {
		return memberWorkSpace;
	}
	public void setMemberWorkSpace(String memberWorkSpace) {
		this.memberWorkSpace = memberWorkSpace;
	}
	public int getMemberTotalFeedback() {
		return memberTotalFeedback;
	}
	public void setMemberTotalFeedback(int memberTotalFeedback) {
		this.memberTotalFeedback = memberTotalFeedback;
	}
	public int getMemberAgreeFeedback() {
		return memberAgreeFeedback;
	}
	public void setMemberAgreeFeedback(int memberAgreeFeedback) {
		this.memberAgreeFeedback = memberAgreeFeedback;
	}
	public String getFeedbackAvailability() {
		return feedbackAvailability;
	}
	public void setFeedbackAvailability(String feedbackAvailability) {
		this.feedbackAvailability = feedbackAvailability;
	}
	public String getTeacherNo() {
		return teacherNo;
	}
	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	public String getExerciseFeedbackRequestTitle() {
		return exerciseFeedbackRequestTitle;
	}
	public void setExerciseFeedbackRequestTitle(String exerciseFeedbackRequestTitle) {
		this.exerciseFeedbackRequestTitle = exerciseFeedbackRequestTitle;
	}
	public String getExerciseFeedbackRequestContent() {
		return exerciseFeedbackRequestContent;
	}
	public void setExerciseFeedbackRequestContent(String exerciseFeedbackRequestContent) {
		this.exerciseFeedbackRequestContent = exerciseFeedbackRequestContent;
	}
	public String getExerciseFeedbackRequestDate() {
		return exerciseFeedbackRequestDate;
	}
	public void setExerciseFeedbackRequestDate(String exerciseFeedbackRequestDate) {
		this.exerciseFeedbackRequestDate = exerciseFeedbackRequestDate;
	}
	public String getExerciseFeedbackApproval() {
		return exerciseFeedbackApproval;
	}
	public void setExerciseFeedbackApproval(String exerciseFeedbackApproval) {
		this.exerciseFeedbackApproval = exerciseFeedbackApproval;
	}
	public String getExerciseFeedbackResult() {
		return exerciseFeedbackResult;
	}
	public void setExerciseFeedbackResult(String exerciseFeedbackResult) {
		this.exerciseFeedbackResult = exerciseFeedbackResult;
	}
	public String getExerciseFeedbackApprovalDate() {
		return exerciseFeedbackApprovalDate;
	}
	public void setExerciseFeedbackApprovalDate(String exerciseFeedbackApprovalDate) {
		this.exerciseFeedbackApprovalDate = exerciseFeedbackApprovalDate;
	}
	public double getEvaluationAverageGrade() {
		return evaluationAverageGrade;
	}
	public void setEvaluationAverageGrade(double evaluationAverageGrade) {
		this.evaluationAverageGrade = evaluationAverageGrade;
	}
	public String getExerciseFeedbackTitle() {
		return exerciseFeedbackTitle;
	}
	public void setExerciseFeedbackTitle(String exerciseFeedbackTitle) {
		this.exerciseFeedbackTitle = exerciseFeedbackTitle;
	}
	public String getExerciseFeedbackContent() {
		return exerciseFeedbackContent;
	}
	public void setExerciseFeedbackContent(String exerciseFeedbackContent) {
		this.exerciseFeedbackContent = exerciseFeedbackContent;
	}
	public String getExerciseFeedbackResultDate() {
		return exerciseFeedbackResultDate;
	}
	public void setExerciseFeedbackResultDate(String exerciseFeedbackResultDate) {
		this.exerciseFeedbackResultDate = exerciseFeedbackResultDate;
	}
	@Override
	public String toString() {
		return "ExerciseFeedbackRequest [exerciseFeedbackRequestNo=" + exerciseFeedbackRequestNo + ", memberNo="
				+ memberNo + ", memberName=" + memberName + ", memberId=" + memberId + ", memberWorkSpace="
				+ memberWorkSpace + ", memberTotalFeedback=" + memberTotalFeedback + ", memberAgreeFeedback="
				+ memberAgreeFeedback + ", feedbackAvailability=" + feedbackAvailability + ", teacherNo=" + teacherNo
				+ ", exerciseFeedbackRequestTitle=" + exerciseFeedbackRequestTitle + ", exerciseFeedbackRequestContent="
				+ exerciseFeedbackRequestContent + ", exerciseFeedbackRequestDate=" + exerciseFeedbackRequestDate
				+ ", exerciseFeedbackApproval=" + exerciseFeedbackApproval + ", exerciseFeedbackResult="
				+ exerciseFeedbackResult + ", exerciseFeedbackApprovalDate=" + exerciseFeedbackApprovalDate
				+ ", evaluationAverageGrade=" + evaluationAverageGrade + ", exerciseFeedbackTitle="
				+ exerciseFeedbackTitle + ", exerciseFeedbackContent=" + exerciseFeedbackContent
				+ ", exerciseFeedbackResultDate=" + exerciseFeedbackResultDate + "]";
	}
}

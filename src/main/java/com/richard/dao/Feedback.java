package com.richard.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Feedback {
	private int fbId;
	private String facultyName;
	private int facultyId;
	private String date;
	private String question;
	private String studentName;
	private int studentId;
	private String answer;
	private int answerId;
	
	public int getFbId() {
		return fbId;
	}
	public void setFbId(int fbId) {
		this.fbId = fbId;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		LocalDate localdate = LocalDate.parse(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYY-MM-dd");
		date = formatter.format(localdate);
		this.date = date;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	
	
}

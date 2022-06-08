package com.BugTracker.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String status;
	private String Start_date;
	private String end_date;

	@OneToOne
	private Project pid;

	@OneToOne
	private Team tid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStart_date() {
		return Start_date;
	}

	public void setStart_date(String start_date) {
		Start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Project getPid() {
		return pid;
	}

	public void setPid(Project pid) {
		this.pid = pid;
	}

	public Team getTid() {
		return tid;
	}

	public void setTid(Team tid) {
		this.tid = tid;
	}

	public Report(Long id, String status, String start_date, String end_date, Project pid, Team tid) {
		super();
		this.id = id;
		this.status = status;
		Start_date = start_date;
		this.end_date = end_date;
		this.pid = pid;
		this.tid = tid;
	}

	public Report() {
		super();
	}

}

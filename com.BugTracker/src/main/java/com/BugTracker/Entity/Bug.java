package com.BugTracker.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bug_desc;
	private String status;

	@OneToOne
	private User devloper_id;
	@OneToOne
	private User tester_id;
	@OneToOne
	private Project pid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBug_desc() {
		return bug_desc;
	}

	public void setBug_desc(String bug_desc) {
		this.bug_desc = bug_desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getDevloper_id() {
		return devloper_id;
	}

	public void setDevloper_id(User devloper_id) {
		this.devloper_id = devloper_id;
	}

	public User getTester_id() {
		return tester_id;
	}

	public void setTester_id(User tester_id) {
		this.tester_id = tester_id;
	}

	public Project getPid() {
		return pid;
	}

	public void setPid(Project pid) {
		this.pid = pid;
	}

	public Bug(Long id, String bug_desc, String status, User devloper_id, User tester_id, Project pid) {
		super();
		this.id = id;
		this.bug_desc = bug_desc;
		this.status = status;
		this.devloper_id = devloper_id;
		this.tester_id = tester_id;
		this.pid = pid;
	}

	public Bug() {
		super();
	}

}

package com.fg.ss.abhiyog.common.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "connected_litigation")
public class ConnectedLitigation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "connected_litigation_tbl_oid")
	private int connectedLitigationTblOid;

	@Column(name = "comment")
	private String comment;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "litigation_oid")
	private Litigation litigation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "connected_litigation_oid")
	private Litigation connectedLitigation;

	public int getConnectedLitigationTblOid() {
		return connectedLitigationTblOid;
	}

	public void setConnectedLitigationTblOid(int connectedLitigationTblOid) {
		this.connectedLitigationTblOid = connectedLitigationTblOid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Litigation getLitigation() {
		return litigation;
	}

	public void setLitigation(Litigation litigation) {
		this.litigation = litigation;
	}

	public Litigation getConnectedLitigation() {
		return connectedLitigation;
	}

	public void setConnectedLitigation(Litigation connectedLitigation) {
		this.connectedLitigation = connectedLitigation;
	}

}

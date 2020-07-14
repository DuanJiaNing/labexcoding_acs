package com.acs.admin.ds.entity;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class SysRole implements Serializable {

	private Integer id;
	private Timestamp insertTime;
	private Timestamp updateTime;
	private String roleName;
}

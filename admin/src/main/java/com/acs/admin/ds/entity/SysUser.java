package com.acs.admin.ds.entity;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class SysUser implements Serializable {

	private Integer id;
	private Timestamp insertTime;
	private Timestamp updateTime;
	private String username;
	private String password;
	private String nickname;
}

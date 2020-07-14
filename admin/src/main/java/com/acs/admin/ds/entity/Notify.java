package com.acs.admin.ds.entity;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class Notify implements Serializable {

	private Integer id;
	private Timestamp insertTime;
	private Timestamp updateTime;
	private Integer userId;
	private String content;
}

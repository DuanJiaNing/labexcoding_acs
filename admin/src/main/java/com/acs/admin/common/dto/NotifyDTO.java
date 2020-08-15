package com.acs.admin.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyDTO implements Serializable {

    private Integer id;
    private Integer userId;
    private String username;
    private String content;
}

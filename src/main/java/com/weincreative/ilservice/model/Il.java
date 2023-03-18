package com.weincreative.ilservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Il {
    @Id
    private String id;
    private String name;
    private Date createDate = new Date();
}



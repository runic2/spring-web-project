package org.zerock.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
//	input - 2022-01-01 => 2022/01/01로 변환

}

package com.test;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private Integer tag = 0;

}

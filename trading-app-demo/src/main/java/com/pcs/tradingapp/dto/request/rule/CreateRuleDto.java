package com.pcs.tradingapp.dto.request.rule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateRuleDto {
	@NotBlank(message = "The rule name is required.")
    @Size(max = 125, message = "The rule name must not exceed 125 characters.")
    private String name;

    @Size(max = 255, message = "The description must not exceed 255 characters.")
    private String description;

    @Size(max = 10000, message = "The JSON content is too long.")
    private String json;

    @Size(max = 10000, message = "The template content is too long.")
    private String template;

    @Size(max = 10000, message = "The SQL string is too long.")
    private String sql;

    @Size(max = 10000, message = "The SQL part is too long.")
    private String sqlPart;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSql() {
		return sql;
	}

	public void setSqlStr(String sql) {
		this.sql = sql;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}
}

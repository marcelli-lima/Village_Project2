package com.example.projeto.model.transport;

import java.util.Objects;


public class FilterVillageDTO {

	private Integer id;
	private String Name;
	
	
	public FilterVillageDTO(Integer id, String name) {
		super();
		this.id = id;
		Name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilterVillageDTO other = (FilterVillageDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}

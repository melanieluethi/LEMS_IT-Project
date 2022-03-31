package ch.fhnw.lems.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//LUM
@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long roleId;
	private UserRole role;
	
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}
}
package com.ibm.AdditionDao;

//
//import java.io.Serializable;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//public class Employee implements Serializable
// {
////	/**
////	 * 
////	 */
////	private static final long serialVersionUID = 2514224258713165192L;
//	@Id
//	@GeneratedValue(strategy =GenerationType.IDENTITY)
//	private String name;
//	private Long id;
//	private String designation;
//	private String location;
//	private String phoneNo;
//	private String uRL;
//	
//	 public Employee() {}
//	
//public Employee(String name, long id, String designation, String location, String phoneNo, String uRL) {
//		super();
//		this.name = name;
//		this.id = id;
//		this.designation = designation;
//		this.location = location;
//		this.phoneNo = phoneNo;
//		this.uRL = uRL;
//	}
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getDesignation() {
//		return designation;
//	}
//
//	public void setDesignation(String designation) {
//		this.designation = designation;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//	public String getPhoneNo() {
//		return phoneNo;
//	}
//
//	public void setPhoneNo(String phoneNo) {
//		this.phoneNo = phoneNo;
//	}
//
//	public String getuRL() {
//		return uRL;
//	}
//
//	public void setuRL(String uRL) {
//		this.uRL = uRL;
//	}
//
//	public void setEmployee(String string) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	 @Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "Employee{" +
//				"id=" +id +
//				", name='" + name +'\'' +
//				", designation='" + designation +'\'' +
//				", location='" + location +'\'' +
//				", phoneNo='" + phoneNo +'\'' +
//				", url='" + uRL +'\'' +
//				'}';
//	}
//	
//
//}
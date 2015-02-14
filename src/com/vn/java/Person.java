package com.vn.java;

public class Person {
	private String name;
	private String age;

	public class Address {
		public String number;

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public Address(String number) {
			super();
			this.number = number;
		}

		public Address() {
			super();
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Person(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Person() {
		super();
	}

}

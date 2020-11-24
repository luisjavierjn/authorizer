package com.banistmo.auth.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "channel")
public class Channel {

	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "name", nullable = false, precision = 30)
	private String name;

	@Column(name = "description", nullable = false, precision = 50)
	private String description;

	@Column(name = "type_channel", nullable = false, precision = 100)
	private String typeChannel;

	public Channel() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getTypeChannel() {
		return typeChannel;
	}

	public void setTypeChannel(String typeChannel) {
		this.typeChannel = typeChannel;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", description=" + description + ", typeChannel="
				+ typeChannel + "]";
	}
}

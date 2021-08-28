package it.formazione.liferay.rest.graph.todo.client.dto.v1_0;

import it.formazione.liferay.rest.graph.todo.client.function.UnsafeSupplier;
import it.formazione.liferay.rest.graph.todo.client.serdes.v1_0.TodoSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Virgilio Alessandro
 * @generated
 */
@Generated("")
public class Todo implements Cloneable, Serializable {

	public static Todo toDTO(String json) {
		return TodoSerDes.toDTO(json);
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public void setCompleted(
		UnsafeSupplier<Boolean, Exception> completedUnsafeSupplier) {

		try {
			completed = completedUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean completed;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Integer, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer id;

	@Override
	public Todo clone() throws CloneNotSupportedException {
		return (Todo)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Todo)) {
			return false;
		}

		Todo todo = (Todo)object;

		return Objects.equals(toString(), todo.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return TodoSerDes.toJSON(this);
	}

}
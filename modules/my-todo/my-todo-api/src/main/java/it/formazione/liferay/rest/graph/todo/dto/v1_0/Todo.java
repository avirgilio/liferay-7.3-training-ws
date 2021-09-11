package it.formazione.liferay.rest.graph.todo.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Virgilio Alessandro
 * @generated
 */
@Generated("")
@GraphQLName(description = "My Todo Entity", value = "Todo")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Todo")
public class Todo implements Serializable {

	public static Todo toDTO(String json) {
		return ObjectMapperUtil.readValue(Todo.class, json);
	}

	@Schema(description = "If the todo is completed")
	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@JsonIgnore
	public void setCompleted(
		UnsafeSupplier<Boolean, Exception> completedUnsafeSupplier) {

		try {
			completed = completedUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "If the todo is completed")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean completed;

	@Schema(description = "The todo description.")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The todo description.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String description;

	@Schema(description = "The todo ID.")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Integer, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The todo ID.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer id;

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
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (completed != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"completed\": ");

			sb.append(completed);
		}

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(description));

			sb.append("\"");
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}
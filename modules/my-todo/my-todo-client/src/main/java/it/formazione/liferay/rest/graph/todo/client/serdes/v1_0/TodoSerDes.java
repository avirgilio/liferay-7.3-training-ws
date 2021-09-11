package it.formazione.liferay.rest.graph.todo.client.serdes.v1_0;

import it.formazione.liferay.rest.graph.todo.client.dto.v1_0.Todo;
import it.formazione.liferay.rest.graph.todo.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Virgilio Alessandro
 * @generated
 */
@Generated("")
public class TodoSerDes {

	public static Todo toDTO(String json) {
		TodoJSONParser todoJSONParser = new TodoJSONParser();

		return todoJSONParser.parseToDTO(json);
	}

	public static Todo[] toDTOs(String json) {
		TodoJSONParser todoJSONParser = new TodoJSONParser();

		return todoJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Todo todo) {
		if (todo == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (todo.getCompleted() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"completed\": ");

			sb.append(todo.getCompleted());
		}

		if (todo.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(todo.getDescription()));

			sb.append("\"");
		}

		if (todo.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(todo.getId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TodoJSONParser todoJSONParser = new TodoJSONParser();

		return todoJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Todo todo) {
		if (todo == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (todo.getCompleted() == null) {
			map.put("completed", null);
		}
		else {
			map.put("completed", String.valueOf(todo.getCompleted()));
		}

		if (todo.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put("description", String.valueOf(todo.getDescription()));
		}

		if (todo.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(todo.getId()));
		}

		return map;
	}

	public static class TodoJSONParser extends BaseJSONParser<Todo> {

		@Override
		protected Todo createDTO() {
			return new Todo();
		}

		@Override
		protected Todo[] createDTOArray(int size) {
			return new Todo[size];
		}

		@Override
		protected void setField(
			Todo todo, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "completed")) {
				if (jsonParserFieldValue != null) {
					todo.setCompleted((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					todo.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					todo.setId(Integer.valueOf((String)jsonParserFieldValue));
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
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

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}
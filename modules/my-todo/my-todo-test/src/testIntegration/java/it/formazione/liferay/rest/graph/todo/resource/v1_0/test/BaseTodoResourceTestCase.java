package it.formazione.liferay.rest.graph.todo.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import it.formazione.liferay.rest.graph.todo.client.dto.v1_0.Todo;
import it.formazione.liferay.rest.graph.todo.client.http.HttpInvoker;
import it.formazione.liferay.rest.graph.todo.client.pagination.Page;
import it.formazione.liferay.rest.graph.todo.client.resource.v1_0.TodoResource;
import it.formazione.liferay.rest.graph.todo.client.serdes.v1_0.TodoSerDes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtilsBean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Virgilio Alessandro
 * @generated
 */
@Generated("")
public abstract class BaseTodoResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_todoResource.setContextCompany(testCompany);

		TodoResource.Builder builder = TodoResource.builder();

		todoResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Todo todo1 = randomTodo();

		String json = objectMapper.writeValueAsString(todo1);

		Todo todo2 = TodoSerDes.toDTO(json);

		Assert.assertTrue(equals(todo1, todo2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Todo todo = randomTodo();

		String json1 = objectMapper.writeValueAsString(todo);
		String json2 = TodoSerDes.toJSON(todo);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Todo todo = randomTodo();

		todo.setDescription(regex);

		String json = TodoSerDes.toJSON(todo);

		Assert.assertFalse(json.contains(regex));

		todo = TodoSerDes.toDTO(json);

		Assert.assertEquals(regex, todo.getDescription());
	}

	@Test
	public void testDeleteTodo() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Todo todo = testDeleteTodo_addTodo();

		assertHttpResponseStatusCode(
			204, todoResource.deleteTodoHttpResponse(todo.getId()));

		assertHttpResponseStatusCode(
			404, todoResource.getTodoHttpResponse(todo.getId()));

		assertHttpResponseStatusCode(404, todoResource.getTodoHttpResponse(0));
	}

	protected Todo testDeleteTodo_addTodo() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteTodo() throws Exception {
		Todo todo = testGraphQLTodo_addTodo();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteTodo",
						new HashMap<String, Object>() {
							{
								put("todoId", todo.getId());
							}
						})),
				"JSONObject/data", "Object/deleteTodo"));

		JSONArray errorsJSONArray = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"todo",
					new HashMap<String, Object>() {
						{
							put("todoId", todo.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray.length() > 0);
	}

	@Test
	public void testGetTodo() throws Exception {
		Todo postTodo = testGetTodo_addTodo();

		Todo getTodo = todoResource.getTodo(postTodo.getId());

		assertEquals(postTodo, getTodo);
		assertValid(getTodo);
	}

	protected Todo testGetTodo_addTodo() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetTodo() throws Exception {
		Todo todo = testGraphQLTodo_addTodo();

		Assert.assertTrue(
			equals(
				todo,
				TodoSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"todo",
								new HashMap<String, Object>() {
									{
										put("todoId", todo.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/todo"))));
	}

	@Test
	public void testGraphQLGetTodoNotFound() throws Exception {
		Integer irrelevantTodoId = RandomTestUtil.randomInt();

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"todo",
						new HashMap<String, Object>() {
							{
								put("todoId", irrelevantTodoId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	@Test
	public void testPutTodo() throws Exception {
		Todo postTodo = testPutTodo_addTodo();

		Todo randomTodo = randomTodo();

		Todo putTodo = todoResource.putTodo(null, randomTodo);

		assertEquals(randomTodo, putTodo);
		assertValid(putTodo);

		Todo getTodo = todoResource.getTodo(randomTodo.getId());

		assertEquals(randomTodo, getTodo);
		assertValid(getTodo);
	}

	protected Todo testPutTodo_addTodo() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetTodosPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetTodosPage() throws Exception {
		GraphQLField graphQLField = new GraphQLField(
			"todos",
			new HashMap<String, Object>() {
				{
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		JSONObject todosJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/todos");

		Assert.assertEquals(0, todosJSONObject.get("totalCount"));

		Todo todo1 = testGraphQLTodo_addTodo();
		Todo todo2 = testGraphQLTodo_addTodo();

		todosJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/todos");

		Assert.assertEquals(2, todosJSONObject.get("totalCount"));

		assertEqualsIgnoringOrder(
			Arrays.asList(todo1, todo2),
			Arrays.asList(
				TodoSerDes.toDTOs(todosJSONObject.getString("items"))));
	}

	@Test
	public void testPostTodo() throws Exception {
		Todo randomTodo = randomTodo();

		Todo postTodo = testPostTodo_addTodo(randomTodo);

		assertEquals(randomTodo, postTodo);
		assertValid(postTodo);
	}

	protected Todo testPostTodo_addTodo(Todo todo) throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Todo testGraphQLTodo_addTodo() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Todo todo1, Todo todo2) {
		Assert.assertTrue(
			todo1 + " does not equal " + todo2, equals(todo1, todo2));
	}

	protected void assertEquals(List<Todo> todos1, List<Todo> todos2) {
		Assert.assertEquals(todos1.size(), todos2.size());

		for (int i = 0; i < todos1.size(); i++) {
			Todo todo1 = todos1.get(i);
			Todo todo2 = todos2.get(i);

			assertEquals(todo1, todo2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Todo> todos1, List<Todo> todos2) {

		Assert.assertEquals(todos1.size(), todos2.size());

		for (Todo todo1 : todos1) {
			boolean contains = false;

			for (Todo todo2 : todos2) {
				if (equals(todo1, todo2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(todos2 + " does not contain " + todo1, contains);
		}
	}

	protected void assertValid(Todo todo) throws Exception {
		boolean valid = true;

		if (todo.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("completed", additionalAssertFieldName)) {
				if (todo.getCompleted() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (todo.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Todo> page) {
		boolean valid = false;

		java.util.Collection<Todo> todos = page.getItems();

		int size = todos.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (Field field :
				getDeclaredFields(
					it.formazione.liferay.rest.graph.todo.dto.v1_0.Todo.
						class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(Todo todo1, Todo todo2) {
		if (todo1 == todo2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("completed", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						todo1.getCompleted(), todo2.getCompleted())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						todo1.getDescription(), todo2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(todo1.getId(), todo2.getId())) {
					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected Field[] getDeclaredFields(Class clazz) throws Exception {
		Stream<Field> stream = Stream.of(
			ReflectionUtil.getDeclaredFields(clazz));

		return stream.filter(
			field -> !field.isSynthetic()
		).toArray(
			Field[]::new
		);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_todoResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_todoResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, Todo todo) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("completed")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("description")) {
			sb.append("'");
			sb.append(String.valueOf(todo.getDescription()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected Todo randomTodo() throws Exception {
		return new Todo() {
			{
				completed = RandomTestUtil.randomBoolean();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomInt();
			}
		};
	}

	protected Todo randomIrrelevantTodo() throws Exception {
		Todo randomIrrelevantTodo = randomTodo();

		return randomIrrelevantTodo;
	}

	protected Todo randomPatchTodo() throws Exception {
		return randomTodo();
	}

	protected TodoResource todoResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseTodoResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private it.formazione.liferay.rest.graph.todo.resource.v1_0.TodoResource
		_todoResource;

}
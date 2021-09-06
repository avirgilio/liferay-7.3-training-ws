**THIS API WILL BE AVAILABLE AT:**  <hr>


**SWAGGER FILE**: ```http://localhost:8080/o/api?endpoint=http://localhost:8080/o/my-todo/v1.0/openapi.json```

**GET TODOS QUERY :**
```graphql
{
    todos {
        items {
            description,
            completed,
            id
        }
        page
        pageSize
        totalCount
    }
}


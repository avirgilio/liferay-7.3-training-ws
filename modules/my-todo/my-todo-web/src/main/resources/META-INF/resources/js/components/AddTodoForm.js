import React, { useState, useContext } from "react";

import ClayForm, { ClayInput, ClayCheckbox } from "@clayui/form";
import ClayModal, { useModal } from "@clayui/modal";
import ClayButton from "@clayui/button";
import TodoAppContext from "../TodoAppContext";
import { useMutation } from "@apollo/client";
import { POST_TODO } from "../graphql/mutations/todoMutations";
import ClayLoadingIndicator from "@clayui/loading-indicator";
import { GET_TODOS_QUERY } from "../graphql/queries/todoQueries";

export default AddTodoFormModal = () => {
  const [addTodo, { data, loading, error }] = useMutation(POST_TODO, {
    refetchQueries: [GET_TODOS_QUERY, "getTodos"],
  });
  const [visible, setVisible] = useState(false);
  const [description, setDescription] = useState("");
  const [isCompletedTodo, setIsCompletedTodo] = useState(false);
  const { spritemap } = useContext(TodoAppContext);

  const { observer, onClose } = useModal({
    onClose: () => setVisible(false),
  });

  const handleSubmit = () => {
    addTodo({
      variables: {
        description: description,
        completed: isCompletedTodo,
      },
    });

    setDescription("");
    setIsCompletedTodo(false);
    onClose();
  };

  if (loading) return <ClayLoadingIndicator />;
  if (error) return `Submission error! ${error.message}`;

  return (
    <>
      {visible && (
        <ClayModal
          observer={observer}
          size="lg"
          spritemap={spritemap}
          status="info"
        >
          <ClayModal.Header>
            {Liferay.Language.get("add-todo")}
          </ClayModal.Header>
          <ClayModal.Body>
            <ClayForm>
              <ClayForm.Group>
                <label htmlFor="todoDescription">description</label>
                <ClayInput
                  component="textarea"
                  id="todoDescription"
                  placeholder="Insert description here"
                  type="text"
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                />
              </ClayForm.Group>
              <ClayCheckbox
                aria-label="Completed"
                checked={isCompletedTodo}
                onChange={() => setIsCompletedTodo((val) => !val)}
                label="Completed"
              />
            </ClayForm>
          </ClayModal.Body>
          <ClayModal.Footer
            last={<ClayButton onClick={handleSubmit}>{"Submit"}</ClayButton>}
          />
        </ClayModal>
      )}
      <ClayButton displayType="primary" onClick={() => setVisible(true)}>
        {Liferay.Language.get("add-todo")}
      </ClayButton>
    </>
  );
};

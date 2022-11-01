package green.spring.todo_list;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoForm {

	 @NotEmpty(message="공백은 입력하실 수 없습니다.")
	 @Size(max=10)
	 private String item;
}

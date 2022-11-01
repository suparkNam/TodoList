package green.spring.todo_list;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
class TodoList2ApplicationTests {
	
	TodoRepository todoRepository;
	
	
	@Test
	public void update(@PathVariable("idx") Integer idx) {
		
		Optional<Todo> todo = todoRepository.findById(idx);
		
		todo.ifPresent(updateTodo->{
			updateTodo.setStatus("Y");
			todoRepository.save(updateTodo);
		});

		
	}

}

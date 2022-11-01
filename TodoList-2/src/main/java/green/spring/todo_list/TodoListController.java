package green.spring.todo_list;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TodoListController {
	
	private final TodoRepository todoRepository;
	
	
	
	public Page<Todo> getList(int page) {
		
        Pageable pageable = PageRequest.of(page, 10);
        return this.todoRepository.findAll(pageable);
    }
	
	@RequestMapping("/index")
	public String index(Model model, Todo todo,
			@RequestParam(value = "page", defaultValue="0")int page) {
			Page<Todo> paging = getList(page);
			model.addAttribute("paging", paging);
		return "index";
	}
	
	@RequestMapping("/index/{idx}")
	public String delete(@PathVariable("idx") Integer idx) {
		todoRepository.deleteById(idx);
		
		return "redirect:/";
	}
	
	@RequestMapping("/update/{idx}")
	public String update(@PathVariable("idx") Integer idx) {
		
		Optional<Todo> todo = todoRepository.findById(idx);
		
		todo.ifPresent(updateTodo->{
			updateTodo.setStatus("Y");
			todoRepository.save(updateTodo);
		});
		return "redirect:/";
		
	}
	
	@RequestMapping("/")
	public String root() {
	    return "redirect:/index";
	}
	
	@PostMapping("/Enter")
	public String createEnter(@Valid TodoForm todoForm, 
			BindingResult bindingResult) {
		
		 if (bindingResult.hasErrors()) {
	            return "redirect:/index";
	        }
		
		Todo todo = new Todo();
		todo.setItem(todoForm.getItem());
		todo.setStatus("N");
		
		this.todoRepository.save(todo);
		return "redirect:/index";
	}
	
}

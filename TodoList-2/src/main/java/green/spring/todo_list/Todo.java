package green.spring.todo_list;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDX_NO")
	@SequenceGenerator(sequenceName = "SEQ_IDX_NO", allocationSize = 1, name = "SEQ_IDX_NO")
	private Integer idx;
	
	private String item;
	
	private String status;
}

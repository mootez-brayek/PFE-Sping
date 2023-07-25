package Pi.Spring.Controller;

import Pi.Spring.Entity.Task;
import Pi.Spring.Entity.User;
import Pi.Spring.Service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/task/")
@RequiredArgsConstructor
@Transactional
@CrossOrigin("*")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;



    @PutMapping("enCours/{idTask}")
    @ResponseBody
    public ResponseEntity<?> setEnCours(@PathVariable Long idTask) {
        try {
            taskService.setEnCours(idTask);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    @PutMapping("update/{idTask}")
    @ResponseBody
    public ResponseEntity<?> updateTask(@PathVariable Long idTask,@RequestBody Task updatedTask) {
        try {
            taskService.updateTask(idTask,updatedTask);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }


    @PutMapping("submit/{idTask}")
    @ResponseBody
    public ResponseEntity<?> submitTask(@PathVariable Long idTask,@RequestBody Task updatedTask) {
        try {
            taskService.submitTask(idTask,updatedTask);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }


    @GetMapping("/get/{idTask}")
    public Task getTask(@PathVariable Long idTask){
        return taskService.getTask(idTask);
    }
}

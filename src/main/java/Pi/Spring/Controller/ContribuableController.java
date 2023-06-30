package Pi.Spring.Controller;



import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Service.ContribuableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Contribuable/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContribuableController {

    @Autowired
    private ContribuableService contribuableService;



    @PostMapping("/save")
    public Contribuable save(
            @RequestBody Contribuable contribuable) {
        return contribuableService.saveContribuable(contribuable);
    }


    @PutMapping("/update/{idContribuable}")
    @ResponseBody
    public  ResponseEntity<?> updateContribuable(@PathVariable ("idContribuable") Long idContribuable,@RequestBody Contribuable updatedContribuable){
        try {
            contribuableService.updateContribuable(idContribuable,updatedContribuable);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Contribuable");
        }
    }


    @DeleteMapping("/delete/{idContribuable}")
    @ResponseBody
    public void deleteContribuable(@PathVariable ("idContribuable") Long idContribuable){
        contribuableService.deleteContribuable(idContribuable);
    }

    @GetMapping ("/get/{idContribuable}")
    @ResponseBody
    public Contribuable getContribuable(@PathVariable ("idContribuable") Long idContribuable){
       return contribuableService.getContribuable(idContribuable);
    }


    @GetMapping ("/get")
    @ResponseBody
    public List<Contribuable> getAllContribuable(){
       return  contribuableService.getAllContribuables();
    }

    @GetMapping("/getContribuableNumber")
    public long getContribuableCount(){
        return contribuableService.getContribuableCount();
    }


}

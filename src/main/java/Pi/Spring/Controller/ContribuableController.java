package Pi.Spring.Controller;


import Pi.Spring.Auth.AuthenticationResponse;
import Pi.Spring.Auth.RegisterRequest;
import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Service.ContribuableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void updateContribuable(@PathVariable ("idContribuable") Long idContribuable){
        contribuableService.updateContribuable(idContribuable);
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
    public void getAllContribuable(){
        contribuableService.getAllContribuables();
    }


    @PostMapping("/AffectContribuable/{idContribuable}/{idSession}")
    public void AffectContribuable(@PathVariable ("idContribuable") Long idContribuable,@PathVariable ("idSession") Long idSession) {
        contribuableService.addAndAffectContribuable(idContribuable, idSession);
    }


}

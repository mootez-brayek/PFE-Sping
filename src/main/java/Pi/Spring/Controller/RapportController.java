package Pi.Spring.Controller;


import Pi.Spring.Entity.Contribuable;
import Pi.Spring.Entity.Rapport;
import Pi.Spring.Service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Rapport/")
@CrossOrigin("*")
public class RapportController {

    @Autowired
    public RapportService rapportService;


    @PostMapping("/save")
    public Rapport save(
            @RequestBody Rapport rapport) {
        return rapportService.AddRapport(rapport);
    }


    @PutMapping("/update/{idRapport}")
    @ResponseBody
    public void updateRapport(@PathVariable ("idRapport") Long idRapport){
        rapportService.updateRapport(idRapport);
    }


    @DeleteMapping("/delete/{idRapport}")
    @ResponseBody
    public void deleteRapport(@PathVariable ("idRapport") Long idRapport){
        rapportService.deleteRapport(idRapport);
    }

    @GetMapping ("/get/{idRapport}")
    @ResponseBody
    public void getRapport(@PathVariable ("idRapport") Long idRapport){
        rapportService.getRapport(idRapport);
    }


    @GetMapping ("/get")
    @ResponseBody
    public List<Rapport> getAllRapport(){
       return rapportService.getAllRapport();}
}

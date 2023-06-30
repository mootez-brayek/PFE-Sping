package Pi.Spring.Controller;


import Pi.Spring.Entity.Programme;
import Pi.Spring.Entity.Role;
import Pi.Spring.Entity.User;
import Pi.Spring.Service.ProgrammeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Programme")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProgrammeController {
    @Autowired
    ProgrammeService programmeService;


    @PostMapping("/save/{idSession}/{idControlleur}/{idSessionContribuable}")
    public Programme createProgramme(@RequestBody Programme programme,@PathVariable Long idSession, @PathVariable Long idControlleur, @PathVariable Long idSessionContribuable) {
        return programmeService.createProgramme(programme,idSession,idControlleur,idSessionContribuable);
    }

    @GetMapping("/getProgramme")
    public List<Programme> getProgrammes(){
        return programmeService.getProgrammes();
    }
}

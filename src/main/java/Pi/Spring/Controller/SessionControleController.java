package Pi.Spring.Controller;

import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;
import Pi.Spring.Service.SessionControleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Session")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SessionControleController {
    @Autowired
    private SessionControleService sessionControleService;


    @PostMapping("/save")
    public SessionControle addSession(@RequestBody SessionControle sessionControle) {
        return sessionControleService.AddSession(sessionControle);
    }

    @PutMapping("/update/{idSessionControle}")
    @ResponseBody
    public void updateSessionControle(@PathVariable ("idSessionControle") Long idSessionControle){
        sessionControleService.updateSessionControle(idSessionControle);
    }


    @DeleteMapping("/delete/{idSessionControle}")
    @ResponseBody
    public void deleteSessionControle(@PathVariable ("idSessionControle") Long idSessionControle){
        sessionControleService.deleteSessionControle(idSessionControle);
    }

    @GetMapping ("/get/{idSessionControle}")
    @ResponseBody
    public void getSessionControle(@PathVariable ("idSessionControle") Long idSessionControle){
        sessionControleService.getSession(idSessionControle);
    }


    @GetMapping ("/get")
    @ResponseBody
    public void getAllSessionControle(){
        sessionControleService.getAllSession();
    }
}

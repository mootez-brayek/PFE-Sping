package Pi.Spring.Controller;

import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;
import Pi.Spring.Repositury.UserRepo;
import Pi.Spring.Service.SessionControleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Session")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SessionControleController {
    @Autowired
    private SessionControleService sessionControleService;
    @Autowired
    private UserRepo userRepo;


    @PostMapping("/save")
    public SessionControle addSession(@RequestBody SessionControle session, @RequestParam("usernames")List<String>userNames,@RequestParam("contribuableNames")List<String>contribuableNames ) {
        return sessionControleService.AddSession(session,userNames,contribuableNames);
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
    public List<SessionControle> getAllSessionControle(){
        return sessionControleService.getAllSession();
    }



}

package Pi.Spring.Controller;

import Pi.Spring.Entity.SessionContribuable;
import Pi.Spring.Entity.SessionControle;
import Pi.Spring.Entity.User;
import Pi.Spring.Repositury.UserRepo;
import Pi.Spring.Service.SessionControleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SessionControle> addSession(@RequestBody SessionControle session, @RequestParam("usernames")List<String>userNames,@RequestParam("contribuableNames")List<String>contribuableNames,@RequestParam ("descriptions") List<String>descriptions ) {
        SessionControle createdSession= sessionControleService.AddSession(session,userNames,contribuableNames,  descriptions);
        return ResponseEntity.ok(createdSession);
    }

    @PutMapping ("update/{sessionId}")
    public ResponseEntity<?> updatSession(@PathVariable Long sessionId,@RequestBody SessionControle updatedSession, @RequestParam("usernames")List<String>userNames,@RequestParam("contribuableNames")List<String>contribuablenames) {
        try {
            sessionControleService.updateSessionControle(sessionId,updatedSession,userNames,contribuablenames);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }

    @GetMapping("/getSessionNumber")
    public long getSessionCount(){
        return sessionControleService.getSessionCount();
    }


    @DeleteMapping("/delete/{idSessionControle}")
    @ResponseBody
    public ResponseEntity<?> deleteSessionControle(@PathVariable  Long idSessionControle){
        try {
            sessionControleService.deleteSessionControle(idSessionControle);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting session");
        }
    }


    @PutMapping ("validate/{sessionId}")
    @ResponseBody
    public ResponseEntity<?> valideSession(@PathVariable Long sessionId) {
        try {
            sessionControleService.validateSession(sessionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }
    @GetMapping ("/getContribuable/{idSessionControle}")
    @ResponseBody
    public List<SessionContribuable> getSessionContribuable(@PathVariable ("idSessionControle") Long idSessionControle){
       return sessionControleService.getSessionContribuable(idSessionControle);
    }

    @GetMapping ("/getControlleurs/{idSessionControle}")
    @ResponseBody
    public List<User> getSessionControlleurs(@PathVariable ("idSessionControle") Long idSessionControle){
        return sessionControleService.getSessionControlleurs(idSessionControle);
    }

    @GetMapping ("/getSession/{idSession}")
    @ResponseBody
    public SessionControle getSession(@PathVariable ("idSession") Long idSession){
        return sessionControleService.getSession(idSession);
    }


    @GetMapping ("/get")
    @ResponseBody
    public List<SessionControle> getAllSessionControle(){
        return sessionControleService.getAllSession();
    }




}

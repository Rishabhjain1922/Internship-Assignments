package com.example.HR.Authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/hr")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class HRController {
    private final HRUserRepository hrUserRepository;
    public  static final Map<String, HRUser> activeSessions = new ConcurrentHashMap<>();
    public HRController(HRUserRepository hrUserRepository) {
        this.hrUserRepository = hrUserRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody HRUser user, HttpServletResponse response) {
        Optional<HRUser> foundUser = hrUserRepository.findByUsernameAndPassword(
                user.getUsername(),
                user.getPassword()
        );

        if (foundUser.isPresent()) {
            String sessionId = UUID.randomUUID().toString();
            activeSessions.put(sessionId, foundUser.get());

            Cookie cookie = new Cookie("HR_SESSION", sessionId);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody HRUser user) {
        if (hrUserRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        HRUser newUser = new HRUser();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPhoneNumber(user.getPhoneNumber());

        return ResponseEntity.ok(hrUserRepository.save(newUser));
    }

    @GetMapping("/check-session")
    public ResponseEntity<?> checkSession(@CookieValue(name = "HR_SESSION", required = false) String sessionId) {
        if (sessionId != null && activeSessions.containsKey(sessionId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "HR_SESSION", required = false) String sessionId) {
        if (sessionId != null) {
            activeSessions.remove(sessionId);
        }
        return ResponseEntity.ok().build();
    }
}
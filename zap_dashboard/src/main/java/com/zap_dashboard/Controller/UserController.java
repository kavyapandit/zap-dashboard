package com.zap_dashboard.Controller;

import com.zap_dashboard.entity.loginEntity;
import com.zap_dashboard.entity.usersEntity;

import jakarta.servlet.http.HttpServletRequest;

import com.zap_dashboard.Repository.UserRepository;
import com.zap_dashboard.Repository.loginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/attendance/{empId}")
    public ResponseEntity<?> getAttendanceLogs(@PathVariable String empId) {
        try {
            List<loginEntity> logs = loginRepository.findByEmpIdOrderByLoginTimeDesc(empId);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Error fetching attendance logs"));
        }
    }

    @GetMapping("/employees/{empId}")
    public ResponseEntity<?> getEmployee(@PathVariable String empId) {
        Optional<usersEntity> user = userRepository.findById(empId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
    }

    @PutMapping("/employees/{empId}")
    public ResponseEntity<?> updateEmployee(
        @PathVariable String empId,
        @RequestPart("name") String name,
        @RequestPart("email") String email,
        @RequestPart("phone") String phone,
        @RequestPart(value = "profilePic", required = false) MultipartFile profilePicFile
    ) {
        try {
            Optional<usersEntity> optionalUser = userRepository.findById(empId);
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("message", "User not found"));
            }

            usersEntity user = optionalUser.get();
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);

            if (profilePicFile != null && !profilePicFile.isEmpty()) {
                String filename = empId + "_" + profilePicFile.getOriginalFilename();
                String uploadDir = "uploads/";
                Files.createDirectories(Paths.get(uploadDir)); // ✅ Ensure folder exists
                File dest = new File(uploadDir + filename);
                profilePicFile.transferTo(dest);
                user.setProfilepic("http://localhost:8080/" + uploadDir + filename);
            }

            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "Profile updated successfully"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Error updating profile"));
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(
        @RequestParam("empId") String empId,
        @RequestParam("name") String name,
        @RequestParam("role") String role,
        @RequestParam("designation") String designation,
        @RequestParam("email") String email,
        @RequestParam("phone") String phone,
        @RequestParam("profilepic") MultipartFile profilepic
    ) {
        if (empId == null || name == null || role == null || designation == null || email == null || phone == null || profilepic == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "All fields are required"));
        }

        String filename = profilepic.getOriginalFilename();
        String generatedPassword = UUID.randomUUID().toString().substring(0, 8);

        usersEntity newUser = new usersEntity();
        newUser.setEmpId(empId);
        newUser.setName(name);
        newUser.setRole(role);
        newUser.setDesignation(designation);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setPassword(generatedPassword);
        newUser.setProfilepic(filename); 

        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "User added successfully", "password", generatedPassword));
    }

    @Autowired
    private loginRepository loginRepository;

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody Map<String, String> credentials, HttpServletRequest request) {
        System.out.println("Received Login Request: " + credentials);

        String empId = credentials.get("emp_id");
        String password = credentials.get("password");

        Map<String, String> response = new HashMap<>();

        if (empId == null || password == null) {
            response.put("status", "failed");
            response.put("message", "Employee ID and Password are required");
            return response;
        }

        Optional<usersEntity> user = userRepository.findByLoginCredentials(empId, password);

        if (user.isPresent()) {
            response.put("status", "success");
            response.put("message", "Login Successful");

            try {
                // Get client IP address
                String ipAddress = request.getRemoteAddr();

                // Save login log
                loginEntity log = new loginEntity();
                log.setEmpId(empId);
                log.setLoginTime(LocalDateTime.now());
                log.setLocation(ipAddress);

                loginRepository.save(log);
            } catch (Exception e) {
                e.printStackTrace(); // log but don't block login
            }
        } else {
            response.put("status", "failed");
            response.put("message", "Invalid Employee ID or Password");
        }

        return response;
    }

    
	/*
	 * @PostMapping("/login") public Map<String, String> loginUser(@RequestBody
	 * Map<String, String> credentials) {
	 * System.out.println("Received Login Request: " + credentials);
	 * 
	 * String empId = credentials.get("emp_id"); String password =
	 * credentials.get("password");
	 * 
	 * Map<String, String> response = new HashMap<>(); if (empId == null || password
	 * == null) { response.put("status", "failed"); response.put("message",
	 * "Employee ID and Password are required"); return response; }
	 * 
	 * Optional<usersEntity> user = userRepository.findByLoginCredentials(empId,
	 * password); if (user.isPresent()) { response.put("status", "success");
	 * response.put("message", "Login Successful"); } else { response.put("status",
	 * "failed"); response.put("message", "Invalid Employee ID or Password"); }
	 * return response; }
	 */

    @PostMapping("/logout")
    public Map<String, String> logoutUser(@RequestBody Map<String, String> payload) {
        String empId = payload.get("emp_id");

        Map<String, String> response = new HashMap<>();

        if (empId == null) {
            response.put("status", "failed");
            response.put("message", "Employee ID is required");
            return response;
        }

        try {
            loginEntity lastLogin = loginRepository.findTopByEmpIdOrderByLoginTimeDesc(empId);

            if (lastLogin != null) {
                lastLogin.setLogoutTime(LocalDateTime.now());
                loginRepository.save(lastLogin);

                response.put("status", "success");
                response.put("message", "Logout time saved");
            } else {
                response.put("status", "failed");
                response.put("message", "No login record found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "failed");
            response.put("message", "Error occurred");
        }

        return response;
    }

    @GetMapping("/employee/{empId}/name")
    public ResponseEntity<?> getEmployeeName(@PathVariable String empId) {
        try {
            Optional<String> employeeName = userRepository.findNameByEmpId(empId);

            if (employeeName.isPresent()) {
                return ResponseEntity.ok(Map.of("name", employeeName.get()));
            } else {
                return ResponseEntity.status(404).body(Map.of("message", "Employee not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Error fetching employee name"));
        }
    }
}

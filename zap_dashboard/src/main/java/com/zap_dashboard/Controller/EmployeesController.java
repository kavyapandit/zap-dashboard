package com.zap_dashboard.Controller;

import com.zap_dashboard.entity.employeesEntity;
import com.zap_dashboard.Repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Fetch all employees
    @GetMapping("/all")
    public List<employeesEntity> getAllEmployees() {
        return employeesRepository.findAll();
    }

    // Handle "Request for Change" and send email
    @PostMapping("/request-change")
    public String requestChange(@RequestBody RequestChangeRequest request) {
        try {
            System.out.println("Received request: employeeId=" + request.getEmployeeId() + ", managerEmail=" + request.getManagerEmail());

            if (request.getEmployeeId() == null || request.getEmployeeId().isEmpty()) {
                return "Error: Employee ID is required";
            }

            if (request.getManagerEmail() == null || request.getManagerEmail().isEmpty()) {
                return "Error: Manager email is required";
            }

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(request.getManagerEmail());
            helper.setFrom("kavyapandit306@gmail.com");
            helper.setSubject("Change Request for Employee ID: " + request.getEmployeeId());

            String htmlContent =
                "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<p>Dear Manager,</p>" +
                "<p><b>GREETINGS OF THE DAY!!</b></p>" +
                "<p>A change request has been raised for employee ID: <b>" + request.getEmployeeId() + "</b></p>" +
                "<p>Please send back your confirmation:</p>" +
                "<div style='margin: 20px 0;'>" +
                "<a href='http://localhost:8080/api/employees/approve?id=" + request.getEmployeeId() + "&newManagerEmail=" + request.getManagerEmail() + "' " +
                "style='background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; margin-right: 10px;'>Approve</a>" +
                "<a href='http://localhost:8080/api/employees/reject?id=" + request.getEmployeeId() + "' " +
                "style='background-color: #f44336; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px;'>Reject</a>" +
                "</div>" +
                "<p>Regards,<br>HR Team</p>" +
                "</body>" +
                "</html>";

            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }

    // Approve the manager change request and notify employee
    @GetMapping("/approve")
    public String approveRequest(@RequestParam String id, @RequestParam String newManagerEmail) {
        Optional<employeesEntity> optionalEmployee = employeesRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            employeesEntity employee = optionalEmployee.get();

            // Update manager info in DB
            employee.setMemail(newManagerEmail);
            employee.setManager("Updated Manager"); // Replace with logic to fetch manager name if needed
            employeesRepository.save(employee);

            // Notify employee
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                helper.setTo(employee.getEmail());
                helper.setFrom("kavyapandit306@gmail.com");
                helper.setSubject("Your Manager Has Been Updated");

                String htmlContent =
                    "<html>" +
                    "<body style='font-family: Arial, sans-serif;'>" +
                    "<p>Dear " + employee.getEname() + ",</p>" +
                    "<p>Your manager has been changed to <b>" + employee.getManager() + "</b>.</p>" +
                    "<p>For any queries, please reach out to HR.</p>" +
                    "<p>Regards,<br>HR Team</p>" +
                    "</body>" +
                    "</html>";

                helper.setText(htmlContent, true);
                mailSender.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
                return "Manager updated, but failed to notify employee: " + e.getMessage();
            }

            return "Request for employee ID " + id + " has been approved and employee notified.";
        } else {
            return "Employee not found for ID: " + id;
        }
    }

    @GetMapping("/reject")
    public String rejectRequest(@RequestParam String id) {
        return "Request for employee ID " + id + " has been rejected.";
    }

    static class RequestChangeRequest {
        private String employeeId;
        private String managerEmail;

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getManagerEmail() {
            return managerEmail;
        }

        public void setManagerEmail(String managerEmail) {
            this.managerEmail = managerEmail;
        }
    }
}

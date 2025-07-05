package com.zap_dashboard.Controller;

import com.zap_dashboard.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AttendanceReportController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-report")
    public String sendReport(@RequestBody ReportRequest request) {
        try {
            StringBuilder body = new StringBuilder("Attendance Report:\n\n");
            for (String line : request.getReportData()) {
                body.append(line).append("\n");
            }
            emailService.sendEmail(request.getEmail(), "Attendance Report", body.toString());
            return "Report sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send report via email";
        }
    }

    public static class ReportRequest {
        private String email;
        private List<String> reportData;

        // Getters and Setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public List<String> getReportData() { return reportData; }
        public void setReportData(List<String> reportData) { this.reportData = reportData; }
    }
}

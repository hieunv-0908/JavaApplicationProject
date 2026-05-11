# 🔍 Báo Cáo Debug - Chức Năng Đặt Lịch Khám

## ❌ Vấn Đề

Chức năng đặt lịch khám không hoạt động thành công. User submit form nhưng không thấy phản hồi hoặc gặp lỗi.

## 🎯 Các Vấn Đề Đã Phát Hiện & Sửa

### 1. **Thiếu Logging** ⭐ (Đã Sửa)
**Vấn đề:** Không có logging để debug khi có lỗi
**Giải pháp:** Thêm logging chi tiết vào:
- `AppointmentController.bookAppointment()`
- `AppointmentService.bookAppointment()`

### 2. **Cấu Hình Thymeleaf Thiếu** ⭐ (Đã Sửa)
**File:** `application.properties`
**Vấn đề:** Không có cấu hình Thymeleaf → Spring không biết render template
**Giải pháp:** Thêm cấu hình:
```properties
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.cache=false
```

### 3. **Sai Tên View File** ⭐ (Đã Sửa)
**File:** `PatientController.java`
**Vấn đề:** Trả về `"patient/medical-records"` nhưng file là `"medical-history.html"`
**Giải pháp:** Sửa thành `"patient/medical-history"`

### 4. **Sai Tên View File (Admin)** ⭐ (Đã Sửa)
**File:** `AdminController.java`
**Vấn đề:** Trả về `"admin/medicine-list"` nhưng file là `"medicines.html"`
**Giải pháp:** Sửa thành `"admin/medicines"`

### 5. **String Concatenation Lỗi** ⭐ (Đã Sửa)
**File:** `AdminUserController.java`
**Vấn đề:** `"doc" + "torDto"` tạo thành `"doctoDto"`
**Giải pháp:** Sửa thành `"doctorDto"`

---

## 🔧 Các Logging Đã Thêm

### AppointmentController
```java
System.out.println("=== BOOK APPOINTMENT REQUEST ===");
System.out.println("Doctor ID: " + doctorId);
System.out.println("Appointment Date: " + appointmentDate);
System.out.println("Appointment Time: " + appointmentTime);
System.out.println("Note: " + note);
System.out.println("User from session: " + user);
```

### AppointmentService
```java
System.out.println("=== BOOK APPOINTMENT SERVICE ===");
System.out.println("Patient: " + patient.getFullName());
System.out.println("Doctor ID: " + doctorId);
System.out.println("Date: " + date);
System.out.println("Time: " + time);
System.out.println("Note: " + note);
System.out.println("Doctor found: " + doctor.getFullName());
```

---

## 🔍 Các Điểm Cần Kiểm Tra Tiếp Theo

### 1. **Session Management**
- Kiểm tra xem user có được lưu trong session không
- Kiểm tra session timeout

### 2. **Form Data Parsing**
- Kiểm tra cách browser gửi `LocalDate` và `LocalTime`
- Có thể cần `@DateTimeFormat` annotation

### 3. **Database Constraints**
- Kiểm tra unique constraint trong bảng appointments
- Kiểm tra foreign key constraints

### 4. **JavaScript Form Submission**
- Kiểm tra xem form có submit đúng data không
- Kiểm tra JavaScript validation

---

## 🚀 Cách Test

### 1. Khởi Động Ứng Dụng
```bash
./gradlew bootRun
```

### 2. Đăng Nhập
- Username: `patient1`
- Password: `patient12345`

### 3. Truy Cập Đặt Lịch
```
http://localhost:8081/patient/appointments/book
```

### 4. Submit Form
- Chọn chuyên khoa → Bác sĩ → Ngày → Giờ → Ghi chú
- Submit và xem console log

### 5. Quan Sát Console
Bạn sẽ thấy output như:
```
=== BOOK APPOINTMENT REQUEST ===
Doctor ID: 1
Appointment Date: 2026-05-15
Appointment Time: 09:00
Note: Test appointment
User from session: User(id=4, username=patient1, ...)

=== BOOK APPOINTMENT SERVICE ===
Patient: Phạm Minh Đức
Doctor ID: 1
Date: 2026-05-15
Time: 09:00
Note: Test appointment
Doctor found: Bác Sĩ Nguyễn Văn An
```

---

## ⚠️ Các Lỗi Có Thể Gặp

### 1. **Session Null**
```
User from session: null
```
→ User chưa đăng nhập hoặc session expired

### 2. **Doctor Not Found**
```
Doctor not found
```
→ Doctor ID không tồn tại trong database

### 3. **Date/Time Parse Error**
```
Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDate'
```
→ Cần thêm `@DateTimeFormat` annotation

### 4. **Duplicate Appointment**
```
Khung giờ này đã có người đặt
```
→ Đã có appointment trùng thời gian

### 5. **Past Date/Time**
```
Không thể đặt lịch trong quá khứ
```
→ User chọn ngày/giờ trong quá khứ

---

## 🔧 Giải Pháp Dự Phòng

### 1. Thêm @DateTimeFormat
```java
@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime appointmentTime,
```

### 2. Thêm Validation
```java
@Valid @ModelAttribute AppointmentBookingDto dto
```

### 3. Cải Thiện Error Handling
```java
catch (Exception e) {
    logger.error("Error booking appointment", e);
    redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
}
```

---

## 📊 Status

- ✅ **Thymeleaf Config:** Fixed
- ✅ **View Names:** Fixed  
- ✅ **String Concatenation:** Fixed
- ✅ **Logging:** Added
- 🔄 **Testing:** In Progress
- ❓ **Root Cause:** Awaiting test results

---

## 📞 Tiếp Theo

Sau khi test với logging mới, chúng ta sẽ biết chính xác lỗi nằm ở đâu và có thể fix cụ thể hơn.

---

**Ngày:** 2026-05-11
**Status:** Debugging in progress


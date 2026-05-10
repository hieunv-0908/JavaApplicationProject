# Database Seeder Documentation

## 📋 Mô Tả

File `Seeder.java` cung cấp dữ liệu mẫu đầy đủ cho ứng dụng quản lý y tế. Dữ liệu sẽ tự động được nhập vào cơ sở dữ liệu khi ứng dụng Spring Boot khởi động lần đầu.

## ✨ Dữ Liệu Được Seed

### 1. **Chuyên Khoa (Specialties)** - 6 bản ghi

| Code | Tên | Mô Tả |
|------|-----|-------|
| NEURO | Thần Kinh | Chuyên khoa về các bệnh liên quan đến hệ thần kinh |
| CARDIO | Tim Mạch | Chuyên khoa về các bệnh tim mạch và huyết áp |
| ORTHO | Chỉnh Hình | Chuyên khoa về xương khớp |
| DERM | Da Liễu | Chuyên khoa về da liễu |
| PEDIA | Nhi Khoa | Chuyên khoa về các bệnh ở trẻ em |
| OB_GYN | Sản Phụ Khoa | Chuyên khoa về bà bầu và phụ nữ |

### 2. **Tài Khoản Người Dùng (Users)**

#### Admin Account
- **Username:** admin
- **Password:** admin123456
- **Email:** admin@medical.com
- **Role:** ADMIN

#### Doctor Accounts
| Username | Password | Email | Name |
|----------|----------|-------|------|
| doctor1 | doctor123456 | doctor1@medical.com | Bác Sĩ Nguyễn Văn An (Thần Kinh) |
| doctor2 | doctor123456 | doctor2@medical.com | Tiến Sĩ Trần Thị Bình (Tim Mạch) |
| doctor3 | doctor123456 | doctor3@medical.com | Bác Sĩ Lê Quốc Hùng (Chỉnh Hình) |

#### Patient Accounts
| Username | Password | Email | Name |
|----------|----------|-------|------|
| patient1 | patient12345 | patient1@medical.com | Phạm Minh Đức |
| patient2 | patient12345 | patient2@medical.com | Võ Thị Hương |
| patient3 | patient12345 | patient3@medical.com | Đỗ Văn Tài |

### 3. **Bác Sĩ (Doctors)** - 3 bản ghi

Mỗi bác sĩ có thông tin chi tiết:
- Họ tên đầy đủ
- Số điện thoại
- Địa chỉ
- Ngày sinh
- Giới tính
- Bằng cấp
- Số năm kinh nghiệm
- Tiểu sử chuyên môn
- Chuyên khoa

### 4. **Bệnh Nhân (Patients)** - 3 bản ghi

Mỗi bệnh nhân có thông tin:
- Họ tên đầy đủ
- Số điện thoại
- Địa chỉ
- Ngày sinh
- Giới tính
- Thời gian tạo

### 5. **Thuốc (Medicines)** - 8 bản ghi

| Code | Tên | Giá | Số Lượng | Đơn vị |
|------|-----|-----|---------|--------|
| MED001 | Paracetamol 500mg | 15,000 VND | 500 | Viên |
| MED002 | Ibuprofen 400mg | 20,000 VND | 300 | Viên |
| MED003 | Amoxicillin 500mg | 25,000 VND | 200 | Viên |
| MED004 | Vitamin C 1000mg | 12,000 VND | 400 | Viên |
| MED005 | Aspirin 100mg | 18,000 VND | 250 | Viên |
| MED006 | Metformin 500mg | 22,000 VND | 180 | Viên |
| MED007 | Lisinopril 10mg | 28,000 VND | 150 | Viên |
| MED008 | Omeprazole 20mg | 24,000 VND | 100 | Viên |

### 6. **Loại Xét Nghiệm (Test Types)** - 8 bản ghi

| Code | Tên | Giá | Loại Mẫu | Thời gian xử lý |
|------|-----|-----|---------|-----------------|
| BLOOD001 | Xét nghiệm máu tổng quát | 150,000 VND | Máu tĩnh mạch | 24 giờ |
| BLOOD002 | Kiểm tra đường huyết | 100,000 VND | Máu tĩnh mạch | 12 giờ |
| BLOOD003 | Xét nghiệm mỡ máu | 250,000 VND | Máu tĩnh mạch | 24 giờ |
| BLOOD004 | Xét nghiệm chức năng gan | 200,000 VND | Máu tĩnh mạch | 24 giờ |
| URINE001 | Xét nghiệm nước tiểu tổng quát | 80,000 VND | Nước tiểu buổi sáng | 12 giờ |
| ECG001 | Điện tâm đồ (ECG) | 300,000 VND | Không cần mẫu | 1 giờ |
| XRAY001 | X-quang ngực | 200,000 VND | Hình ảnh | 24 giờ |
| ULTRASOUND001 | Siêu âm ổ bụng | 350,000 VND | Hình ảnh siêu âm | 24 giờ |

### 7. **Lịch Khám (Appointments)** - 3 bản ghi

| Bệnh nhân | Bác Sĩ | Ngày | Giờ | Trạng thái | Ghi chú |
|-----------|--------|------|-----|-----------|---------|
| Phạm Minh Đức | Bác Sĩ Nguyễn Văn An | T+5 ngày | 09:00 | CONFIRMED | Tái khám bệnh đau đầu |
| Võ Thị Hương | Tiến Sĩ Trần Thị Bình | T+7 ngày | 10:30 | CONFIRMED | Khám sức khỏe định kỳ |
| Phạm Minh Đức | Tiến Sĩ Trần Thị Bình | T-2 ngày | 14:00 | COMPLETED | Khám chuyên khoa tim mạch |

### 8. **Hồ Sơ Y Tế (Medical Records)** - 1 bản ghi

Được tạo từ lịch khám đã hoàn thành:
- **Triệu chứng:** Đau đầu, chóng mặt, khó ngủ
- **Chẩn đoán:** Rối loạn thần kinh, căng thẳng
- **Kết luận:** Tư vấn thay đổi lối sống, uống thuốc theo đơn

### 9. **Đơn Thuốc (Prescriptions)** - 1 bản ghi

Được tạo từ hồ sơ y tế

### 10. **Chi Tiết Đơn Thuốc (Prescription Details)** - 2 bản ghi

Mỗi đơn thuốc có 2-3 loại thuốc được kê đơn

## 🚀 Cách Sử Dụng

### Khởi Động Ứng Dụng

```bash
# Sử dụng Gradle
./gradlew bootRun

# Hoặc chạy qua IDE JetBrains
# Nhấn Ctrl+Shift+F10 (hoặc tương tự) để chạy ứng dụng
```

### Dữ Liệu Sẽ Được Tự Động Seeding Nếu:
- Database trống (hoặc bảng không có dữ liệu)
- Ứng dụng khởi động lần đầu tiên

### Quan Sát Quá Trình Seeding

Xem console output, bạn sẽ thấy:
```
========== DATABASE SEEDING STARTED ==========

✓ Created 6 specialties
✓ Created Admin user: admin / admin123456
✓ Created Doctor user: doctor1 / doctor123456
✓ Created Doctor user: doctor2 / doctor123456
✓ Created Doctor user: doctor3 / doctor123456
✓ Created Patient user: patient1 / patient12345
✓ Created Patient user: patient2 / patient12345
✓ Created Patient user: patient3 / patient12345
✓ Created 3 doctors
✓ Created 3 patients
✓ Created 8 medicines
✓ Created 8 test types
✓ Created 3 appointments
✓ Created 1 medical records
✓ Created 1 prescriptions
✓ Created 2 prescription details

========== DATABASE SEEDING COMPLETED SUCCESSFULLY ==========
```

## 🔐 Chế Độ Bảo Vệ

- **Mật khẩu được mã hóa:** Sử dụng BCrypt
- **Kiểm tra trùng lặp:** Dữ liệu sẽ được kiểm tra, nếu đã tồn tại sẽ được bỏ qua (Skip)
- **Giao dịch an toàn:** Sử dụng `@Transactional`

## ⚙️ Cấu Trúc Seeding

Seeder được chia thành các phương thức riêng lẻ:

1. `seedSpecialties()` - Tạo chuyên khoa
2. `seedUsers()` - Tạo tài khoản người dùng, bác sĩ, bệnh nhân
3. `seedMedicines()` - Tạo dữ liệu thuốc
4. `seedTestTypes()` - Tạo loại xét nghiệm
5. `seedAppointments()` - Tạo lịch khám
6. `seedMedicalRecords()` - Tạo hồ sơ y tế
7. `seedPrescriptions()` - Tạo đơn thuốc và chi tiết đơn thuốc

## 📝 Ghi Chú

- Các hồ sơ y tế được tạo từ các lịch khám đã hoàn thành
- Các đơn thuốc được kê từ các hồ sơ y tế
- Kiểm tra `count()` được sử dụng để tránh tạo dữ liệu trùng lặp khi khởi động lại ứng dụng
- Status của lịch khám: PENDING, CONFIRMED, COMPLETED, CANCELLED
- Status của đơn thuốc: PENDING_DISPENSE, DISPENSED

## 🔄 Để Reset Dữ Liệu

Nếu muốn reset dữ liệu và seeding lại:

1. **Xóa database hiện tại** (backup trước nếu cần)
2. **Xóa các file dữ liệu** nếu sử dụng H2 hoặc SQLite
3. **Khởi động lại ứng dụng** - dữ liệu mới sẽ được seed tự động

Hoặc sử dụng SQL:
```sql
DELETE FROM prescription_details;
DELETE FROM prescriptions;
DELETE FROM medical_records;
DELETE FROM appointments;
DELETE FROM test_types;
DELETE FROM medicines;
DELETE FROM doctors;
DELETE FROM patients;
DELETE FROM specialties;
DELETE FROM users;
```

Sau đó khởi động lại ứng dụng.

## 📞 Hỗ Trợ

Nếu gặp lỗi trong quá trình seeding:
- Kiểm tra kết nối database
- Kiểm tra lỗi trong console output
- Đảm bảo các repository đã được inject đúng
- Kiểm tra các foreign key constraints

---

**Cập nhật lần cuối:** 2026-05-10


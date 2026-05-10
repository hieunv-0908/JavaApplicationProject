# 🎯 QUICK START - Seeder Data

## ✅ Hoàn Thành

File `Seeder.java` đã được viết hoàn chỉnh với dữ liệu mẫu đầy đủ!

## 📊 Dữ Liệu Được Cung Cấp

| Item | Số Lượng | Ghi Chú |
|------|----------|--------|
| Chuyên Khoa | 6 | NEURO, CARDIO, ORTHO, DERM, PEDIA, OB_GYN |
| Tài Khoản (Users) | 7 | 1 Admin + 3 Bác Sĩ + 3 Bệnh Nhân |
| Bác Sĩ (Doctors) | 3 | Với chi tiết đầy đủ |
| Bệnh Nhân (Patients) | 3 | Với chi tiết đầy đủ |
| Thuốc (Medicines) | 8 | Từ Paracetamol đến Omeprazole |
| Xét Nghiệm (Test Types) | 8 | Máu, Nước tiểu, ECG, X-quang, Siêu âm |
| Lịch Khám (Appointments) | 3 | CONFIRMED & COMPLETED |
| Hồ Sơ Y Tế (Medical Records) | 1 | Từ appointments hoàn thành |
| Đơn Thuốc (Prescriptions) | 1 | Từ medical records |
| Chi Tiết Đơn Thuốc (Prescription Details) | 2 | Kết nối đơn thuốc với thuốc |

## 🚀 Để Chạy

```bash
./gradlew bootRun
```

Hoặc nhấn `Shift+F10` trên IDE JetBrains

## 🔐 Tài Khoản Test

```
Admin:
  Username: admin
  Password: admin123456

Bác Sĩ:
  Username: doctor1/2/3
  Password: doctor123456

Bệnh Nhân:
  Username: patient1/2/3
  Password: patient12345
```

## 📄 Tài Liệu

- `SEEDER_DOCUMENTATION.md` - Tài liệu chi tiết
- `SEEDER_GUIDE_VI.md` - Hướng dẫn Tiếng Việt

## ⚠️ Lưu Ý

- Lần đầu khởi động: Tạo tất cả dữ liệu
- Lần khởi động tiếp theo: Skip dữ liệu đã tồn tại
- Để reset: Xóa DB rồi restart ứng dụng

## 📍 Vị Trí File

```
src/main/java/re/java_application_project_final/Seeder.java
```

File này tự động chạy khi ứng dụng khởi động (implements CommandLineRunner)

---

✅ **Status:** Sẵn sàng sử dụng!


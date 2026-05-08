INSERT INTO users(username, password, email, role, enabled)
VALUES
    ('doctor1', '$2a$10$8W0Q6p3kM7l9nK5yQxM8QeLzQmQ3J7i2y1hF8j0R4ZkGmT1vB9D7K', 'doctor1@gmail.com', 'DOCTOR', true),

    ('doctor2', '$2a$10$8W0Q6p3kM7l9nK5yQxM8QeLzQmQ3J7i2y1hF8j0R4ZkGmT1vB9D7K', 'doctor2@gmail.com', 'DOCTOR', true),

    ('doctor3', '$2a$10$8W0Q6p3kM7l9nK5yQxM8QeLzQmQ3J7i2y1hF8j0R4ZkGmT1vB9D7K', 'doctor3@gmail.com', 'DOCTOR', true),

    ('doctor4', '$2a$10$8W0Q6p3kM7l9nK5yQxM8QeLzQmQ3J7i2y1hF8j0R4ZkGmT1vB9D7K', 'doctor4@gmail.com', 'DOCTOR', true),

    ('doctor5', '$2a$10$8W0Q6p3kM7l9nK5yQxM8QeLzQmQ3J7i2y1hF8j0R4ZkGmT1vB9D7K', 'doctor5@gmail.com', 'DOCTOR', true);
INSERT INTO doctors (
    user_id,
    full_name,
    phone,
    address,
    date_of_birth,
    gender,
    degree,
    experience_years,
    biography,
    specialty_id,
    created_at,
    updated_at
)
VALUES
    (
        4,
        'Nguyen Van An',
        '0901234567',
        'Ha Noi',
        '1985-03-12',
        'MALE',
        'Tien si Y khoa',
        12,
        'Chuyen dieu tri benh tim mach va tu van suc khoe tong quat.',
        1,
        NOW(),
        NOW()
    ),

    (
        5,
        'Tran Thi Hoa',
        '0912345678',
        'Hai Phong',
        '1990-07-21',
        'FEMALE',
        'Thac si Noi khoa',
        8,
        'Bac si co kinh nghiem dieu tri benh noi khoa.',
        2,
        NOW(),
        NOW()
    ),

    (
        6,
        'Le Minh Duc',
        '0987654321',
        'Da Nang',
        '1982-11-05',
        'MALE',
        'Chuyen khoa II',
        15,
        'Chuyen gia ve than kinh va phuc hoi chuc nang.',
        3,
        NOW(),
        NOW()
    ),

    (
        7,
        'Pham Thu Trang',
        '0977123456',
        'Ho Chi Minh',
        '1993-01-17',
        'FEMALE',
        'Bac si Da khoa',
        5,
        'Tan tam voi benh nhan va co ky nang giao tiep tot.',
        1,
        NOW(),
        NOW()
    ),

    (
        8,
        'Hoang Quoc Bao',
        '0966888999',
        'Can Tho',
        '1988-09-28',
        'MALE',
        'Tien si Ngoai khoa',
        10,
        'Chuyen phau thuat va dieu tri ngoai khoa.',
        2,
        NOW(),
        NOW()
    );

INSERT INTO medicines (
    code,
    name,
    description,
    manufacturer,
    price,
    quantity,
    unit,
    dosage,
    usage_instructions,
    side_effects,
    contraindications,
    active,
    created_at,
    updated_at
)
VALUES
    (
        'MED001',
        'Paracetamol',
        'Thuốc giảm đau hạ sốt',
        'DHG Pharma',
        10000,
        200,
        'Viên',
        '500mg',
        'Uống sau ăn',
        'Buồn nôn nhẹ',
        'Không dùng cho người dị ứng paracetamol',
        true,
        NOW(),
        NOW()
    );
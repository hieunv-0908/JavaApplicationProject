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
        1,
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
        2,
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
        3,
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
        4,
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
        5,
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
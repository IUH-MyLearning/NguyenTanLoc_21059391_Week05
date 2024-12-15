# Works Project

## Tác giả
- Họ tên: Nguyễn Tấn Lộc
- MSSV: 21059391

## Mô tả dự án
Đây là website tuyển dụng cho phép công ty tìm kiếm ứng viên phù hợp cho công việc đang tuyển dụng và các ứng viên có thể 
tìm kiếm công việc phù hợp cho mình.

## Công nghệ sử dụng
- **Ngôn ngữ lập trình**: Java.
- **Framework**: Spring Boot.
- **CSDL**: MariaDB.
- **Khác**: Thymeleaf, Spring Boot Mail API.

## Tính năng
#### Chung
- Đăng nhập/Đăng ký 
#### Công ty
- Đăng bài tuyển dụng.
- Xem thông tin các ứng viên phù hợp với kỹ năng của công việc đang tuyển.
- Gửi email mời cho các ứng viên phù hợp công việc đang đăng tuyển.
#### Ứng viên
- Xem thông tin các công việc phù hợp với kỹ năng của ứng viên.
- Xem thông tin các kỹ năng còn thiếu được gợi ý để có thể học thêm.

## Về dự án
#### Cấu trúc dự án
![Ảnh chụp cấu trúc dự án](https://drive.google.com/uc?id=1frkrxmXjNHb7WRFwOW8UgHOC8lPz7gr8)
#### Mô hình CSDL
![Ảnh chụp mô hình csdl](https://drive.google.com/uc?id=14RZajgyKiKLGxepGJWykcLOgCxP1j9yP)

## Cách cài đặt
1. Clone dự án:
   ```bash
   git clone https://github.com/IUH-MyLearning/NguyenTanLoc_21059391_Week05.git
2. Cấu hình
    ``` bash
    spring.application.name=NguyenTanLoc_21059391_Week05
    spring.datasource.url=jdbc:mariadb://localhost:3306/works
    spring.datasource.username=root
    spring.datasource.password=sapassword
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.sql.init.mode=always
    
    #Mail
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=<your-gmail>
    spring.mail.password=<your-app-password>
    spring.mail.protocol=smtp
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    spring.mail.properties.mail.smtp.starttls.required=true
    spring.mail.properties.mail.smtp.connectiontimeout=5000
    spring.mail.properties.mail.smtp.timeout=5000
    spring.mail.properties.mail.smtp.writetimeout=5000

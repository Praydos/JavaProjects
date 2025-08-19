
## 📝 Mini Report – Spring Boot Auth Application with Spring Security

### 1. **Application Objective**

The application provides **user management** with authentication and authorization using **Spring Security**.
It allows:

* User login with roles (`USER`, `ADMIN`).
* Route protection based on roles.
* Display and management of users stored in the database.

---

### 2. **Main Components**

#### 🔐 **Security Configuration (SecurityConfig)**

* **CSRF disabled** for easier testing.
* Access rules:

  * `/home`, `/login`, `/register/**` → accessible to everyone.
  * `/user/**` → only accessible to users with **USER** role.
  * `/admin/**` → only accessible to **ADMIN**.
  * All other URLs → require authentication.
* **Form-based login** enabled (`formLogin`).
* `DaoAuthenticationProvider` configured with `UserDetailsService`.

#### 👤 **User Service (MyUserDetailService)**

* Retrieves users from the database via **MyUserRepository**.
* Converts `MyUser` entity to Spring Security’s `UserDetails`.
* Handles roles (default `USER` if none, otherwise split by comma).
* Passwords are currently stored **without encryption** (`{noop}`).

#### 📑 **Controller (controller)**

* Available pages:

  * `/home` → public home page.
  * `/login` → login page.
  * `/user/home` → user dashboard.
  * `/admin/home` → admin dashboard.
* Simple CRUD for users:

  * `/user/all-users` → list all users.
  * `/users/new` → form to add a new user.
  * `/users/save` → save user to the database.

#### 🗄️ **Entity (MyUser)**

* `MyUser` entity mapped to the `USERS` table.
* Fields: `id`, `username`, `password`, `role`.

---

### 3. **Strengths**

✅ Clear role-based access control and URL protection.
✅ Integration of Spring Security with `UserDetailsService`.
✅ Simple interface for managing and displaying users.
✅ Uses `Thymeleaf` for front-end templates.

---


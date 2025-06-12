# ToDoZen – Java Web To-Do App

## Features
- User registration/login (session-based)
- Add, edit, delete, mark complete tasks
- Priority, deadline, tags
- Filter/sort (dashboard)
- Bootstrap 5 UI, dark/light mode
- H2 database (file-based, no setup required)

## Project Structure
- `src/com/todozen/` – Java source (controller, dao, model, util)
- `web/jsp/` – JSP pages
- `web/css/` – Styles
- `web/js/` – JavaScript
- `web/WEB-INF/` – `web.xml` and `schema.sql`

## Setup & Run Instructions
1. **Open in NetBeans 25** as a standard web project (not Maven).
2. **Add Libraries**: Jakarta EE 11, JSTL, H2 JDBC driver (add jars to project libraries).
3. **Database**: H2 will auto-create the DB file at first run. To initialize tables, execute `web/WEB-INF/schema.sql` using H2 Console or via Java code.
4. **Deploy** to Apache Tomcat 11 (set web root to `web`).
5. **Access** at [http://localhost:8080/ToDoZen/jsp/login.jsp](http://localhost:8080/ToDoZen/jsp/login.jsp)

## Notes
- Bootstrap 5 via CDN.
- Default DB is H2; you can adapt `DBConnection.java` for MySQL if needed.
- For dark mode, use the toggle button in the navbar.
- All main features are in `dashboard.jsp`.

---
**Enjoy your productivity with ToDoZen!**

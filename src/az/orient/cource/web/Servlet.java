package az.orient.cource.web;

import az.orient.cource.dao.*;
import az.orient.cource.model.*;
import az.orient.cource.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


@WebServlet(name = "ControllerServlet", urlPatterns = "/cs")
public class Servlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "";
        String address = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PrintWriter pw = response.getWriter();
        StudentDao studentDao = new StudentDaoImpl();
        StudentService studentService = new StudentServiceImpl(studentDao);
        TeacherDao teacherDao = new TeacherDaoImpl();
        TeacherService teacherService = new TeacherServiceImpl(teacherDao);
        PaymentDao paymentDao = new PaymentDaoImpl();
        PaymentService paymentService = new PaymentServiceImpl(paymentDao);
        LessonDao lessonDao = new LessonDoImpl();
        LessonService lessonService = new LessonSefviceImpl(lessonDao);

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        try {
            if (action.equalsIgnoreCase("register")) {

                pw.write("<h1> Success <h1>");
            } else if (action.equalsIgnoreCase("getStudentList")) {
                List<Student> studentList = studentService.getStudentList();
                request.setAttribute("studentList", studentList);
                address = "/WEB-INF/pages/studentList.jsp";
            } else if (action.equalsIgnoreCase("getTeacherList")) {
                List<Teacher> teacherList = teacherService.getTeacherList();
                request.setAttribute("teacherList", teacherList);
                address = "/WEB-INF/pages/teacherList.jsp";

            } else if (action.equalsIgnoreCase("addStudent")) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String adress = request.getParameter("address");
                String phone = request.getParameter("phone");
                String dob = request.getParameter("dob");
                Student student = new Student();
                student.setName(name);
                student.setSurname(surname);
                student.setAddress(adress);
                student.setPhone(phone);
                if (dob != null)
                    student.setDob(df.parse(dob));
                boolean isAdded = studentService.addStudent(student);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }

            } else if (action.equalsIgnoreCase("addTeacher")) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String adress = request.getParameter("address");
                String phone = request.getParameter("phone");
                String dob = request.getParameter("dob");
                Teacher teacher = new Teacher();
                teacher.setName(name);
                teacher.setSurname(surname);
                teacher.setAddress(adress);
                teacher.setPhone(phone);
                if (dob != null)
                    teacher.setDob(df.parse(dob));
                boolean add = teacherService.addTeacher(teacher);
                response.setContentType("text/html");
                if (add) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("getStudentData")) {
                List<Student> studentList = studentService.getStudentList();
                request.setAttribute("studentList", studentList);
                address = "/WEB-INF/pages/studentData.jsp";
            } else if (action.equalsIgnoreCase("getTeacherData")) {
                List<Teacher> teacherList = teacherService.getTeacherList();
                request.setAttribute("teacherList", teacherList);
                address = "/WEB-INF/pages/teacherData.jsp";
            } else if (action.equalsIgnoreCase("editStudent")) {
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                Student student = studentService.getStudentById(studentId);
                request.setAttribute("student", student);
                address = "/WEB-INF/pages/editStudent.jsp";
            } else if (action.equalsIgnoreCase("updateStudent")) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String adress = request.getParameter("address");
                String phone = request.getParameter("phone");
                String dob = request.getParameter("dob");
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                Student student = new Student();
                student.setName(name);
                student.setSurname(surname);
                student.setAddress(adress);
                student.setPhone(phone);
                if (dob != null)
                    student.setDob(df.parse(dob));
                boolean isUpdate = studentService.updateStudent(student, studentId);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("deleteStudent")) {
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                boolean delete = studentService.deleteStudent(studentId);
                if (delete) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("editTeacher")) {
                Long teacherId = Long.parseLong(request.getParameter("teacherId"));
                Teacher teacher = teacherService.getTeacherById(teacherId);
                request.setAttribute("teacher", teacher);
                address = "/WEB-INF/pages/editTeacher.jsp";
            } else if (action.equalsIgnoreCase("updateTeacher")) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String adress = request.getParameter("address");
                String phone = request.getParameter("phone");
                String dob = request.getParameter("dob");
                Long teacherId = Long.parseLong(request.getParameter("teacherId"));
                Teacher teacher = new Teacher();
                teacher.setName(name);
                teacher.setSurname(surname);
                teacher.setAddress(adress);
                teacher.setPhone(phone);
                if (dob != null)
                    teacher.setDob(df.parse(dob));
                boolean isUpdate = teacherService.updateTeacher(teacher, teacherId);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("deleteTeacher")) {
                Long teacherId = Long.parseLong(request.getParameter("teacherId"));
                boolean delete = teacherService.deleteTeacher(teacherId);
                if (delete) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("searchStudentData")) {
                String keyword = request.getParameter("keyword");
                List<Student> studentList = studentService.searchStudentData(keyword);
                request.setAttribute("studentList", studentList);
                address = "WEB-INF/pages/studentData.jsp";
            } else if (action.equalsIgnoreCase("getPaymentList")) {
                List<Teacher> teacherList =teacherService.getTeacherList();
                List<Lesson> lessonList =lessonService.getLessonList();
                List<Payment> paymentList = paymentService.getPaymentList();
                request.setAttribute("teacherList",teacherList);
                request.setAttribute("lessonList",lessonList);
                request.setAttribute("paymentList", paymentList);

                address = "/WEB-INF/pages/paymentList.jsp";
            } else if (action.equalsIgnoreCase("newPayment")) {
                List<Student> studentList = studentService.getStudentList();
                List<Teacher> teacherList = teacherService.getTeacherList();
                List<Lesson> lessonList = lessonService.getLessonList();
                request.setAttribute("studentList", studentList);
                request.setAttribute("teacherList", teacherList);
                request.setAttribute("lessonList", lessonList);
                address = "/WEB-INF/pages/newPayment.jsp";
            } else if (action.equalsIgnoreCase("addPayment")) {
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                Long teacherId = Long.parseLong(request.getParameter("teacherId"));
                Long lessonId = Long.parseLong(request.getParameter("lessonId"));
                String amountStr = request.getParameter("amount");
                Double amount = null;
                if (amountStr != null && !amountStr.isEmpty()) {
                    amount = Double.parseDouble(amountStr);
                }
                Payment payment = new Payment();
                Student student = new Student();
                student.setId(studentId);
                Teacher teacher = new Teacher();
                teacher.setId(teacherId);
                Lesson lesson = new Lesson();
                lesson.setId(lessonId);
                payment.setStudent(student);
                payment.setTeacher(teacher);
                payment.setLesson(lesson);
                payment.setAmount(amount);
                boolean isAdded = paymentService.addPayment(payment);
                response.setContentType("text/html");
                if (isAdded) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            } else if (action.equalsIgnoreCase("getPaymentData")) {
                List<Payment> paymentList = paymentService.getPaymentList();
                request.setAttribute("paymentList", paymentList);
                address = "/WEB-INF/pages/paymentData.jsp";
            } else if (action.equalsIgnoreCase("editPayment")) {
                Long paymentId = Long.parseLong(request.getParameter("paymentId"));
                List<Student> studentList = studentService.getStudentList();
                List<Teacher> teacherList = teacherService.getTeacherList();
                List<Lesson> lessonList = lessonService.getLessonList();
                request.setAttribute("studentList", studentList);
                request.setAttribute("teacherList", teacherList);
                request.setAttribute("lessonList", lessonList);
                Payment payment = paymentService.getPaymentById(paymentId);
                request.setAttribute("payment", payment);
                address = "/WEB-INF/pages/editPayment.jsp";
            }else if(action.equalsIgnoreCase("updatePayment")){
                Long studentId = Long.parseLong(request.getParameter("studentId"));
                Long teacherId = Long.parseLong(request.getParameter("teacherId"));

                Long lessonId = Long.parseLong(request.getParameter("lessonId"));
                String amountStr = request.getParameter("amount");
                Double amount = null;
                if (amountStr != null && !amountStr.isEmpty()) {
                    amount = Double.parseDouble(amountStr);
                }
                Long paymentId=Long.parseLong(request.getParameter("paymentId"));
                Payment payment = new Payment();
                Student student = new Student();
                student.setId(studentId);
                Teacher teacher = new Teacher();
                teacher.setId(teacherId);
                Lesson lesson = new Lesson();
                lesson.setId(lessonId);
                payment.setStudent(student);
                payment.setTeacher(teacher);
                payment.setLesson(lesson);
                payment.setAmount(amount);
                boolean isUpdate = paymentService.updatePayment(payment,paymentId);
                response.setContentType("text/html");
                if (isUpdate) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            }else if(action.equalsIgnoreCase("deletePayment")){
                Long paymentId=Long.parseLong(request.getParameter("paymentId"));
                boolean delete=paymentService.deletePayment(paymentId);
                if (delete) {
                    pw.print("success");
                } else {
                    pw.print("error");
                }
            }else if(action.equalsIgnoreCase("getTeacherComboListByLessonId")){
                Long lessonId=Long.parseLong(request.getParameter("lessonId"));
                List<Teacher> teacherList=teacherService.getTeacherComboListByLessonId(lessonId);
                request.setAttribute("teacherList",teacherList);
                address="/WEB-INF/pages/teacherCombo.jsp";
            }else if(action.equalsIgnoreCase("advancedSearchPayment")){
                Long lessonId=Long.parseLong(request.getParameter("lessonId"));
                Long teacherId=Long.parseLong(request.getParameter("teacherId"));
                String minAmount=request.getParameter("minAmount");
                String maxAmount=request.getParameter("maxAmount");
                String beginDate=request.getParameter("beginDate");
                String endDate=request.getParameter("endDate");
                AdvancedSearch advancedSearch=new AdvancedSearch();
                advancedSearch.setLessonId(lessonId);
                advancedSearch.setTeacherId(teacherId);
                advancedSearch.setMinAmount(minAmount);
                advancedSearch.setMaxAmount(maxAmount);
                advancedSearch.setBeginDate(beginDate);
                advancedSearch.setEndDate(endDate);
                List<Payment> paymentList= paymentService.advancedSearchPayment(advancedSearch);
                request.setAttribute("paymentList",paymentList);
                address="/WEB-INF/pages/paymentData.jsp";

            }

        } catch (Exception x) {
            x.printStackTrace();
        }

        if (address != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
            requestDispatcher.forward(request, response);
        }


    }
}

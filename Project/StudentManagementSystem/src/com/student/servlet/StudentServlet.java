package com.student.servlet;

import com.student.dao.StudentDAO;
import com.student.model.Student;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = new StudentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                addStudent(request, response);
            } else if ("update".equals(action)) {
                updateStudent(request, response);
            } else if ("search".equals(action)) {
                searchStudents(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("delete".equals(action)) {
                deleteStudent(request, response);
            } else if ("edit".equals(action)) {
                showEditForm(request, response);
            } else {
                listStudents(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        
        Map<String, Integer> stats = studentDAO.getStatistics();
        request.setAttribute("stats", stats);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentList.jsp");
        dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        Student student = new Student();
        student.setName(request.getParameter("name"));
        student.setEmail(request.getParameter("email"));
        student.setCourse(request.getParameter("course"));
        student.setPhone(request.getParameter("phone"));
        student.setGrade(request.getParameter("grade"));
        studentDAO.addStudent(student);
        response.sendRedirect("students");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        request.setAttribute("student", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editStudent.jsp");
        dispatcher.forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        Student student = new Student();
        student.setId(Integer.parseInt(request.getParameter("id")));
        student.setName(request.getParameter("name"));
        student.setEmail(request.getParameter("email"));
        student.setCourse(request.getParameter("course"));
        student.setPhone(request.getParameter("phone"));
        student.setGrade(request.getParameter("grade"));
        studentDAO.updateStudent(student);
        response.sendRedirect("students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("students");
    }

    private void searchStudents(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String keyword = request.getParameter("keyword");
        List<Student> students = studentDAO.searchStudents(keyword);
        request.setAttribute("students", students);
        request.setAttribute("searchKeyword", keyword);
        
        Map<String, Integer> stats = studentDAO.getStatistics();
        request.setAttribute("stats", stats);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentList.jsp");
        dispatcher.forward(request, response);
    }
}

package com.klef.jfsd.springboot.controller;


import com.klef.jfsd.springboot.DTO.FacultyLeaveRequest;
import com.klef.jfsd.springboot.models.*;
import com.klef.jfsd.springboot.repository.SectionRepository;
import com.klef.jfsd.springboot.repository.StudentCourseMappingRepository;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private  SectionRepository sectionRepository;

    @GetMapping("ccCourses")
    public ResponseEntity<?> ccCourses(@RequestParam int facultyId){
     List<Course> courses= facultyService.ccCourses(facultyId);
     if(courses==null){
         return  ResponseEntity.noContent().build();
     }
     return ResponseEntity.ok(courses);
    }

    @PostMapping("uploadHandout")
    public ResponseEntity<?> uploadHandout(@RequestParam int courseId,
                                           @RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            return ResponseEntity.badRequest().body("Invalid file");
        }

        String data=null;
        if(!file.isEmpty()) {
            String fileType = file.getContentType();
            String fileData = Base64.getEncoder().encodeToString(file.getBytes());
            data = "data:"+fileType +";base64,"+fileData;

        }

        Course course= facultyService.uploadHandout(courseId, data);
        if (course == null) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok("Handout successfully uploaded");
    }

    @PostMapping("applyLeave")
    public  ResponseEntity<?> applyLeave(@RequestBody FacultyLeaveRequest request){
        FacultyLeave facultyLeave = new FacultyLeave();
        facultyLeave.setFaculty(adminService.viewFacultyById(request.getFacultyId()));
        facultyLeave.setReason(request.getReason());
        facultyLeave.setStartDate(request.getStartDate());
        facultyLeave.setEndDate(request.getEndDate());
        facultyService.applyLeave(facultyLeave);
        return ResponseEntity.ok("Leave successfully applied");
    }

    @GetMapping("viewLeavesByFaculty")
    public  ResponseEntity<?> viewLeavesByFaculty(@RequestParam int facultyId){
        List<FacultyLeave> facultyLeaves = facultyService.viewFacultyLeaves(facultyId);
        if(facultyLeaves==null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facultyLeaves);
    }


    @GetMapping("ViewFacultyCourses")
    public  ResponseEntity<?> ViewFacultyCourses(@RequestParam int facultyId){
        List<CourseFacultyMapping> mappings = adminService.viewCourseFacultyMapping(facultyId);
        if(mappings==null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mappings);
    }

    @PostMapping("uploadContent")
    public ResponseEntity<?> uploadContext(@RequestParam MultipartFile file,
                                           @RequestParam int sectionId,
                                           @RequestParam String title) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(sectionId+" "+ title);
        CourseContent courseContent = new CourseContent();
        courseContent.setTitle(title);
        courseContent.setSection(sectionRepository.findById(sectionId).orElse(null));
        String data=null;
        if(!file.isEmpty()) {
            String fileType = file.getContentType();
            String fileData = Base64.getEncoder().encodeToString(file.getBytes());
            data = "data:"+fileType +";base64,"+fileData;

        }
        courseContent.setContent(data);
        facultyService.uploadCourseContent(courseContent);
        return  ResponseEntity.ok(courseContent)    ;
    }

    @GetMapping("viewSectionByFaculty")
    public  ResponseEntity<?> viewSectionByFaculty(@RequestParam int facultyId){
        List<Section> sections = facultyService.viewALlSections(facultyId);
        if(sections==null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sections);
    }

    @GetMapping("viewCourseContentBySection")
    public  ResponseEntity<?> viewCourseContentBySection(@RequestParam int sectionId){
        List<CourseContent> contents = facultyService.viewCoursesBySectionId(sectionId);
        if(contents==null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contents);
    }


    @GetMapping("viewProfile")
    public  ResponseEntity<?> viewProfile(@RequestParam int facultyId){
        Faculty faculty = adminService.viewFacultyById(facultyId);
        if(faculty==null){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(faculty);
    }
}


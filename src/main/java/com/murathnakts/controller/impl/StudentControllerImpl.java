package com.murathnakts.controller.impl;

import com.murathnakts.controller.BaseController;
import com.murathnakts.controller.IStudentController;
import com.murathnakts.dto.DtoStudent;
import com.murathnakts.dto.DtoStudentIU;
import com.murathnakts.entity.RootEntity;
import com.murathnakts.entity.Student;
import com.murathnakts.services.IStudentService;
import com.murathnakts.utils.PageableRequest;
import com.murathnakts.utils.PageableResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentControllerImpl extends BaseController implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping(path = "/save")
    @Override
    public DtoStudent saveStudent(@RequestBody @Valid DtoStudentIU dtoStudentIU) {
        return studentService.saveStudent(dtoStudentIU);
    }

    @GetMapping(path = "/list")
    @Override
    public List<DtoStudent> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/list/{id}")
    @Override
    public DtoStudent getStudentById(@PathVariable(name = "id") Integer id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteStudent(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public DtoStudent updateStudent(@PathVariable(name = "id") Integer id,
                                    @RequestBody DtoStudentIU dtoStudentIU) {
        return studentService.updateStudent(id, dtoStudentIU);
    }

    @GetMapping("/birth-date")
    @Override
    public List<String> getStudentBirthDate() {
        return studentService.getStudentBirthDate();
    }


    //@ModelAttribute
    @GetMapping(path = "/pageable")
    @Override
    public RootEntity<PageableResponse<DtoStudent>> findAllPageable(PageableRequest pageRequest) {
        Page<Student> page = studentService.findAllPageable(toPageable(pageRequest));
        return success(toPageableResponse(page, studentService.toDtoList(page.getContent())));
    }
}

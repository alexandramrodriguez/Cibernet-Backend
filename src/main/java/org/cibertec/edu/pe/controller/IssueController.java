package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.dto.IssueRequestDTO;
import org.cibertec.edu.pe.dto.ResponseDTO;
import org.cibertec.edu.pe.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issue")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IssueController {

	@Autowired
	private IssueService issueService;
	
	@GetMapping("/findAll")
	public ResponseDTO findAll(){
		return issueService.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseDTO findById(@PathVariable Long id) {
		return issueService.findById(id);
	}
	
	@PostMapping("/create")
	public ResponseDTO createIssue(@RequestBody IssueRequestDTO issue) {
		return issueService.createIssue(issue);
	}
	
	@PutMapping("/update/{id}")
	public ResponseDTO updateIssue(@PathVariable Long id, @RequestBody IssueRequestDTO issue) {
		return issueService.updateIssue(id, issue);
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseDTO deleteIssue(@PathVariable Long id){
        return issueService.deleteIssue(id);
    }
}

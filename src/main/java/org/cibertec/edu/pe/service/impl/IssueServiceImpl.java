package org.cibertec.edu.pe.service.impl;

import java.util.List;

import org.cibertec.edu.pe.dto.IssueRequestDTO;
import org.cibertec.edu.pe.dto.ResponseDTO;
import org.cibertec.edu.pe.entity.Issue;
import org.cibertec.edu.pe.entity.User;
import org.cibertec.edu.pe.repository.IssueRepository;
import org.cibertec.edu.pe.repository.UserRepository;
import org.cibertec.edu.pe.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService{

	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseDTO findAll() {
		ResponseDTO response = new ResponseDTO();
		try {
			List<Issue> issues = (List<Issue>) issueRepository.findAll();
			response.setData(issues);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("List of all issues");	
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error fetching issues");	
		}
		return response;
	}

	@Override
	public ResponseDTO findById(Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			Issue issue = issueRepository.findById(id).orElse(null);
			if(issue == null) throw new Exception("Issue doesn't exist.");
			response.setData(issue);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("Issue with id: "+id);	
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error fetching issue with id: "+id);	
		}
		return response;
	}

	@Override
	public ResponseDTO createIssue(IssueRequestDTO issue) {
		ResponseDTO response = new ResponseDTO();
		try {
			Issue newIssue = new Issue();
			newIssue.setContent(issue.getContent());
			User u = userRepository.findById(issue.getUserId()).orElse(null);
			if(u == null) throw new Exception("User doesn't exist.");
			newIssue.setUser(u);
			Issue dbIssue = issueRepository.save(newIssue);
			response.setData(dbIssue);
			response.setStatusCode(201);
			response.setError(false);
			response.setMessage("Issue created succesfully");			
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error creating issue");	
		}
		return response;
	}

	@Override
	public ResponseDTO updateIssue(Long id, IssueRequestDTO issue) {
		ResponseDTO response = new ResponseDTO();
		try {
			Issue dbIssue = issueRepository.findById(id).get();
			dbIssue.setContent(issue.getContent());
			User u = userRepository.findById(issue.getUserId()).orElse(null);
			if(u == null) throw new Exception("User doesn't exist.");
			dbIssue.setUser(u);
			Issue newIssue = issueRepository.save(dbIssue);
			response.setData(newIssue);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("Issue updated succesfully");
		}catch(Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error updating issue");
		}
		return response;
	}

	@Override
	public ResponseDTO deleteIssue(Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			Issue dbIssue = issueRepository.findById(id).orElse(null);
			if(dbIssue == null) throw new Exception("Issue doesn't exist.");
			issueRepository.delete(dbIssue);
			response.setData(dbIssue);
			response.setStatusCode(200);
			response.setError(false);
			response.setMessage("Issue deleted succesfully");
		}catch (Exception e) {
			response.setData(e.getMessage());
			response.setStatusCode(500);
			response.setError(true);
			response.setMessage("Error deleting issue");
		}
		return response;
	}

}

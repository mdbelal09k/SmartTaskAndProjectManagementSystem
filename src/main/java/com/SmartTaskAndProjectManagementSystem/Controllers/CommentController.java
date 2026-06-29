package com.SmartTaskAndProjectManagementSystem.Controllers;

import com.SmartTaskAndProjectManagementSystem.dto.*;
import com.SmartTaskAndProjectManagementSystem.service.CommentService;
import com.SmartTaskAndProjectManagementSystem.Util.ApiResonse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<ApiResonse<CommentResponse>> create(@Valid @RequestBody CommentRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResonse.success("Comment created", commentService.create(request)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResonse<CommentResponse>> getById(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResonse.success("Comment fetched", commentService.getById(id)));
	}

	@GetMapping("/by-task/{taskId}")
	public ResponseEntity<ApiResonse<Page<CommentResponse>>> getByTask(@PathVariable Long taskId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(ApiResonse.success("Comments fetched", commentService.getByTask(taskId, pageable)));
	}

	@GetMapping("/by-member/{memberId}")
	public ResponseEntity<ApiResonse<Page<CommentResponse>>> getByMember(@PathVariable Long memberId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity
				.ok(ApiResonse.success("Comments fetched", commentService.getByMember(memberId, pageable)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResonse<CommentResponse>> update(@PathVariable Long id,
			@Valid @RequestBody CommentRequest request) {
		return ResponseEntity.ok(ApiResonse.success("Comment updated", commentService.update(id, request)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResonse<Void>> delete(@PathVariable Long id) {
		commentService.delete(id);
		return ResponseEntity.ok(ApiResonse.success("Comment deleted", null));
	}
}